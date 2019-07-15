package io.github.zelr0x.bullcow.service;

import io.github.zelr0x.bullcow.model.dao.IPlayerDao;
import io.github.zelr0x.bullcow.model.dao.PlayerDao;
import io.github.zelr0x.bullcow.model.dto.PlayerDto;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import java.util.List;
import java.util.Optional;

/**
 * Player related service interface implementation.
 */
public final class PlayerService implements IPlayerService {
    private IPlayerDao playerDao = new PlayerDao();

    /**
     * Returns users within a specified range.
     *
     * @param firstInclusive one-based index of the first player (inclusive).
     * @param lastExclusive one-based index of the last player (exclusive).
     * @return list of player within a specified range.
     */
    @Override
    public Optional<List<? extends PlayerDto>> getRanked(
            final int firstInclusive, final int lastExclusive) {
        long rank = firstInclusive + 1;
        final Optional<List<PlayerDto>> playersOpt = playerDao.getOrdered(
                firstInclusive,
                lastExclusive,
                IPlayerService.MIN_GAMES);
        if (playersOpt.isPresent()) {
            final List<PlayerDto> players = playersOpt.get();
            for (final PlayerDto player : players) {
                player.setRank(rank++);
            }
            return Optional.of(players);
        }
        return Optional.empty();
    }

    /**
     * Saves or updates stat difference for the player with the specified id.
     * It always adds stat difference.
     *
     * @param playerId the id of the target player.
     * @param statDifference the difference in stats of that player.
     */
    @Override
    public void updateStatistics(
            final Long playerId, final StatDifference statDifference) {
        playerDao.update(playerId, statDifference);
    }
}
