package io.github.zelr0x.bullcow.controller.servlet.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login", "/signin", "/login#login"})
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE_PATH = "/WEB-INF/jsp/login.jsp";

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher dispatcher =
                request.getRequestDispatcher(LOGIN_PAGE_PATH);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: impl login
    }
}
