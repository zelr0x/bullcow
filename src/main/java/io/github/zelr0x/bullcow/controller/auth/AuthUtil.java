package io.github.zelr0x.bullcow.controller.auth;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.controller.util.SessionAttrStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthUtil contains utility methods related to authentication.
 */
final class AuthUtil {
    /**
     * Performs all the steps necessary to log in a user.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @param userId a Long object containing the id of the user.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    static void logUserIn(final HttpServletRequest request,
                          final HttpServletResponse response,
                          final Long userId)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        session.setAttribute(SessionAttrStore.IS_LOGGED_IN, SessionAttrStore.LOGGED_IN);
        request.getSession().setAttribute(SessionAttrStore.USER_ID, userId);
        response.sendRedirect(RouteStore.LOGGED_IN_HOME);
    }

    /**
     * Forwards a user to error page.
     * empty return statement is required if it is used in the middle
     * of the controller's method.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    static void forwardToError(final HttpServletRequest request,
                               final HttpServletResponse response)
            throws ServletException, IOException {
        final String loginErrorMsg = "Could not log in";
        request.setAttribute(AuthParamStore.ERROR, loginErrorMsg);
        request.getRequestDispatcher(PathStore.ERROR_PAGE)
                .forward(request, response);
    }

    /**
     * Checks if a user that issued a specified request is logged in.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @return true if the user is logged in, false otherwise
     */
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

    /**
     * Prevents instantiation.
     */
    private AuthUtil() {
        throw new AssertionError();
    }
}
