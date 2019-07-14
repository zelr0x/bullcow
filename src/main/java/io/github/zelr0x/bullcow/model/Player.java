package io.github.zelr0x.bullcow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * Player entity.
 */
@Entity
@Table(name = "Players")
public final class Player extends BaseEntity implements Comparable<Player> {
    @Id
    @GeneratedValue(generator = "playerKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "playerKeyGenerator",
            strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "property", value = "user"))
    private Long id;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    @Column(name = "total_games")
    private int totalGames;

    @Column(name = "total_guesses")
    private long totalGuesses;

    @org.hibernate.annotations.Formula(value = "total_games * 1.0 / total_guesses")
    @Column(name = "avg_guesses")
    private double averageGuesses;

    @Transient
//    @OneToOne(mappedBy = "player")
    @JoinColumn(name = "name", referencedColumnName = "name")
    private String name;

    @Transient
    @org.hibernate.annotations.Formula(value = "rank(averageGuesses)")
    private long rank;

    public Player(final User user, final int totalGames, final long totalGuesses) {
        this.user = user;
        this.totalGames = totalGames;
        this.totalGuesses = totalGuesses;
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

    public long getRank() {
        return rank;
    }

    public void setRank(final long rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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
                rank == player.rank &&
                Objects.equals(user, player.user) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, totalGames, totalGuesses, averageGuesses, name, rank);
    }
}
