package io.github.zelr0x.bullcow.model.dao;

import io.github.zelr0x.bullcow.model.Player;
import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.PlayerDto;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * PlayerDao encapsulates DB access for Player entity.
 */
public final class PlayerDao implements IPlayerDao {
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
    @Override
    public Optional<List<PlayerDto>> getOrdered(
            final int firstInclusive, final int lastExclusive,
            final int minGames) {
        final int amount = lastExclusive - firstInclusive;
        List<PlayerDto> players;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            final String hql =
                    "FROM Player " +
                    "WHERE total_games > :min_games";
            try {
                players = session.createQuery(hql, Player.class)
                        .setParameter("min_games", minGames)
                        .setFirstResult(firstInclusive)
                        .setMaxResults(amount)
                        .stream()
                        .sorted(Comparator.comparingDouble(Player::getAverageGuesses))
                        .map(PlayerDto::of)
                        .collect(Collectors.toList());
            } catch (NoResultException nre) {
                return Optional.empty();
            }
        }
        return Optional.of(players);
    }

    /**
     * Saves or updates stat difference for the player with the specified id.
     * It always adds stat difference.
     *
     * @param playerId the id of the target player.
     * @param statDifference the difference in stats of that player.
     */
    @Override
    public void update(
            final Long playerId, final StatDifference statDifference) {
        Player player;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            final User user = session.load(User.class, playerId);
            player = user.getPlayer();
            if (player != null) {
                player.setTotalGames(player.getTotalGames()
                        + statDifference.getGamesPlayed());
                player.setTotalGuesses(player.getTotalGuesses()
                        + statDifference.getGuessesMade());
            } else {
                player = new Player(
                        user,
                        statDifference.getGamesPlayed(),
                        statDifference.getGuessesMade());
                user.setPlayer(player);
            }
            session.saveOrUpdate(player);
            session.getTransaction().commit();
        }
    }
}
