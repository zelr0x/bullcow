package io.github.zelr0x.bullcow.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates Player entity in a way suitable for transfer.
 */
public final class PlayerDto implements Serializable {
    private static final long serialVersionUID = 2913374135L;

    private long rank;
    private String name;
    private int totalGames;
    private double averageGuesses;

    /**
     * Constructs a player.
     * @param rank the rank of the player
     * @param name the name of the player
     * @param averageGuesses average number of guesses made before winning
     * @param totalGames total number of games played
     */
    public PlayerDto(final long rank, final String name,
                     final double averageGuesses, final int totalGames) {
        this.rank = rank;
        this.name = name;
        this.averageGuesses = averageGuesses;
        this.totalGames = totalGames;
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
