package io.github.zelr0x.bullcow.controller.servlet;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeServlet handles requests to /home.
 */
@WebServlet(
        name = "HomeServlet",
        urlPatterns = {
                RouteStore.HOME,
                RouteStore.INDEX})
public class HomeServlet extends HttpServlet {
    /**
     * Forwards GET requests to the home page.
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
     * Forwards POST requests to the home page.
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
     * Forwards the requests to home page.
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
        request.getRequestDispatcher(PathStore.HOME_PAGE)
                .forward(request, response);
    }
}
