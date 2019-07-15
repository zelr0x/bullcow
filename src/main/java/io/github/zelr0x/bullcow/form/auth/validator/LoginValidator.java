package io.github.zelr0x.bullcow.form.auth.validator;

import io.github.zelr0x.bullcow.form.auth.LoginForm;
import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;
import io.github.zelr0x.bullcow.service.IUserService;
import io.github.zelr0x.bullcow.service.UserService;

import java.util.Optional;

/**
 * LoginValidator validates LoginForm objects.
 */
public final class LoginValidator {
    private IUserService userService = new UserService();

    /**
     * Checks if a user with credentials contained in a specified
     * LoginForm object is valid.
     *
     * @param form a LoginForm object containing user credentials.
     * @return a ValidationResult object containing the results of the check.
     */
    public ValidationResult validate(final LoginForm form) {
        final ValidationResult result = new ValidationResult();
        checkCredentials(form, result);
        return result;
    }

    /**
     * Checks if a user with credentials contained in a specified
     * LoginForm object is registered in the application.
     *
     * @param form a LoginForm object containing user credentials.
     * @param result a ValidationResult object used to store
     *               a result of the check.
     */
    private void checkCredentials(final LoginForm form,
                                  final ValidationResult result) {
        final Optional<User> user = userService.getUser(
                UserDto.of(form.getUserName(), form.getPassword()));
        if (!user.isPresent()) {
            result.addError("Wrong credentials");
            result.setUser(Optional.empty());
        } else {
            result.setUser(user);
        }
    }
}
