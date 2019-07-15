package io.github.zelr0x.bullcow.service;

import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;

import java.util.Optional;

/**
 * User related service interface.
 */
public interface IUserService {
    /**
     * Checks if a specified name is free to use.
     *
     * @param username a username to check.
     * @return true if the username is free to use, false otherwise.
     */
    boolean isUsernameAssignable(String username);

    /**
     * Tries to retrieve a User with a specified name from a DB.
     *
     * @param target a UserDto object containing info about the target user.
     * @return a User object or nothing.
     */
    Optional<User> getUser(UserDto target);

    /**
     * Tries to add a user to a DB.
     *
     * @param target a UserDto object containing info about the target user.
     * @return a Long object containing the ID of a freshly created user
     * or nothing if user creation was not successful.
     */
    Optional<Long> addUser(UserDto target);
}
