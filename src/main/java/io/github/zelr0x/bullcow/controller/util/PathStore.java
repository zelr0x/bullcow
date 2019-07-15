package io.github.zelr0x.bullcow.controller.util;

/**
 * PathStore stores various paths used throughout the application.
 */
public final class PathStore {
    private static final String JSP_ROOT = "/WEB-INF/jsp";
    public static final String HOME_PAGE = "/index.jsp";

    public static final String GAME_PAGE = JSP_ROOT + "/game.jsp";

    public static final String LOGIN_PAGE = JSP_ROOT + "/login.jsp";

    public static final String ERROR_PAGE = JSP_ROOT + "/error.jsp";

    /**
     * Prevents instantiation.
     */
    private PathStore() {
        throw new AssertionError();
    }
}
