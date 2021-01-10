package com.github.cc3002.citricjuice.model.board;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusPanelTest extends AbstractPanelTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        testPanel = getPanel(PanelType.BONUS,1);
    }

    @Override
    @Test
    public void equalsTest() {
        checkEquals(getPanel(PanelType.BONUS,1));
        checkNotEquals(getPanel(PanelType.BOSS,2));
    }



    @Override
    @RepeatedTest(100)
    public void activateByTest() {
        int expectedStars = 0;
        assertEquals(expectedStars, player.getStars());
        final var testRandom = new Random(testSeed);
        player.getDice().setSeed(testSeed);
        for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
            final int roll = testRandom.nextInt(6) + 1;
            testPanel.activateBy(player);
            expectedStars += roll * Math.min(3, normaLvl);
            assertEquals(expectedStars, player.getStars(),
                    "Test failed with seed: " + testSeed);
            player.normaClear();
        }

    }

}
