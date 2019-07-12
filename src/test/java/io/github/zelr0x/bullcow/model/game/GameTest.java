package io.github.zelr0x.bullcow.model.game;

import io.github.zelr0x.bullcow.model.game.exception.LongGuessException;
import io.github.zelr0x.bullcow.model.game.exception.NonNumericGuessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        final int[] target = new int[] {0, 1, 2, 3};
        game = new Game(target);
    }

    @Test
    public void testCheckGuess3b0c() throws Exception {
        final Guess guess = new Guess("0125");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 3b0c: bulls--", 3, result.getBulls());
        Assert.assertEquals("-- 3b0c: cows--", 0, result.getCows());
        Assert.assertEquals("Incorrect guess message",
                "3B0C", result.toString());
    }

    @Test
    public void testCheckGuess0b0c() throws Exception {
        final Guess guess = new Guess("9876");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 0b0c: bulls--", 0, result.getBulls());
        Assert.assertEquals("-- 0b0c: cows--", 0, result.getCows());
        Assert.assertEquals("Incorrect guess message",
                "0B0C", result.toString());
    }

    @Test
    public void testCheckGuess0b4c() throws Exception {
        final Guess guess = new Guess("3210");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 0b4c: bulls--", 0, result.getBulls());
        Assert.assertEquals("-- 0b4c: cows--", 4, result.getCows());
        Assert.assertEquals("Incorrect guess message",
                "0B4C", result.toString());
    }

    @Test
    public void testCheckGuess2b1c() throws Exception {
        final Guess guess = new Guess("5120");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 2b1c: bulls--", 2, result.getBulls());
        Assert.assertEquals("-- 2b1c: cows--", 1, result.getCows());
        Assert.assertEquals("Incorrect guess message",
                "2B1C", result.toString());
    }

    @Test
    public void testCheckGuess2b2c() throws Exception {
        final Guess guess = new Guess("0321");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 2b2c: bulls--", 2, result.getBulls());
        Assert.assertEquals("-- 2b2c: cows--", 2, result.getCows());
        Assert.assertEquals("Incorrect guess message",
                "2B2C", result.toString());
    }

    @Test
    public void testCheckGuess4b0c() throws Exception {
        final Guess guess = new Guess("0123");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 4b0c: bulls--", 4, result.getBulls());
        Assert.assertEquals("-- 4b0c: cows--", 0, result.getCows());
        Assert.assertEquals("Correct guess message",
                "4B0C (correct guess)", result.toString());
    }

    @Test
    public void testCheckGuessPrefix() throws Exception {
        final Guess guess = new Guess("23");
        final GuessResult result = game.checkGuess(guess);
        Assert.assertEquals("-- 3b0c: bulls--", 3, result.getBulls());
        Assert.assertEquals("-- 3b0c: cows--", 0, result.getCows());
    }

    @Test(expected = LongGuessException.class)
    public void testCheckGuessTooLong() throws Exception {
        final int length = game.getTargetDigits().length + 1;
        final Guess guess = new Guess(IntStream.iterate(0, i -> 0)
                .limit(length)
                .toString());
        final GuessResult result = game.checkGuess(guess);
    }

    @Test(expected = NonNumericGuessException.class)
    public void testCheckGuessAlphabetical() throws Exception {
        final Guess guess = new Guess("abcd");
        final GuessResult result = game.checkGuess(guess);
    }
}
