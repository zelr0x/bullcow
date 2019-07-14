package io.github.zelr0x.bullcow.controller.auth;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.controller.util.SessionAttrStore;
import io.github.zelr0x.bullcow.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

final class AuthUtil {
    static void logUserIn(final HttpServletRequest request,
                          final HttpServletResponse response,
                          final Long userId)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        session.setAttribute(SessionAttrStore.IS_LOGGED_IN, SessionAttrStore.LOGGED_IN);
        request.getSession().setAttribute(SessionAttrStore.USER_ID, userId);
        request.getRequestDispatcher(PathStore.LOGGED_IN_HOME)
                .forward(request, response);
    }

    static void forwardToError(final HttpServletRequest request,
                        final HttpServletResponse response)
            throws ServletException, IOException {
        final String loginErrorMsg = "Could not log in";
        request.setAttribute(AuthParamStore.ERROR, loginErrorMsg);
        request.getRequestDispatcher(PathStore.ERROR_PAGE)
                .forward(request, response);
    }

    /**
     * Performs whitelist check and redundant blacklist check if needed.
     * @param uri uri to check
     * @return true if uri conforms to white- and black- list rules,
     * false otherwise.
     */
    static boolean isAuthNotRequired(final String uri) {
        return RouteStore.NO_AUTH_ENDPOINTS.stream().anyMatch(uri::equals)
                || RouteStore.NO_AUTH_ROUTE_STARTS.stream().anyMatch(uri::startsWith);
    }

    static boolean isLoggedIn(final HttpServletRequest request) {
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
}
