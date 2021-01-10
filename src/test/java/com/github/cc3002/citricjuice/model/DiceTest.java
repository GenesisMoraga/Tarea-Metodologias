package com.github.cc3002.citricjuice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    Dice testDice;

    @BeforeEach
    public void setUp() {
        testDice=new Dice();
    }
    @Test
    public void rollTest(){
        final long testSeed = new Random().nextLong();
        testDice.setSeed(testSeed);
        final int roll = testDice.roll();
        assertTrue(roll >= 1 && roll <= 6);
    }
}
