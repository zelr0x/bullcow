package io.github.zelr0x.bullcow.controller.auth;

import io.github.zelr0x.bullcow.controller.util.RouteStore;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * AuthFilter filters all requests that were not allowed in
 * by other filters. It is the main filter of the application.
 */
@WebFilter(filterName = "AuthFilter")
public final class AuthFilter implements Filter {
    /**
     * Called by the web container to indicate to a filter
     * that it is being placed into service.
     *
     * @param filterConfig A filter configuration object used by
     *                     a servlet container to pass information
     *                     to a filter during initialization.
     */
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    /**
     * Redirects to login page all users trying to access resources
     * that are not in the white list if they are not logged in.
     *
     * It does NOT meant to protect any service resources like shutdown ports
     * or admin panels.
     *
     * @param servletRequest an HttpServletRequest object that contains
     *                       the request the client has made of the servlet.
     * @param servletResponse an HttpServletResponse object that contains
     *                        the response the servlet sends to the client.
     * @param filterChain a FilterChain object giving a view into the
     *                    invocation chain of a filtered request for a resource.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String uri = request.getRequestURI().toLowerCase();

        if (isAuthNotRequired(uri) || AuthUtil.isLoggedIn(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        servletRequest.getRequestDispatcher(RouteStore.LOGIN)
                .forward(servletRequest, servletResponse);
    }

    /**
     * Performs whitelist check and redundant blacklist check if needed.
     *
     * @param uri a String object containing a uri to check.
     * @return true if uri conforms to white- and black- list rules,
     * false otherwise.
     */
    private static boolean isAuthNotRequired(final String uri) {
        return RouteStore.NO_AUTH_ENDPOINTS.stream().anyMatch(uri::equals)
                || RouteStore.NO_AUTH_ROUTE_STARTS.stream().anyMatch(uri::startsWith);
    }

    /**
     * Called by the web container to indicate to a filter
     * that it is being taken out of service.
     */
    @Override
    public void destroy() {

    }
}
