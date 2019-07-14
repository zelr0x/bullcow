package io.github.zelr0x.bullcow.model.dao;


import io.github.zelr0x.bullcow.model.Player;
import io.github.zelr0x.bullcow.model.dto.StatDifference;

import java.util.List;
import java.util.Optional;

/**
 * Contacts for Player entity.
 */
public interface IPlayerDao {
    /**
     * Returns players within a specified range.
     * @param firstInclusive one-based index of the first player (inclusive)
     * @param lastExclusive one-based index of the last player (exclusive)
     * @return list of players within a specified range
     */
    Optional<List<? extends Player>> getRanked(
            final int firstInclusive, final int lastExclusive,
            final int minGames);

    Player update(
            final Long userId, final StatDifference statDifference);
}
