package io.github.zelr0x.bullcow.form.auth;

/**
 * RegistrationForm encapsulates registration form.
 */
public final class RegistrationForm {
    private final String userName;
    private final String password;
    private final String passwordRepeat;

    /**
     * Constructs a registration form.
     * @param userName the name of the user.
     * @param password the password of the user.
     * @param passwordRepeat the password of the user (repeated).
     */
    public RegistrationForm(final String userName, final String password,
                            final String passwordRepeat) {
        this.userName = userName;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

    /**
     * Get the contents of a username field from a form.
     * @return the username contained in this form.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the contents of a password field from a form.
     * @return the password contained in this form.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the contents of a password repeat field from a form.
     * @return the password repeat contained in this form.
     */
    public String getPasswordRepeat() {
        return passwordRepeat;
    }
}
