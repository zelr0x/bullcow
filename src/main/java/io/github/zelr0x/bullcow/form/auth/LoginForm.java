package io.github.zelr0x.bullcow.form.auth;

/**
 * LoginForm encapsulates login form.
 */
public final class LoginForm {
    private final String userName;
    private final String password;

    /**
     * Constructs a login form.
     *
     * @param userName the name of the user.
     * @param password the password of the user.
     */
    public LoginForm(final String userName, final String password) {
        this.password = password;
        this.userName = userName;
    }

    /**
     * Get the contents of a password field from a form.
     *
     * @return the password contained in this form.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the contents of a username field from a form.
     * @return the username contained in this form.
     */
    public String getUserName() {
        return userName;
    }
}
