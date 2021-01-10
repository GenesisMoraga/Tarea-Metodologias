package com.github.cc3002.citricjuice.model;

import java.util.Random;

/**
 * This class represents a dice in the game 99.7% Citric Liquid.
 */
public class Dice {
    private Random random;

    /**
     * Creates a new Dice.
     */
    public Dice(){
        random= new Random();
    }

    /**
     * Set's the seed for random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }


    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll(){
        return random.nextInt(6) + 1;
    }

    /**
     * Returns true or false randomly.
     */
    public boolean bool(){
        return random.nextBoolean();
    }
}
