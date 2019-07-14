package io.github.zelr0x.bullcow.service;

import io.github.zelr0x.bullcow.model.User;
import io.github.zelr0x.bullcow.model.dto.UserDto;

import java.util.Optional;

/**
 * User related service interface.
 */
public interface IUserService {
    boolean isUsernameAssignable(String target);

    Optional<User> getUser(UserDto target);

    Optional<Long> addUser(UserDto user);
}
