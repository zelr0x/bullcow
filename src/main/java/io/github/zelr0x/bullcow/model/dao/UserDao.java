package io.github.zelr0x.bullcow.model.dao;

import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.Optional;

/**
 * UserDao encapsulates DB access for User entity.
 */
public final class UserDao implements IUserDao {
    /**
     * Tries to retrieve a User with a specified name from a DB.
     *
     * @param name the name of the target user.
     * @return a User object or nothing.
     */
    @Override
    public Optional<User> getUser(final String name) {
        User user;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            final String hql = "FROM User WHERE name = :name";
            try {
                user = session.createQuery(hql, User.class)
                        .setParameter("name", name)
                        .getSingleResult();
            } catch (NoResultException nre) {
                return Optional.empty();
            }
        }
        return Optional.of(user);
    }

    /**
     * Tries to add a user to a DB.
     *
     * @param target a UserDto object containing the information
     *               about the new user.
     * @return a Long object containing the ID of a freshly created user
     * or nothing if user creation was not successful.
     */
    @Override
    public Optional<Long> addUser(final UserDto target) {
        Transaction tx = null;
        Long userId = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            tx = session.beginTransaction();
            final User user = new User(
                    target.getName(),
                    String.valueOf(target.getPassword())
            );
            userId = (Long) session.save(user);
            tx.commit();
        } catch (final HibernateException e) {
            if (tx != null) tx.rollback();
        }
        return userId != null ? Optional.of(userId) : Optional.empty();
    }
}
