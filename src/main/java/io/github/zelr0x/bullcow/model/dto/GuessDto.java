package io.github.zelr0x.bullcow.model.dto;

import io.github.zelr0x.bullcow.model.game.Guess;
import io.github.zelr0x.bullcow.model.game.GuessResult;

import java.io.Serializable;

/**
 * GuessDto encapsulates guess information in a way suitable for transfer.
 */
public final class GuessDto implements Serializable {
    private static final long serialVersionUID = 312130008L;

    private final int turn;
    private final String guess;
    private final String result;
    private final String error;
    private final boolean isWinning;

    public static GuessDto of(final int turn, final Guess guess,
                              final GuessResult result, final String error) {
        return new GuessDto(turn, guess, result, error);
    }

    static GuessDto ofEmpty(final int turn,
                            final Guess guess,
                            final String error) {
        return new GuessDto(turn, guess, GuessResult.ofEmpty(), error);
    }

    public int getTurn() {
        return turn;
    }

    public String getGuess() {
        return guess;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public boolean isWinning() {
        return isWinning;
    }

    private GuessDto(final int turn, final Guess guess,
                     final GuessResult result, final String error) {
        this.turn = turn;
        this.guess = guess.getGuess();
        this.result = result.toString();
        this.isWinning = result.isWinning();
        this.error = error;
    }
}
