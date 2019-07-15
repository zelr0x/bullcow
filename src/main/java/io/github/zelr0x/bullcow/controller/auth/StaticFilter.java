package io.github.zelr0x.bullcow.controller.auth;

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
 * StaticFilter filters requests to static resources.
 * Currently allows all requests /static/*.
 */
@WebFilter(filterName = "StaticFilter")
public final class StaticFilter implements Filter {
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
     * Allows all requests to static resources.
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
        final String path = request.getServletPath();
        servletRequest.getRequestDispatcher(path)
                .forward(servletRequest, servletResponse);
    }

    /**
     * Called by the web container to indicate to a filter
     * that it is being taken out of service.
     */
    @Override
    public void destroy() {

    }
}
