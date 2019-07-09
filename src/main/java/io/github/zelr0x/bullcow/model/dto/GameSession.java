package io.github.zelr0x.bullcow.model.dto;

import io.github.zelr0x.bullcow.game.Game;
import io.github.zelr0x.bullcow.game.Guess;
import io.github.zelr0x.bullcow.game.GuessResult;
import io.github.zelr0x.bullcow.util.NumberParseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates game session info.
 */
public class GameSession implements Serializable {
    private static final long serialVersionUID = 1913374655L;

    private Game game;
    private int turn;
    private GuessInfo lastGuessInfo;
    private List<String> errors;

    public GameSession() {
        turn = 0;
        game = new Game();
        lastGuessInfo = null;
        errors = new ArrayList<>();
    }

    public void makeGuess(final String guessString) {
        if (guessString.equalsIgnoreCase(lastGuessInfo.getGuess())) {
            turn++;
            return;
        }

        errors.clear();
        final Guess guess = new Guess(guessString);
        final GuessResult result;
        try {
            result = game.checkGuess(guess);
        } catch (final NumberParseException e) {
            errors.add(e.getMessage());
            return;
        }
        lastGuessInfo = new GuessInfo(guess, result);
        turn++;
    }

    public int getLength() {
        return game.getTargetLength();
    }

    public int getTurn() {
        return turn;
    }

    public GuessInfo getLastGuessInfo() {
        return lastGuessInfo;
    }

    public List<String> getErrors() {
        return errors;
    }
}