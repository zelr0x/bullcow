package io.github.zelr0x.bullcow.model.dto;

import java.io.Serializable;

/**
 * GuessInfo encapsulates guess information.
 */
public final class GuessInfo implements Serializable {
    private static final long serialVersionUID = 312130008L;
    private final int turn;
    private final String guess;
    private final String result;
    private final String error;

    public GuessInfo(final int turn, final String guess,
                     final String result, final String error) {
        this.turn = turn;
        this.guess = guess;
        this.result = result;
        this.error = error;
    }
}
