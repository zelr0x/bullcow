package io.github.zelr0x.bullcow.controller.filter;

import io.github.zelr0x.bullcow.service.IUserService;
import io.github.zelr0x.bullcow.service.UserService;
import io.github.zelr0x.bullcow.util.CollectionUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Filters all requests. The first filter of the application.
 */
public final class AuthFilter implements Filter {
    private static final String ROOT = "/";

    static final String API_ROOT = "/api/";
    static final String STATIC_ROOT = "/static/";

    static final String RATING_ROUTE = "/rating";
    static final String LOGIN_ROUTE = "/login";

    /** NO_AUTH_ROUTES Should NOT include ROOT ("/"). */
    private static final Set<String> NO_AUTH_ROUTES =
            CollectionUtil.immutableSetOf(API_ROOT, STATIC_ROOT,
                    RATING_ROUTE, LOGIN_ROUTE);

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

        if (uri.equals(ROOT)
                || NO_AUTH_ROUTES.stream().anyMatch(uri::startsWith)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        final HttpSession session = request.getSession(false);
        if (session != null) {
            final IUserService userService = new UserService();
            if (userService.checkJsessionid(session.getId())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        final RequestDispatcher dispatcher =
                servletRequest.getRequestDispatcher(LOGIN_ROUTE);
        dispatcher.forward(servletRequest, servletResponse);
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public void destroy() {

    }
}
