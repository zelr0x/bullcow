package io.github.zelr0x.bullcow.controller.auth;

final class AuthParamStore {
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String PASSWORD_REPEAT = "password_repeat";
    static final String ERROR = "error";

    private AuthParamStore() {
        throw new AssertionError();
    }
}
