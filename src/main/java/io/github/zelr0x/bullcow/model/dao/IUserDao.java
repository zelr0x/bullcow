package io.github.zelr0x.bullcow.model.dao;

import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;

import java.util.Optional;

/**
 * Contacts for User entity.
 */
public interface IUserDao {
    Optional<User> getUser(String name);
    Optional<Long> addUser(UserDto target);
}
