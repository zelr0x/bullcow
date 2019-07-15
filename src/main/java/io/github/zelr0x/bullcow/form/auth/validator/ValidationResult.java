package io.github.zelr0x.bullcow.form.auth.validator;

import io.github.zelr0x.bullcow.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ValidationResult is used to store the results of various checks.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class ValidationResult {
    private Optional<User> user;

    private boolean valid = true;

    private List<String> errors;

    /**
     * Add an error to the list of errors.
     *
     * @param errorMessage a error message describing the error
     */
    void addError(final String errorMessage) {
        if (isValid()) {
            errors = new ArrayList<>();
            valid = false;
        }
        errors.add(errorMessage);
    }

    /**
     * Checks if this ValidationResult is valid.
     *
     * @return true if this ValidationResult contains no errors,
     * false otherwise
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Returns a list of errors.
     *
     * @return a list of errors.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Sets an optional user which may be helpful to avoid
     * duplicate queries or abstraction leaks.
     *
     * @param user an Optional object containing a User or nothing
     */
    // TODO?: use User (not optional) as a parameter
    // TODO?: use UserDto
    public void setUser(final Optional<User> user) {
        this.user = user;
    }

    /**
     * Returns an optional user if set.
     *
     * @return an Optional object containing a User or nothing
     */
    // TODO?: use UserDto
    public Optional<User> getUser() {
        return user;
    }
}
