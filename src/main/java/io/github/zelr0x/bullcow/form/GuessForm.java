package io.github.zelr0x.bullcow.form;

/**
 * GuessForm encapsulates guess form.
 */
public final class GuessForm {
    private final String guess;

    /**
     * Constructs a guess object.
     * @param guess the guess
     */
    public GuessForm(final String guess) {
        this.guess = guess;
    }

    /**
     * Returns a string representation of the guess.
     * @return the guess as a String
     */
    public String getGuess() {
        return guess;
    }
}
