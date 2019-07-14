package io.github.zelr0x.bullcow.controller.auth;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.form.auth.RegistrationForm;
import io.github.zelr0x.bullcow.form.auth.validator.RegistrationValidator;
import io.github.zelr0x.bullcow.form.auth.validator.ValidationResult;
import io.github.zelr0x.bullcow.model.dto.UserDto;
import io.github.zelr0x.bullcow.service.IUserService;
import io.github.zelr0x.bullcow.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(
        name = "RegistrationServlet",
        urlPatterns = {
                RouteStore.REGISTER,
                RouteStore.SIGN_UP})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(AuthUtil.isLoggedIn(request)
                    ? PathStore.LOGGED_IN_HOME
                    : PathStore.LOGIN_PAGE)
                .forward(request, response);
    }

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
        final String repeat = request.getParameter(AuthParamStore.PASSWORD_REPEAT);
        if (username == null || password == null || repeat == null) {
            AuthUtil.forwardToError(request, response);
            return;
        }

        final RegistrationForm form = new RegistrationForm(username, password, repeat);
        final RegistrationValidator validator = new RegistrationValidator();
        final ValidationResult validationResult = validator.validate(form);
        if (validationResult.isValid()) {
            final IUserService userService = new UserService();
            final Optional<Long> userId = userService.addUser(
                    UserDto.of(username, password));
            if (userId.isPresent()) {
                AuthUtil.logUserIn(request, response, userId.get());
                return;
            }
        }
        AuthUtil.forwardToError(request, response);
    }
}