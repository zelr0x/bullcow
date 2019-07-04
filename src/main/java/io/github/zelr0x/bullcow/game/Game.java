package io.github.zelr0x.bullcow.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.github.zelr0x.bullcow.game.NumericUtils.generateDistinctDigitNumber;
import static io.github.zelr0x.bullcow.game.NumericUtils.getDigits;
import static io.github.zelr0x.bullcow.game.NumericUtils.prefixNormalize;

/**
 * Bulls and cows game.
 * Doesn't handle duplicate letters in a target word (by specification).
 */
public final class Game {
    private static final int MAX_LENGTH = 10;
    private static final int DEFAULT_LENGTH = 4;

    private final int targetLength;
    private final int[] targetDigits;

    /**
     * Returns the digits of a target number.
     * @return the digits of a target number
     */
    public int[] getTargetDigits() {
        return targetDigits;
    }

    /**
     * Evaluates guess. Each bull represents a correctly positioned
     * and correctly guessed digit. Each cow represents an incorrectly
     * positioned digit that is present in the target number.
     * @param guess a string representation of a guess
     * @return GuessResult object containing the evaluation of this guess
     * @throws IllegalArgumentException if guess is of incorrect size
     * or contains non-numeric data
     */
    public GuessResult checkGuess(final String guess)
            throws IllegalArgumentException {
        final int[] digits = prefixNormalize(getDigits(guess), targetLength);
        int bulls = 0;
        final Set<Integer> cowCandidates = new HashSet<>(targetLength);
        for (int i = 0; i < targetLength; i++) {
            if (targetDigits[i] == digits[i]) {
                bulls++;
            } else {
                cowCandidates.add(targetDigits[i]);
            }
        }
        final int cows = (int) Arrays.stream(digits)
                .distinct()
                .filter(cowCandidates::contains)
                .count();
        return new GuessResult.Builder(targetLength)
                .bulls(bulls)
                .cows(cows)
                .build();
    }

    /**
     * Starts a game session for a specified target number length.
     * @param targetLength an amount of digits in a target number
     */
    public Game(final int targetLength) {
        this.targetLength = targetLength <= MAX_LENGTH
                ? targetLength
                : DEFAULT_LENGTH;
        this.targetDigits = generateDistinctDigitNumber(targetLength);
    }

    /**
     * Starts a game session with default parameters of a target number.
     */
    public Game() {
        this(DEFAULT_LENGTH);
    }

    /**
     * Starts a game session for a specified target number.
     * @param target the digits of a target number
     */
    public Game(final int[] target) {
        this(target.length);
        System.arraycopy(target, 0, this.targetDigits, 0, target.length);
    }
}
