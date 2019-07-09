package io.github.zelr0x.bullcow.model.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates User entity in a way suitable for transfer.
 */
public class User implements Comparable<User>, Serializable {
    private static final long serialVersionUID = 2911231253L;

    private final Long id;
    private final String name;

    public User(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the name of the user.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(final User o) {
        return Long.compare(this.id, o.id);
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name);
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
