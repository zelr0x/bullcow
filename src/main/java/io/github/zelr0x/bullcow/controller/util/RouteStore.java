package io.github.zelr0x.bullcow.controller.util;

import io.github.zelr0x.bullcow.util.CollectionUtil;

import java.util.Collections;
import java.util.Set;

/**
 * This class specifies all app routes and contains immutable sets
 * used for whitelist-filtering of each request.
 */
public final class RouteStore {
    public static final String ROOT = "/";
    public static final String STATIC_ROOT = "/static/";
    public static final String API_ROOT = "/api/";
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

    public static final String REGISTRATION_FORM = LOGIN + "#signup";
    public static final String LOGIN_FORM = LOGIN + "#login";

    /**
     * Specifies route starts that don't require authentication.
     * Should not include webapp root ("/"), otherwise filtering will break.
     */
    public static final Set<String> NO_AUTH_ROUTE_STARTS =
            CollectionUtil.immutableSetOf(
                    STATIC_ROOT,
                    RATING,
                    RANKINGS,
                    API_ROOT,
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
     * Specifies route starts that require authentication.
     * Helps performing redundant checks.
     */
    private static final Set<String> AUTH_REQUIRED_ROUTE_STARTS =
            CollectionUtil.immutableSetOf(
                    GAME,
                    PLAY);

    /**
     * Specifies route endpoints that require authentication.
     * Helps performing redundant checks.
     */
    private static final Set<String> AUTH_REQUIRED_ENDPOINTS =
            CollectionUtil.immutableSetOf();

    static {
        assert Collections.disjoint(NO_AUTH_ROUTE_STARTS, NO_AUTH_ENDPOINTS);
        assert Collections.disjoint(NO_AUTH_ROUTE_STARTS, AUTH_REQUIRED_ENDPOINTS);
        assert Collections.disjoint(NO_AUTH_ROUTE_STARTS, AUTH_REQUIRED_ROUTE_STARTS);
        assert Collections.disjoint(NO_AUTH_ENDPOINTS, AUTH_REQUIRED_ENDPOINTS);
        assert Collections.disjoint(NO_AUTH_ENDPOINTS, AUTH_REQUIRED_ROUTE_STARTS);
        assert Collections.disjoint(AUTH_REQUIRED_ENDPOINTS, AUTH_REQUIRED_ROUTE_STARTS);
    }

    private RouteStore() {
        throw new AssertionError();
    }
}
