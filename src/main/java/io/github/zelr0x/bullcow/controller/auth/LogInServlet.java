package io.github.zelr0x.bullcow.controller.auth;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.controller.util.UriUtil;
import io.github.zelr0x.bullcow.form.auth.LoginForm;
import io.github.zelr0x.bullcow.form.auth.validator.LoginValidator;
import io.github.zelr0x.bullcow.form.auth.validator.ValidationResult;
import io.github.zelr0x.bullcow.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * LogInServlet handles user log-in.
 */
@WebServlet(
        name = "LogInServlet",
        urlPatterns = {
                RouteStore.LOGIN,
                RouteStore.SIGN_IN})
public final class LogInServlet extends HttpServlet {
    /**
     * Returns log-in page.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        if (AuthUtil.isLoggedIn(request)) {
            request.getRequestDispatcher(PathStore.LOGGED_IN_HOME);
            return;
        }

        final Optional<String> fragment = UriUtil.getFragment(request.getRequestURI());
        if (!fragment.isPresent()) {
            request.getRequestDispatcher(PathStore.LOGIN_PAGE)
                    .forward(request, response);
            return;
        }
        response.sendRedirect(fragment.get().equals(RouteStore.REGISTRATION_FORM)
                ? RouteStore.REGISTRATION_FORM
                : RouteStore.LOGIN_FORM);
    }

    /**
     * Handles user log-in request.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        if (AuthUtil.isLoggedIn(request)) {
            request.getRequestDispatcher(PathStore.LOGGED_IN_HOME);
            return;
        }

        final String username = request.getParameter(AuthParamStore.USERNAME);
        final String password = request.getParameter(AuthParamStore.PASSWORD);
        if (username == null || password == null) {
            AuthUtil.forwardToError(request, response);
            return;
        }

        final LoginForm form = new LoginForm(username, password);
        final LoginValidator validator = new LoginValidator();
        final ValidationResult validationResult = validator.validate(form);
        final Optional<User> user = validationResult.getUser();
        if (validationResult.isValid() && user.isPresent()) {
            AuthUtil.logUserIn(request, response, user.get().getId());
            return;
        }

        AuthUtil.forwardToError(request, response);
    }
}
