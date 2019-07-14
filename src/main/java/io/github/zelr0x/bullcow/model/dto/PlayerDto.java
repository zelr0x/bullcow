package io.github.zelr0x.bullcow.model.dto;

import io.github.zelr0x.bullcow.model.Player;

import java.io.Serializable;

/**
 * Encapsulates Player entity in a way suitable for transfer.
 */
public final class PlayerDto implements Serializable {
    private static final long serialVersionUID = 5223008L;

    private long rank;
    private String name;
    private int totalGames;
    private double averageGuesses;

    public static PlayerDto of(final long rank,
                               final String name,
                               final int totalGames,
                               final double averageGuesses) {
        return new PlayerDto(rank, name, totalGames, averageGuesses);
    }

    public static PlayerDto of(final Player player) {
        return new PlayerDto(player);
    }

    private PlayerDto(final long rank, final String name,
                      final int totalGames, final double averageGuesses) {
        this.rank = rank;
        this.name = name;
        this.totalGames = totalGames;
        this.averageGuesses = averageGuesses;
    }

    private PlayerDto(final Player player) {
        this(player.getRank(),
                player.getName(),
                player.getTotalGames(),
                player.getAverageGuesses());
    }

    public long getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public double getAverageGuesses() {
        return averageGuesses;
    }
}
