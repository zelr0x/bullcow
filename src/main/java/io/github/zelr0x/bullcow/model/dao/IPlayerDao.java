package io.github.zelr0x.bullcow.model.dao;

import io.github.zelr0x.bullcow.model.dto.PlayerDto;
import io.github.zelr0x.bullcow.model.dto.StatDifference;

import java.util.List;
import java.util.Optional;

/**
 * IPlayerDao specifies contracts for Player entity.
 */
public interface IPlayerDao {
    /**
     * Retrieves players from the DB ordered by their respective
     * average guess fields starting from the least guesses before win
     * (best result) to the most guesses before win.
     *
     * @param firstInclusive one-based index of the first player (inclusive).
     * @param lastExclusive one-based index of the last player (exclusive).
     * @param minGames the minimum number of games the player should have
     *                 to be returned by the underlying query.
     * @return list of players within a specified range.
     */
    Optional<List<PlayerDto>> getOrdered(
            int firstInclusive, int lastExclusive, int minGames);

    /**
     * Saves or updates stat difference for the player with the specified id.
     * It always adds stat difference.
     *
     * @param playerId the id of the target player.
     * @param statDifference the difference in stats of that player.
     */
    void update(Long playerId, StatDifference statDifference);
}
