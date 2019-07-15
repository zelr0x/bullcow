package io.github.zelr0x.bullcow.controller.servlet.api.pub;

import io.github.zelr0x.bullcow.controller.util.RouteStore;
import io.github.zelr0x.bullcow.service.IPlayerService;
import io.github.zelr0x.bullcow.util.NumericUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * GetRatingServlet controls an api endpoint.
 * It is designed to return JSON object containing
 * only one property - an array of Player objects.
 */
@WebServlet(
        name = "GetRatingServlet",
        urlPatterns = {
                RouteStore.API_PUB_ROOT + "get/rating",
                RouteStore.API_PUB_ROOT + "get/rankings"})
public class GetRatingServlet extends HttpServlet {
    /**
     * Returns application/json of Player objects.
     *
     * @param request an HttpServletRequest object that contains
     *                the request the client has made of the servlet.
     * @param response an HttpServletResponse object that contains
     *                 the response the servlet sends to the client.
     * @throws IOException if an I/O error is detected when handling the request.
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws IOException {
        final String firstParam = request.getParameter("first");
        final String lastParam = request.getParameter("last");
        final Optional<Integer> first = NumericUtils.parseInt(firstParam);
        final Optional<Integer> last = NumericUtils.parseInt(lastParam);
        response.setContentType("application/json;charset=UTF-8");
        // Safe since getPlayersJson expects Optionals.
        response.getOutputStream()
                .print(IPlayerService.getPlayersJson(first, last));
    }
}
