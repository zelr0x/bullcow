package io.github.zelr0x.bullcow.model.dto;

import java.io.Serializable;

/**
 * StatDifference encapsulates the difference
 * in player statistics in a way suitable for transfer.
 */
public final class StatDifference implements Serializable {
    private static final long serialVersionUID = 24561931L;

    private int gamesPlayed;
    private int guessesMade;

    public static StatDifference of(final int gamesPlayed,
                                    final int guessesMade) {
        return new StatDifference(gamesPlayed, guessesMade);
    }

    private StatDifference(final int gamesPlayed, final int guessesMade) {
        this.gamesPlayed = gamesPlayed;
        this.guessesMade = guessesMade;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGuessesMade() {
        return guessesMade;
    }
}
