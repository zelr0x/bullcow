package io.github.zelr0x.bullcow.game;

import org.junit.Assert;
import org.junit.Test;

import static io.github.zelr0x.bullcow.game.Game.DEFAULT_LENGTH;

public class NumericUtilsTest {
    @Test
    public void testGenerateTarget() {
        final Game game = new Game();
        Assert.assertEquals("-- Length of generated target --",
                4, game.getTargetDigits().length);
    }

    @Test
    public void testJoinIntArray() {
        final int[] guess = new int[] {1, 2, 3, 4};
        Assert.assertEquals("1234", NumericUtils.joinIntArray(guess));
    }

    @Test
    public void testPrefixJoinIntArray() throws Exception {
        final int[] guess = new int[] {3, 4};
        Assert.assertEquals("0034",
                NumericUtils.prefixJoinIntArray(guess, DEFAULT_LENGTH));
    }
}
