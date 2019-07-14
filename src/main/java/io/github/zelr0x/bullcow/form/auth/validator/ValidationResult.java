package io.github.zelr0x.bullcow.form.auth.validator;

import io.github.zelr0x.bullcow.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class ValidationResult {
    private Optional<User> user;

    private boolean valid = true;

    private List<String> errors;

    void addError(final String errorMessage) {
        if (isValid()) {
            errors = new ArrayList<>();
            valid = false;
        }
        errors.add(errorMessage);
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setUser(final Optional<User> user) {
        this.user = user;
    }

    public Optional<User> getUser() {
        return user;
    }
}
