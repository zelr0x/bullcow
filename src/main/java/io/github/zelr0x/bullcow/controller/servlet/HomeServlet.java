package io.github.zelr0x.bullcow.controller.servlet;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "HomeServlet",
        urlPatterns = {
                RouteStore.HOME,
                RouteStore.INDEX})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(PathStore.HOME_PAGE)
                .forward(request, response);
    }
}
