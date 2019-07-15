package io.github.zelr0x.bullcow.controller.auth;

/**
 * AuthParamStore stores names of request parameters
 * related to authentication.
 */
final class AuthParamStore {
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String PASSWORD_REPEAT = "password_repeat";
    static final String ERROR = "error";

    /**
     * Prevents instantiation.
     */
    private AuthParamStore() {
        throw new AssertionError();
    }
}
