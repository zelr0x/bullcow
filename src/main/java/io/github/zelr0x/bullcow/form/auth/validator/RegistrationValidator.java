package io.github.zelr0x.bullcow.form.auth.validator;

import io.github.zelr0x.bullcow.form.auth.RegistrationForm;
import io.github.zelr0x.bullcow.service.IUserService;
import io.github.zelr0x.bullcow.service.UserService;

public class RegistrationValidator {
    private IUserService userService = new UserService();

    public ValidationResult validate(final RegistrationForm form) {
        final ValidationResult result = new ValidationResult();
        checkPasswordsMatch(form, result);
        checkUsernameNotTaken(form, result);
        return result;
    }

    private void checkPasswordsMatch(final RegistrationForm form,
                                     final ValidationResult result) {
        if (!form.getPassword().equals(form.getPasswordRepeat())) {
            result.addError("Passwords don't match");
        }
    }

    private void checkUsernameNotTaken(final RegistrationForm form,
                                       final ValidationResult result) {
        if (!userService.isUsernameAssignable(form.getUserName())){
            result.addError("Username is already taken");
        }
    }
}
