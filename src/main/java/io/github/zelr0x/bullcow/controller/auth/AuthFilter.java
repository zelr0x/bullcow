package io.github.zelr0x.bullcow.controller.filter;

import io.github.zelr0x.bullcow.controller.SessionAttrStore;
import io.github.zelr0x.bullcow.controller.RouteStore;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters all requests. The first filter of the application.
 */
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

        if (isAuthNotRequired(uri) || isLoggedIn(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        servletRequest.getRequestDispatcher(RouteStore.LOGIN)
                .forward(servletRequest, servletResponse);
    }

    private boolean isAuthNotRequired(final String uri) {
        return uri.equals(RouteStore.ROOT)
                || RouteStore.NO_AUTH_PATH_STARTS.stream()
                    .anyMatch(uri::startsWith);
    }

    private boolean isLoggedIn(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            final Boolean isLoggedIn = (Boolean) session
                    .getAttribute(SessionAttrStore.IS_LOGGED_IN);
            return isLoggedIn != null
                    && request.isRequestedSessionIdValid()
                    && isLoggedIn.equals(SessionAttrStore.LOGGED_IN);
        }
        return false;
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public void destroy() {

    }
}
