package io.github.zelr0x.bullcow.util;

/**
 * NumberParseException indicates failed attempt to parse a number.
 * Checked version of NumberFormatException.
 */
public class NumberParseException extends Exception {
    /**
     * Constructs an exception with a given message.
     *
     * @param message a message
     */
    public NumberParseException(final String message) {
        super(message);
    }
}
