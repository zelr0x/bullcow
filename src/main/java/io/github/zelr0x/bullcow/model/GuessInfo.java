package io.github.zelr0x.bullcow.model;

import io.github.zelr0x.bullcow.game.Guess;
import io.github.zelr0x.bullcow.game.GuessResult;

import java.io.Serializable;

/**
 * GuessInfo encapsulates guess information.
 */
public final class GuessInfo implements Serializable {
    private static final long serialVersionUID = 1928374655L;

    private final Guess guess;
    private final GuessResult result;

    /**
     * Constructs a guess object.
     * @param guess the digits of the guess
     * @param result the result of the guess
     */
    public GuessInfo(final Guess guess, final GuessResult result) {
        this.guess = guess;
        this.result = result;
    }

    /**
     * Returns a string representation of the guess.
     * @return the guess as a String
     */
    public String getGuess() {
        return guess.getGuess();
    }

    /**
     * Returns the result of the guess.
     * @return the result of the guess
     */
    public GuessResult getResult() {
        return result;
    }
}
