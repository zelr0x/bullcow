package io.github.zelr0x.bullcow.service;

import io.github.zelr0x.bullcow.model.Player;
import io.github.zelr0x.bullcow.model.dao.IPlayerDao;
import io.github.zelr0x.bullcow.model.dao.PlayerDao;
import io.github.zelr0x.bullcow.model.dto.PlayerDto;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Player related service interface implementation.
 */
public class PlayerService implements IPlayerService {
    private IPlayerDao playerDao = new PlayerDao();

    /**
     * Returns users within a specified range.
     * @param firstInclusive one-based index of the first player (inclusive)
     * @param lastExclusive one-based index of the last player (exclusive)
     * @return list of player within a specified range
     */
    @Override
    public Optional<List<? extends PlayerDto>> getRanked(
            final int firstInclusive, final int lastExclusive) {
        final Optional<List<? extends Player>> players =
                playerDao.getRanked(
                        firstInclusive,
                        lastExclusive,
                        IPlayerService.MIN_GAMES);
        return players.map(list -> list.stream()
                .map(PlayerDto::of)
                .collect(Collectors.toList()));
    }

    @Override
    public Player updateStatistics(
            final Long userId, final StatDifference statDifference) {
        return playerDao.update(userId, statDifference);
    }
}
