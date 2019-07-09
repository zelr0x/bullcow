package io.github.zelr0x.bullcow.game;

import io.github.zelr0x.bullcow.game.exception.LongGuessException;
import io.github.zelr0x.bullcow.game.exception.NonNumericGuessException;
import io.github.zelr0x.bullcow.util.NumberParseException;
import io.github.zelr0x.bullcow.util.NumericUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Bulls and cows game.
 * Doesn't handle duplicate letters in a target word (by specification).
 */
public final class Game {

    static final int DEFAULT_LENGTH = 4;

    private static final int MAX_LENGTH = 10;
    private static final int MIN_LENGTH = DEFAULT_LENGTH;

    private final int targetLength;
    private final int[] targetDigits;

    /**
     * Evaluates guess. Each bull represents a correctly positioned
     * and correctly guessed digit. Each cow represents an incorrectly
     * positioned digit that is present in the target number.
     * @param guess a Guess
     * @return GuessResult object containing the evaluation of this guess
     * @throws NumberParseException (one of its subclasses)
     * if guess is of incorrect size or contains malformed data
     */
    public GuessResult checkGuess(final Guess guess)
            throws NumberParseException {
        final String guessString = guess.getGuess();
        if (guessString.length() > targetLength) {
            throw new LongGuessException(targetLength);
        }

        try {
            final int[] digits = NumericUtils.prefixNormalize(
                    NumericUtils.getDigits(guessString),
                    targetLength);
            return checkGuess(digits);
        } catch (final NumberParseException e) {
            throw new NonNumericGuessException();
        }
    }

    /**
     * {@link #checkGuess(Guess)} helper method.
     * Suitable for testing and required for production.
     * @param guess the digits of the guess
     * @return GuessResult object containing the evaluation of this guess
     */
    GuessResult checkGuess(final int[] guess) {
        int bulls = 0;
        final Set<Integer> cowCandidates = new HashSet<>(targetLength);
        for (int i = 0; i < targetLength; i++) {
            if (targetDigits[i] == guess[i]) {
                bulls++;
            } else {
                cowCandidates.add(targetDigits[i]);
            }
        }
        final int cows = (int) Arrays.stream(guess)
                .distinct()
                .filter(cowCandidates::contains)
                .count();
        return new GuessResult.Builder(targetLength)
                .bulls(bulls)
                .cows(cows)
                .build();
    }

    /**
     * Returns the length of the target number of this game instance.
     * @return the length of the target number
     */
    public int getTargetLength() {
        return targetLength;
    }

    /**
     * Starts a game session for a specified target number length.
     * @param targetLength an amount of digits in a target number
     */
    public Game(final int targetLength) {
        this.targetLength = (targetLength <= MAX_LENGTH
                            && targetLength >= MIN_LENGTH)
                ? targetLength
                : DEFAULT_LENGTH;
        this.targetDigits =
                NumericUtils.generateDistinctDigitNumber(targetLength);
    }

    /**
     * Starts a game session with default parameters of a target number.
     */
    public Game() {
        this(DEFAULT_LENGTH);
    }

    /**
     * Starts a game session for a specified target number.
     * Made for testing purposes.
     * @param target the digits of a target number
     */
    Game(final int[] target) {
        this(target.length);
        System.arraycopy(target, 0, this.targetDigits, 0, target.length);
    }

    /**
     * Returns the digits of a target number.
     * Made for testing purposes.
     * @return the digits of a target number
     */
    int[] getTargetDigits() {
        return targetDigits;
    }
}
