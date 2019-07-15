package io.github.zelr0x.bullcow.model.game.exception;

import io.github.zelr0x.bullcow.util.NumberParseException;

/**
 * NonNumericGuessException is an Exception indicating that
 * some String contains non-numeric data.
 */
public class NonNumericGuessException extends NumberParseException {
    private static final String DEFAULT_MSG = "String contains non-numeric data.";
    /**
     * Constructs an exception with a given message.
     *
     * @param message a message.
     */
    public NonNumericGuessException(final String message) {
        super(message);
    }

    /**
     * Constructs an exception with a default message.
     */
    public NonNumericGuessException() {
        this(DEFAULT_MSG);
    }
}
