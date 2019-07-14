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
 * Filters requests to static resources.
 */
@WebFilter(
        filterName = "StaticFilter",
        urlPatterns = RouteStore.STATIC_ROOT + "*")
public final class StaticFilter implements Filter {
    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    /**
     * Currently just forwards requests.
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
        final String path = request.getServletPath();
        servletRequest.getRequestDispatcher(path)
                .forward(servletRequest, servletResponse);
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public void destroy() {

    }
}
