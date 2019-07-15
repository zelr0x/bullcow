package io.github.zelr0x.bullcow.form;

/**
 * GuessForm encapsulates guess form.
 */
public final class GuessForm {
    private final String guess;

    /**
     * Constructs a guess object.
     *
     * @param guess the guess.
     */
    public GuessForm(final String guess) {
        this.guess = guess;
    }

    /**
     * Returns a String object containing the guess.
     *
     * @return a String object containing the guess.
     */
    public String getGuess() {
        return guess;
    }
}
