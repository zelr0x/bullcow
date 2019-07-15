package io.github.zelr0x.bullcow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * A Player entity.
 */
@SuppressWarnings("checkstyle:all")
@Entity
@Table(name = "Players")
public final class Player implements Comparable<Player> {
    @Id
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn
    @MapsId
    private User user;

    @Column(name = "total_games")
    private int totalGames;

    @Column(name = "total_guesses")
    private long totalGuesses;

    @Column(name = "avg_guesses")
    @org.hibernate.annotations.Formula(
            value = " ROUND(total_guesses * 1.0 / total_games, 2)")
    private double averageGuesses;

    @Transient
    private String name;

    public Player() {
    }

    public Player(final User user) {
        this.user = user;
    }

    public Player(final User user, final int totalGames, final long totalGuesses) {
        this.user = user;
        this.totalGames = totalGames;
        this.totalGuesses = totalGuesses;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public long getTotalGuesses() {
        return totalGuesses;
    }

    public void setTotalGuesses(final long totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(final int totalGames) {
        this.totalGames = totalGames;
    }

    public double getAverageGuesses() {
        return averageGuesses;
    }

    public void setAverageGuesses(final double averageGuesses) {
        this.averageGuesses = averageGuesses;
    }

    public String getName() {
        return user.getName();
    }

    @Override
    public int compareTo(final Player o) {
        return Double.compare(averageGuesses, o.averageGuesses);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return totalGames == player.totalGames &&
                totalGuesses == player.totalGuesses &&
                Double.compare(player.averageGuesses, averageGuesses) == 0 &&
                Objects.equals(user, player.user) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, totalGames, totalGuesses, averageGuesses, name);
    }
}
