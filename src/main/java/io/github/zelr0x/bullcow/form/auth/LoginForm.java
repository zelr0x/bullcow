package io.github.zelr0x.bullcow.form.auth;

/**
 * LoginForm encapsulates login form.
 */
public final class LoginForm {
    private final String userName;
    private final String password;

    /**
     * Constructs a login form.
     * @param userName the name of the user
     * @param password the password of the user
     */
    public LoginForm(final String userName, final String password) {
        this.password = password;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
