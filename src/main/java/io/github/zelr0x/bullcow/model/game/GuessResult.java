package io.github.zelr0x.bullcow.model.game;

import java.io.Serializable;

/**
 * GuessResult encapsulates the result of a bulls and cows game guess.
 */
public final class GuessResult implements Serializable {
    private static final long serialVersionUID = 2938475665L;

    private final int bulls;
    private final int cows;
    private final boolean solved;

    /**
     * Returns an empty GuessResult (0 bulls, 0 cows, solved = false).
     * @return an empty GuessResult
     */
    public static GuessResult ofEmpty() {
        return EmptyGuessResultHolder.INSTANCE;
    }

    /**
     * Returns a number of "bulls" - correctly guessed digits.
     * @return a number of correctly guessed digits
     */
    int getBulls() {
        return bulls;
    }

    /**
     * Returns a number of "cows" - incorrectly positioned digits
     * present in a target number.
     * @return a number of incorrectly positioned digits
     * present in a target number
     */
    int getCows() {
        return cows;
    }

    /**
     * Checks if the guess is the winning one.
     * @return true if this is the winning guess, false otherwise
     */
    public boolean isWinning() {
        return solved;
    }

    /**
     * Returns String representation of a GuessResult.
     * Includes indication of all digits guessed correctly.
     * @return String representation of a GuessResult
     */
    @Override
    public String toString() {
        final String result = bulls + "B" + cows + "C";
        return solved
                ? result + ". You won!"
                : result;
    }

    /**
     * Builder builds GuessResult step by step.
     */
    static final class Builder {
        private final int total;
        private int bulls = 0;
        private int cows = 0;

        /**
         * Creates GuessResult.Builder for a specified total number of digits.
         * @param total a total number of digits
         */
        Builder(final int total) {
            this.total = total;
        }

        /**
         * Specifies an amount of digits guessed correctly.
         * @param setBulls an amount of digits guessed correctly
         * @return GuessResult.Builder
         */
        Builder bulls(final int setBulls) {
            this.bulls = setBulls;
            return this;
        }

        /**
         * Specifies an amount of digits guessed correctly.
         * @param setCows an amount of guessed digits that are present
         *                in a number but positioned incorrectly
         * @return GuessResult.Builder
         */
        Builder cows(final int setCows) {
            this.cows = setCows;
            return this;
        }

        /**
         * Creates a GuessResult object representing the result
         * of a given guess.
         * @return an result of a guess
         */
        GuessResult build() {
            return new GuessResult(this);
        }
    }

    /**
     * Constructs a GuessResult object with a specified builder.
     * @param builder a Builder object used to specify
     *                the parameters of the result
     */
    private GuessResult(final Builder builder) {
        this.bulls = builder.bulls;
        this.cows = builder.cows;
        this.solved = bulls == builder.total;
    }

    /**
     * Constructs an empty GuessResult.
     */
    private GuessResult() {
        this.bulls = 0;
        this.cows = 0;
        this.solved = false;
    }

    /**
     * Thread safe and lazy singleton holding empty GuessResult.
     */
    private static final class EmptyGuessResultHolder {
        /**
         * Restricts instantiation.
         */
        private EmptyGuessResultHolder() {
            throw new AssertionError();
        }

        /**
         * An instance of empty GuessResult.
         */
        private static final GuessResult INSTANCE = new GuessResult();
    }
}
