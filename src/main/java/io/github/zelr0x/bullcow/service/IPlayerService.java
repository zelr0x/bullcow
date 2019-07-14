package io.github.zelr0x.bullcow.service;

import io.github.zelr0x.bullcow.model.Player;
import io.github.zelr0x.bullcow.model.dto.PlayerDto;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import io.github.zelr0x.bullcow.util.JsonSerializer;

import java.util.List;
import java.util.Optional;

/**
 * Player related service interface.
 */
public interface IPlayerService {
    int DEFAULT_FIRST = 0;
    int DEFAULT_LAST = 25;
    int MIN_GAMES = -1; // Show users that have never played the game

    /**
     * Returns players within a specified range.
     * @param firstInclusive one-based index of the first player (inclusive)
     * @param lastExclusive one-based index of the last player (exclusive)
     * @return list of users within a specified range
     */
    Optional<List<? extends PlayerDto>> getRanked(
            int firstInclusive, int lastExclusive);

    /**
     * Returns 25 users.
     * @return list of 25 users
     */
    default Optional<List<? extends PlayerDto>> getRanked() {
        return getRanked(DEFAULT_FIRST, DEFAULT_LAST);
    }

    Player updateStatistics(final Long userId, final StatDifference statDifference);

    /**
     * Returns a JSON array of players within a specified range
     * serialized to a JSON object.
     * @param first the index of the first player to return
     * @param last the index of the last player to return
     * @return a JSON array of players within a specified range
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    static String getPlayersJson(final Optional<Integer> first,
                                 final Optional<Integer> last) {
        final Optional<List<? extends PlayerDto>> players =
                (first.isPresent() && last.isPresent())
                        ? new PlayerService().getRanked(first.get(), last.get())
                        : new PlayerService().getRanked();
        return players.isPresent()
                ? JsonSerializer.serialize("players", players.get())
                : "";
    }

    /**
     * Returns a JSON array of players within a default range
     * serialized to a JSON object.
     * @return a JSON array of players within a default range
     */
    static String getPlayersJson() {
        return getPlayersJson(Optional.empty(), Optional.empty());
    }
}
