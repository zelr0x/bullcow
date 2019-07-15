package io.github.zelr0x.bullcow.service;

import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dao.IUserDao;
import io.github.zelr0x.bullcow.model.dao.UserDao;
import io.github.zelr0x.bullcow.model.dto.UserDto;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

/**
 * Player related service interface implementation.
 */
public final class UserService implements IUserService {
    private IUserDao userDao = new UserDao();

    /**
     * Checks if a specified name is free to use.
     * @param username a username to check.
     * @return true if the username is free to use, false otherwise.
     */
    @Override
    public boolean isUsernameAssignable(final String username) {
        return !userDao.getUser(username).isPresent();
    }

    /**
     * Tries to retrieve a User with a specified name from a DB.
     *
     * @param target a UserDto object containing info about the target user.
     * @return a User object or nothing.
     */
    @Override
    public Optional<User> getUser(final UserDto target) {
        final Optional<User> user = userDao.getUser(target.getName());
        if (user.isPresent()
                && BCrypt.checkpw(target.getPassword(), user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    /**
     * Tries to add a user to a DB.
     *
     * @param target a UserDto object containing info about the target user.
     * @return a Long object containing the ID of a freshly created user
     * or nothing if user creation was not successful.
     */
    @Override
    public Optional<Long> addUser(final UserDto target) {
        final String hashed = BCrypt.hashpw(target.getPassword(), BCrypt.gensalt());
        final UserDto user = UserDto.of(target.getName(), hashed);
        return userDao.addUser(user);
    }
}
