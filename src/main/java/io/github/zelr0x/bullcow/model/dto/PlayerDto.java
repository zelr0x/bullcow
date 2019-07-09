package io.github.zelr0x.bullcow.model.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates Player entity in a way suitable for transfer.
 */
public final class PlayerDto implements Serializable {
    private static final long serialVersionUID = 2913374135L;

    private final long rank;
    private final String name;
    private final int totalGames;
    private final double averageGuesses;

    /**
     * Constructs a player.
     * @param rank the rank of the player
     * @param name the name of the player
     * @param totalGuesses total number of guesses made
     * @param totalGames total number of games played
     */
    public PlayerDto(final long rank, final String name,
                     final int totalGames, final long totalGuesses) {
        this.rank = rank;
        this.name = name;
        this.totalGames = totalGames;
        this.averageGuesses = totalGuesses / totalGames;
    }

    /**
     * Returns the rank of the player.
     * @return the rank of the player
     */
    public long getRank() {
        return rank;
    }

    /**
     * Returns the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the total number of games of the player.
     * @return the total number of games of the player
     */
    public int getTotalGames() {
        return totalGames;
    }

    /**
     * Returns the average number of guesses made before winning.
     * @return the average number of guesses made before winning
     */
    public double getAverageGuesses() {
        return averageGuesses;
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerDto player = (PlayerDto) o;
        return rank == player.rank
                && totalGames == player.totalGames
                && Double.compare(player.averageGuesses, averageGuesses) == 0
                && Objects.equals(name, player.name);
    }

    @SuppressWarnings("checkstyle:JavadocType")
    @Override
    public int hashCode() {
        return Objects.hash(rank, name, totalGames, averageGuesses);
    }
}
