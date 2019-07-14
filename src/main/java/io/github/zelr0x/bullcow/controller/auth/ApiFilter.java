package io.github.zelr0x.bullcow.controller.filter;

import io.github.zelr0x.bullcow.controller.RouteStore;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filters requests to API endpoints.
 */
public final class ApiFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String uri = request.getRequestURI().toLowerCase();
        if (uri.startsWith(RouteStore.API_PUB_ROOT)) {
            final String path = request.getServletPath();
            servletRequest.getRequestDispatcher(path)
                    .forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
