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
public class UserService implements IUserService {
    private IUserDao userDao = new UserDao();

    @Override
    public boolean isUsernameAssignable(final String target) {
        return !userDao.getUser(target).isPresent();
    }

    @Override
    public Optional<User> getUser(final UserDto target) {
        final Optional<User> user = userDao.getUser(target.getName());
        if (user.isPresent()
                && BCrypt.checkpw(target.getPassword(), user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Long> addUser(final UserDto target) {
        final String hashed = BCrypt.hashpw(target.getPassword(), BCrypt.gensalt());
        final UserDto user = UserDto.of(target.getName(), hashed);
        return userDao.addUser(user);
    }
}
