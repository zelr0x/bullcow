package io.github.zelr0x.bullcow.model.dao;

import io.github.zelr0x.bullcow.model.Player;
import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.StatDifference;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

/**
 * Encapsulates DB access for Player entity.
 */
public final class PlayerDao implements IPlayerDao {
    /**
     * Retrieves players from the DB.
     * @param firstInclusive one-based index of the first player (inclusive)
     * @param lastExclusive one-based index of the last player (exclusive)
     * @return list of players within a specified range
     */
    @Override
    public Optional<List<? extends Player>> getRanked(
            final int firstInclusive, final int lastExclusive,
            final int minGames) {
        final int amount = lastExclusive - firstInclusive;
        List<Player> players;
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
                        .getResultList();
            } catch (NoResultException nre) {
                return Optional.empty();
            }
        }
        return Optional.of(players);
    }

    @Override
    public Player update(
            final Long userId, final StatDifference statDifference) {
        Player player;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            final User user = session.load(User.class, userId);
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
        return player;
    }
}
