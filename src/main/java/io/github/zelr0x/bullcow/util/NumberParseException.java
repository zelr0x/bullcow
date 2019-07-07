package io.github.zelr0x.bullcow.game;

/**
 * Indicates failed attempt to parse a number.
 * Checked version of NumberFormatException.
 */
public class NumberParseException extends Exception {
    /**
     * Construct an exception with a message.
     * @param message a message
     */
    public NumberParseException(final String message) {
        super(message);
    }
}
