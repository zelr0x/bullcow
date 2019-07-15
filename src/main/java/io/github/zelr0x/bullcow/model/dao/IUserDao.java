package io.github.zelr0x.bullcow.model.dao;

import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;

import java.util.Optional;

/**
 * IUserDao specifies contacts for User entity.
 */
public interface IUserDao {
    /**
     * Tries to retrieve a User with a specified name from a DB.
     *
     * @param name the name of the target user.
     * @return a User object or nothing.
     */
    Optional<User> getUser(String name);

    /**
     * Tries to add a user to a DB.
     *
     * @param target a UserDto object containing the information
     *               about the new user.
     * @return a Long object containing the ID of a freshly created user
     * or nothing if user creation was not successful.
     */
    Optional<Long> addUser(UserDto target);
}
