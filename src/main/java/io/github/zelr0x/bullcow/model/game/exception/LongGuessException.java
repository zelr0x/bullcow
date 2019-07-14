package io.github.zelr0x.bullcow.model.game.exception;

import io.github.zelr0x.bullcow.util.NumberParseException;

/**
 * Exception indicating that String contains non-numeric data.
 */
public class LongGuessException extends NumberParseException {
    private static final String DEFAULT_MSG = "Guess is too long.";
    /**
     * Constructs an exception with a given message.
     * @param message a message
     */
    public LongGuessException(final String message) {
        super(message);
    }

    /**
     * Constructs an exception with a default message.
     */
    public LongGuessException() {
        this(DEFAULT_MSG);
    }

    /**
     * Constructs an exception with a given message.
     * @param expectedLength expected length of a parsed String
     */
    public LongGuessException(final int expectedLength) {
        this(DEFAULT_MSG + " Expected length: " + expectedLength);
    }
}
