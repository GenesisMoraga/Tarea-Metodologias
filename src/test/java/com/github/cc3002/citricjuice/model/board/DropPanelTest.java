package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DropPanelTest extends AbstractPanelTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        testPanel = getPanel(PanelType.DROP,1);
    }

    @Override
    @Test
    public void equalsTest() {
        checkEquals(getPanel(PanelType.DROP,1));
        checkNotEquals(getPanel(PanelType.ENCOUNTER,2));
    }


    @Override
    @RepeatedTest(100)
    public void activateByTest() {
        int expectedStars = 30;
        player.increaseStarsBy(30);
        assertEquals(expectedStars, player.getStars());
        final var testRandom = new Random(testSeed);
        player.getDice().setSeed(testSeed);
        for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
            final int roll = testRandom.nextInt(6) + 1;
            testPanel.activateBy(player);
            expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
            assertEquals(expectedStars, player.getStars(),
                    "Test failed with seed: " + testSeed);
            player.normaClear();
        }

    }
}
