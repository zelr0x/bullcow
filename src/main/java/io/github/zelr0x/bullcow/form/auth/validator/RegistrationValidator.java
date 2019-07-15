package io.github.zelr0x.bullcow.form.auth.validator;

import io.github.zelr0x.bullcow.form.auth.RegistrationForm;
import io.github.zelr0x.bullcow.service.IUserService;
import io.github.zelr0x.bullcow.service.UserService;

/**
 * RegistrationValidator validates RegistrationForm objects.
 */
public final class RegistrationValidator {
    private IUserService userService = new UserService();

    /**
     * Checks if a user with credentials contained in a specified
     * RegistrationForm object is valid.
     *
     * @param form a RegistrationForm object containing user credentials.
     * @return a ValidationResult object containing the results of the check.
     */
    public ValidationResult validate(final RegistrationForm form) {
        final ValidationResult result = new ValidationResult();
        checkPasswordsMatch(form, result);
        checkUsernameNotTaken(form, result);
        return result;
    }

    /**
     * Checks if the password contained in a specified
     * RegistrationForm object matches its repeat contained in
     * the same form.
     *
     * @param form a RegistrationForm object containing user credentials.
     * @param result a ValidationResult object used to store
     *               a result of the check.
     */
    private void checkPasswordsMatch(final RegistrationForm form,
                                     final ValidationResult result) {
        if (!form.getPassword().equals(form.getPasswordRepeat())) {
            result.addError("Passwords don't match");
        }
    }

    /**
     * Checks if the username contained in a specified RegistrationForm
     * object is not taken by another user.
     *
     * @param form a RegistrationForm object containing user credentials.
     * @param result a ValidationResult object used to store
     *               a result of the check.
     */
    // TODO: Some synchronization is needed (maybe at the DB side)
    //  otherwise the check should be disabled
    //  (if username repeats are tolerable).
    private void checkUsernameNotTaken(final RegistrationForm form,
                                       final ValidationResult result) {
        if (!userService.isUsernameAssignable(form.getUserName())) {
            result.addError("Username is already taken");
        }
    }
}
