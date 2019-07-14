package io.github.zelr0x.bullcow.model.dto;

import io.github.zelr0x.bullcow.model.User;

import java.io.Serializable;

/**
 * Encapsulates User entity in a way suitable for transfer.
 */
public final class UserDto implements Serializable {
    private static final long serialVersionUID = 112117064328L;

    private String name;
    private String password;

    public static UserDto of(final String name, final String password) {
        return new UserDto(name, password);
    }

    public static UserDto of(final User user) {
        return new UserDto(user);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private UserDto(final User user) {
        this(user.getName(), user.getPassword());
    }

    private UserDto(final String name, final String password) {
        this.name = name;
        this.password = password;
    }
}
