package io.github.zelr0x.bullcow.game;

/**
 * GuessResult object represents the result of a bulls and cows game guess.
 */
public final class GuessResult {
    private final int total;
    private final int bulls;
    private final int cows;

    /**
     * Constructs a GuessResult object with a specified builder.
     * @param builder a Builder object used to specify
     *                the parameters of the result
     */
    private GuessResult(final Builder builder) {
        this.total = builder.total;
        this.bulls = builder.bulls;
        this.cows = builder.cows;
    }

    /**
     * Returns a number of "bulls" - correctly guessed digits.
     * @return a number of correctly guessed digits
     */
    public int getBulls() {
        return bulls;
    }

    /**
     * Returns a number of "cows" - incorrectly positioned digits
     * present in a target number.
     * @return a number of incorrectly positioned digits
     * present in a target number
     */
    public int getCows() {
        return cows;
    }

    /**
     * Returns String representation of a GuessResult.
     * Includes indication of all digits guessed correctly.
     * @return String representation of a GuessResult
     */
    @Override
    public String toString() {
        final String result = bulls + "B" + cows + "C";
        return bulls == total
                ? result + " (correct guess)"
                : result;
    }

    /**
     * Builder builds GuessResult step by step.
     */
    public static final class Builder {
        private final int total;
        private int bulls = 0;
        private int cows = 0;

        /**
         * Creates GuessResult.Builder for a specified total number of digits.
         * @param total a total number of digits
         */
        public Builder(final int total) {
            this.total = total;
        }

        /**
         * Specifies an amount of digits guessed correctly.
         * @param setBulls an amount of digits guessed correctly
         * @return GuessResult.Builder
         */
        public Builder bulls(final int setBulls) {
            this.bulls = setBulls;
            return this;
        }

        /**
         * Specifies an amount of digits guessed correctly.
         * @param setCows an amount of guessed digits that are present
         *                in a number but positioned incorrectly
         * @return GuessResult.Builder
         */
        public Builder cows(final int setCows) {
            this.cows = setCows;
            return this;
        }

        /**
         * Creates a GuessResult object representing the result
         * of a given guess.
         * @return an result of a guess
         */
        public GuessResult build() {
            return new GuessResult(this);
        }
    }
}
