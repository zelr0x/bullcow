package io.github.zelr0x.bullcow.model.game;

import java.io.Serializable;

/**
 * Encapsulates guess.
 */
public final class Guess implements Serializable {
    private static final long serialVersionUID = 9938476665L;

    private final String guess;

    /**
     * Constructs guess from a specified String.
     * @param guess a string hopefully containing digits of the guess
     */
    public Guess(final String guess) {
        this.guess = guess.trim();
    }

    /**
     * Returns String representation of a guess suitable for further
     * processing. Formatted without knowing of a game target length so
     * further formatting is required.
     * @return String representation of a guess
     */
    public String getGuess() {
        return guess;
    }
}
