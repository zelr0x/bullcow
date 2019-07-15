package io.github.zelr0x.bullcow.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * A User entity.
 */
@SuppressWarnings("checkstyle:all")
@Entity
@Table(name = "Users")
public final class User extends BaseEntity
        implements Comparable<User>, Serializable {
    // Currently, every user creation should create an associated player.
    // OneToOne parameter cascade = CascadeType.ALL is required when JPA methods
    // like session.persist() are used.
    // Hibernate's @Cascade is required when hibernate-specific methods
    // session.save(), session.update() or session.saveOrUpdate() are used
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @org.hibernate.annotations.Cascade(
            org.hibernate.annotations.CascadeType.ALL)
    private Player player;

    private String name;

    @com.google.gson.annotations.Expose(serialize = false)
    private String password;

    public User() {
    }

    public User(final String name, final String password) {
        this.name = name;
        this.password = password;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public int compareTo(final User o) {
        return Long.compare(getId(), o.getId());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(player, user.player) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, name, password);
    }
}
