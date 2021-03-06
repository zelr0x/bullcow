package io.github.zelr0x.bullcow.controller.servlet;

import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.service.IPlayerService;

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
        urlPatterns = {
                RouteStore.RATING,
                RouteStore.RANKINGS})
public class RatingServlet extends HttpServlet {
    private static final String RATING_PAGE = "/WEB-INF/jsp/rating.jsp";
    private static final String JSON_ARR_NAME = "players";

    /**
     * Forwards GET requests to an according JSP page.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Forwards POST requests to an according JSP page.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Forwards the requests to an according JSP page.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     * @throws ServletException if the request could not be handled.
     */
    private void process(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(JSON_ARR_NAME, IPlayerService.getPlayersJson());
        request.getRequestDispatcher(RATING_PAGE)
                .forward(request, response);
    }
}
