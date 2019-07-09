package io.github.zelr0x.bullcow.controller.servlet;

import io.github.zelr0x.bullcow.controller.servlet.api.pub.GetRatingServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests to /rating.
 */
@WebServlet(
        name = "RatingServlet",
        urlPatterns = "/rating")
public class RatingServlet extends HttpServlet {
    private static final String RATING_PAGE_PATH = "/WEB-INF/jsp/rating.jsp";
    private static final String JSON_ARR_NAME = "players";

    /**
     * Forwards GET requests to an according JSP page.
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(JSON_ARR_NAME, GetRatingServlet.getPlayersJson());
        final RequestDispatcher dispatcher =
                request.getRequestDispatcher(RATING_PAGE_PATH);
        dispatcher.forward(request, response);
    }
}
