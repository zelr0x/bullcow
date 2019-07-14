package io.github.zelr0x.bullcow.controller.servlet;

import io.github.zelr0x.bullcow.controller.util.PathStore;
import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.controller.util.SessionAttrStore;
import io.github.zelr0x.bullcow.form.GuessForm;
import io.github.zelr0x.bullcow.model.dto.GameSession;
import io.github.zelr0x.bullcow.model.dto.GuessDto;
import io.github.zelr0x.bullcow.util.JsonSerializer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "GameServlet",
        urlPatterns = {
                RouteStore.GAME,
                RouteStore.PLAY})
public class GameServlet extends HttpServlet {
    private static final String GUESS_PARAM = "guess";
    private static final boolean DEBUG = true;
    private static final String NEW_GAME_PARAM = "new";
    private static final String NEW_GAME_VALUE = "1";

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(PathStore.GAME_PAGE)
                .forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        final String guessParam = request.getParameter(GUESS_PARAM);
        if (guessParam == null) {
            final RequestDispatcher dispatcher =
                    request.getRequestDispatcher(PathStore.GAME_PAGE);
            final String newParam = request.getParameter(NEW_GAME_PARAM);
            if (newParam != null && newParam.equals(NEW_GAME_VALUE)) {
                final HttpSession httpSession = request.getSession();
                httpSession.removeAttribute(SessionAttrStore.GAME_SESSION);
                // GameSessionListener will do the rest from here.
            }

            dispatcher.forward(request, response);
            return;
        }

        final GuessForm form = new GuessForm(guessParam);
        // Not a bad place for guess validation

        // Create or retrieve GameSession
        final Object gameSessionObj = request.getSession()
                .getAttribute(SessionAttrStore.GAME_SESSION);
        final GameSession gameSession;
        if (gameSessionObj == null) {
            final GameSession newGameSession = DEBUG
                    ? GameSession.forDebugging()
                    : new GameSession();
            request.getSession().setAttribute(
                    SessionAttrStore.GAME_SESSION,
                    newGameSession);
            gameSession = newGameSession;
        } else {
            gameSession = (GameSession) gameSessionObj;
        }

        final GuessDto guessDto = gameSession.makeGuess(form.getGuess());
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream()
                .print(JsonSerializer.serialize(guessDto));
    }
}
