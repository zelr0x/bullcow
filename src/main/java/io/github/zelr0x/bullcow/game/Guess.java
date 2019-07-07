package io.github.zelr0x.bullcow.game;

import io.github.zelr0x.bullcow.util.NumberParseException;
import io.github.zelr0x.bullcow.util.NumericUtils;

import java.io.Serializable;
import java.util.Optional;

public final class Guess implements Serializable {
    private static final long serialVersionUID = 9938476665L;

    private final String guess;
    private int[] digits;
    private boolean checked = true;

    public Guess(final String guess) {
        this.guess = guess.trim();
    }

    public String getGuess() {
        return guess;
    }

    public boolean isValid() {
        if (!checked) {
            checked = true;
            try {
                digits = NumericUtils.getDigits(guess);
            } catch (final NumberParseException e) {
                digits = null;
            }
        }
        return digits != null;
    }

    public Optional<int[]> getDigits() {
        return isValid() ? Optional.of(digits) : Optional.empty();
    }
}
