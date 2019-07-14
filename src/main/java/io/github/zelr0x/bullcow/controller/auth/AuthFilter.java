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
 * Filters all requests. The first filter of the application.
 */
@WebFilter(
        filterName = "AuthFilter",
        urlPatterns = RouteStore.ROOT + "*")
public final class AuthFilter implements Filter {
    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    /**
     * Redirects to login page all users trying to access resources
     * that are not in the white list if they don't have a valid jsessionid.
     *
     * It does NOT protect any service resources like shutdown ports
     * or admin panels.
     *
     * @param servletRequest a request
     * @param servletResponse a response
     * @param filterChain the filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String uri = request.getRequestURI().toLowerCase();

        if (AuthUtil.isAuthNotRequired(uri) || AuthUtil.isLoggedIn(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        servletRequest.getRequestDispatcher(RouteStore.LOGIN)
                .forward(servletRequest, servletResponse);
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public void destroy() {

    }
}
