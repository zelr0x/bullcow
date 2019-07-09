package io.github.zelr0x.bullcow.controller.servlet.api.pub;

import io.github.zelr0x.bullcow.dto.PlayerDto;
import io.github.zelr0x.bullcow.service.UserService;
import io.github.zelr0x.bullcow.util.JsonSerializer;
import io.github.zelr0x.bullcow.util.NumericUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Api endpoint returning JSON of PlayerDto objects.
 */
@WebServlet(name = "GetRatingServlet",
            urlPatterns = "/api/pub/get/rating")
public class GetRatingServlet extends HttpServlet {
    /**
     * Returns application/json of PlayerDto objects
     * @param request a request
     * @param response a response
     * @throws IOException
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
        final ServletOutputStream out = response.getOutputStream();
        out.print(getPlayersJson(first, last));
    }

    /**
     * Returns a JSON array of players within a specified range
     * serialized to a JSON object.
     * @param first the index of the first player to return
     * @param last the index of the last player to return
     * @return a JSON array of players within a specified range
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static String getPlayersJson(final Optional<Integer> first,
                                        final Optional<Integer> last) {
        final List<PlayerDto> players = (first.isPresent() && last.isPresent())
                ? new UserService().getPlayers(first.get(), last.get())
                : new UserService().getPlayers();
        return JsonSerializer.serialize("players", players);
    }

    /**
     * Returns a JSON array of players within a default range
     * serialized to a JSON object.
     * @return a JSON array of players within a default range
     */
    public static String getPlayersJson() {
        return getPlayersJson(Optional.empty(), Optional.empty());
    }
}
