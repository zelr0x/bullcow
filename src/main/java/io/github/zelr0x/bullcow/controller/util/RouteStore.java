package io.github.zelr0x.bullcow.controller.util;

import io.github.zelr0x.bullcow.util.CollectionUtil;

import java.util.Set;

/**
 * RouteStore contains app routes and immutable sets
 * used for whitelist-filtering of requests.
 */
public final class RouteStore {
    private static final String ROOT = "/";

    private static final String API_ROOT = "/api/";
    public static final String API_PUB_ROOT = API_ROOT + "pub/";

    public static final String HOME = "/home";
    public static final String INDEX = "/index";

    public static final String RATING = "/rating";
    public static final String RANKINGS = "/rankings";

    public static final String GAME = "/game";
    public static final String PLAY = "/play";

    public static final String LOGIN = "/login";
    public static final String SIGN_IN = "/signin";
    public static final String REGISTER = "/register";
    public static final String SIGN_UP = "/signup";
    public static final String LOGOUT = "/logout";
    public static final String LOGGED_IN_HOME = GAME;

    public static final String REGISTRATION_FORM = LOGIN + "#signup";
    public static final String LOGIN_FORM = LOGIN + "#login";

    /**
     * Specifies route starts that don't require authentication.
     * Should not include webapp root ("/"), otherwise filtering will break.
     */
    public static final Set<String> NO_AUTH_ROUTE_STARTS =
            CollectionUtil.immutableSetOf(
                    RATING,
                    RANKINGS,
                    LOGIN,
                    REGISTER,
                    SIGN_IN,
                    SIGN_UP);

    /**
     * Specifies route endpoints that don't require authentication.
     */
    public static final Set<String> NO_AUTH_ENDPOINTS =
            CollectionUtil.immutableSetOf(
                    ROOT,
                    LOGOUT,
                    HOME,
                    INDEX);

    /**
     * Prevents instantiation.
     */
    private RouteStore() {
        throw new AssertionError();
    }
}
