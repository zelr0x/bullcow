package io.github.zelr0x.bullcow.controller.servlet.auth;

import io.github.zelr0x.bullcow.controller.PathStore;
import io.github.zelr0x.bullcow.controller.RouteStore;
import io.github.zelr0x.bullcow.controller.UriUtil;
import io.github.zelr0x.bullcow.form.auth.LoginForm;
import io.github.zelr0x.bullcow.form.auth.validator.LoginValidator;
import io.github.zelr0x.bullcow.form.auth.validator.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(
        name = "LogInServlet",
        urlPatterns = {
                "/login",
                "/signin"})
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
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

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        final String username = request.getParameter(AuthParamStore.USERNAME);
        final String password = request.getParameter(AuthParamStore.PASSWORD);
        if (username == null || password == null) {
            AuthUtil.forwardToError(request, response);
        }

        final LoginForm form = new LoginForm(username, password);
        final LoginValidator validator = new LoginValidator();
        final ValidationResult validationResult = validator.validate(form);
        if (validationResult.isValid()) {
            AuthUtil.logUserIn(request, response);
            return;
        }

        AuthUtil.forwardToError(request, response);
    }
}
