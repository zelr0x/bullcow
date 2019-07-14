package io.github.zelr0x.bullcow.form.auth.validator;

import io.github.zelr0x.bullcow.form.auth.LoginForm;
import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;
import io.github.zelr0x.bullcow.service.IUserService;
import io.github.zelr0x.bullcow.service.UserService;

import java.util.Optional;

public class LoginValidator {
    private IUserService userService = new UserService();

    public ValidationResult validate(final LoginForm form) {
        final ValidationResult result = new ValidationResult();
        checkCredentials(form, result);
        return result;
    }

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
