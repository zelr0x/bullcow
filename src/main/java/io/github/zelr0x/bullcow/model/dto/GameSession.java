package io.github.zelr0x.bullcow.model.dto;

import io.github.zelr0x.bullcow.model.game.Game;
import io.github.zelr0x.bullcow.model.game.Guess;
import io.github.zelr0x.bullcow.model.game.GuessResult;
import io.github.zelr0x.bullcow.util.NumberParseException;

import java.io.Serializable;

/**
 * GameSession encapsulates game session info in a way suitable for transfer.
 */
public final class GameSession implements Serializable {
    private static final long serialVersionUID = 145614581L;

    private static final transient boolean REPEAT_AS_NEW = false;

    @com.google.gson.annotations.Expose(serialize = false)
    private Game game;

    private int turn;
    private GuessDto lastGuess;
    private String error;

    public GameSession() {
        this(new Game(), 0, null, "");
    }

    /**
     * Creates debug game session with target number always set to 1234.
     *
     * @return a debug game session with target number always set to 1234.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static GameSession forDebugging() {
        return new GameSession(new Game(new int[]{1, 2, 3, 4}));
    }

    public GuessDto makeGuess(final String guessString) {
        if (lastGuess != null
                && lastGuess.getGuess().equalsIgnoreCase(guessString)) {
            if (REPEAT_AS_NEW) turn++;
            return lastGuess;
        }

        turn++;
        final Guess guess = new Guess(guessString);
        try {
            final GuessResult result = game.checkGuess(guess);
            lastGuess = GuessDto.of(turn, guess, result, "");
        } catch (final NumberParseException e) {
            error = e.getMessage();
            lastGuess = GuessDto.ofEmpty(turn, guess, error);
       }
        return lastGuess;
    }

    public int getLength() {
        return game.getTargetLength();
    }

    public Game getGame() {
        return game;
    }

    public int getTurn() {
        return turn;
    }

    public GuessDto getLastGuess() {
        return lastGuess;
    }

    public String getError() {
        return error;
    }

    public boolean isFinished() {
        return lastGuess.isWinning();
    }

    private GameSession(final Game game, final int turn,
                        final GuessDto lastGuess, final String error) {
        this.game = game;
        this.turn = turn;
        this.lastGuess = lastGuess;
        this.error = error;
    }

    private GameSession(final Game game) {
        this(game, 0, null, "");
    }
}
