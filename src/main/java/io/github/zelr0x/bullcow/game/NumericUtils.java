package io.github.zelr0x.bullcow.game;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Contains utility methods for working with numbers and numeric arrays
 * in base10 numeric system.
 */
public final class NumericUtils {
    private static final int RADIX = 10;

    /**
     * Tries to convert a String representation of a number to
     * an array of digits in that number.
     * @param number a string representation of the number
     * @return an array of digits of the number
     * @throws IllegalArgumentException if the number contains
     * non-numeric data
     */
    public static int[] getDigits(final String number)
            throws IllegalArgumentException {
        final String digits = number.trim();
        if (digits.chars().allMatch(Character::isDigit)) {
            return digits.chars()
                    .map(Character::getNumericValue)
                    .toArray();
        }
        throw new IllegalArgumentException(
                "Number string should consist solely of integers");
    }

    /**
     * Returns an array of digits of a specified number.
     * @param number the target number
     * @param length a length of a target array
     * @return an array of digits of the number
     */
    public static int[] getDigits(final int number, final int length) {
        final int[] result = new int[length];
        for (int i = length - 1, temp = number; i >= 0; i--) {
            result[i] = temp % RADIX;
            temp /= RADIX;
        }
        return result;
    }

    /**
     * Validates the length of an array and returns it back
     * prefixed with zeros if the initial length is less than
     * the specified length.
     * @param digits an initial array
     * @param targetLength  length of a target array
     * @return a normalized version of the array
     * @throws IllegalArgumentException if initial array length
     * is greater than the targetLength
     */
    public static int[] prefixNormalize(final int[] digits,
                                        final int targetLength)
            throws IllegalArgumentException {
        final int diff = targetLength - digits.length;
        if (diff < 0) {
            throw new IllegalArgumentException(
                    "The number is longer than the target length");
        }
        return (diff == 0)
                ? digits
                : prefixZeros(digits, diff);
    }

    /**
     * Creates array containing all the elements of a specified
     * array padded with a specified number of zeros at the beginning.
     * @param arr an array to pad with zeros
     * @param amount a padding length
     * @return the input array padded with zeros at the beginning
     */
    public static int[] prefixZeros(final int[] arr,
                                    final int amount) {
        return IntStream.concat(IntStream.iterate(0, i -> 0)
                        .limit(amount),
                Arrays.stream(arr))
                .toArray();
    }

    /**
     * Generates a number and returns an array of its digits.
     * @param length the length of the number to generate in digits
     * @return an array consisting of digits of a target number
     */
    public static int[] generateDistinctDigitNumber(final int length) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return IntStream.iterate(random.nextInt(RADIX),
                i -> random.nextInt(RADIX))
                .distinct()
                .limit(length)
                .toArray();
    }

    /**
     * Restricts instantiation.
     */
    private NumericUtils() {
        throw new AssertionError();
    }
}
