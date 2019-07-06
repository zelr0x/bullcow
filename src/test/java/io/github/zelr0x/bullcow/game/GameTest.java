package io.github.zelr0x.bullcow.game;

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
        final GuessResult guess = game.checkGuess("0125");
        Assert.assertEquals("-- 3b0c: bulls--", 3, guess.getBulls());
        Assert.assertEquals("-- 3b0c: cows--", 0, guess.getCows());
        Assert.assertEquals("Incorrect guess message",
                "3B0C", guess.toString());
    }

    @Test
    public void testCheckGuess0b0c() throws Exception {
        final GuessResult guess = game.checkGuess("9876");
        Assert.assertEquals("-- 0b0c: bulls--", 0, guess.getBulls());
        Assert.assertEquals("-- 0b0c: cows--", 0, guess.getCows());
        Assert.assertEquals("Incorrect guess message",
                "0B0C", guess.toString());
    }

    @Test
    public void testCheckGuess0b4c() throws Exception {
        final GuessResult guess = game.checkGuess("3210");
        Assert.assertEquals("-- 0b4c: bulls--", 0, guess.getBulls());
        Assert.assertEquals("-- 0b4c: cows--", 4, guess.getCows());
        Assert.assertEquals("Incorrect guess message",
                "0B4C", guess.toString());
    }

    @Test
    public void testCheckGuess2b1c() throws Exception {
        final GuessResult guess = game.checkGuess("5120");
        Assert.assertEquals("-- 2b1c: bulls--", 2, guess.getBulls());
        Assert.assertEquals("-- 2b1c: cows--", 1, guess.getCows());
        Assert.assertEquals("Incorrect guess message",
                "2B1C", guess.toString());
    }

    @Test
    public void testCheckGuess2b2c() throws Exception {
        final GuessResult guess = game.checkGuess("0321");
        Assert.assertEquals("-- 2b2c: bulls--", 2, guess.getBulls());
        Assert.assertEquals("-- 2b2c: cows--", 2, guess.getCows());
        Assert.assertEquals("Incorrect guess message",
                "2B2C", guess.toString());
    }

    @Test
    public void testCheckGuess4b0c() throws Exception {
        final GuessResult guess = game.checkGuess("0123");
        Assert.assertEquals("-- 4b0c: bulls--", 4, guess.getBulls());
        Assert.assertEquals("-- 4b0c: cows--", 0, guess.getCows());
        Assert.assertEquals("Correct guess message",
                "4B0C (correct guess)", guess.toString());
    }

    @Test
    public void testCheckGuessPrefix() throws Exception {
        final GuessResult guess = game.checkGuess("23");
        Assert.assertEquals("-- 3b0c: bulls--", 3, guess.getBulls());
        Assert.assertEquals("-- 3b0c: cows--", 0, guess.getCows());
    }

    @Test(expected = NumberParseException.class)
    public void testCheckGuessTooLong() throws Exception {
        final int length = game.getTargetDigits().length + 1;
        final GuessResult guess = game.checkGuess(
                IntStream.iterate(0, i -> 0)
                        .limit(length)
                        .toString());
    }

    @Test(expected = NumberParseException.class)
    public void testCheckGuessAlphabetical() throws Exception {
        final GuessResult guess = game.checkGuess("abcd");
    }
}
