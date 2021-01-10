package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.unit.Boss;
import com.github.cc3002.citricjuice.model.unit.IUnit;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Wild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public abstract class AbstractUnitTest {
    protected IUnit testUnit;
    protected Player aPlayer;
    protected Boss aBoss;
    protected Wild aWild;
    protected long testSeed;
    protected long testSeed2;

    @BeforeEach
    public void setUp(){
        testSeed= new Random().nextLong();
        testSeed2= new Random().nextLong();
        aPlayer=new Player("player",5,1,-1,3);
        aBoss=new Boss("boss",7,3,1,1);
        aWild=new Wild("wild", 4,2,1,2);

    }

    @Test
    public void constructorTest() {
        final var expectedUnit = testUnit.copy();
        assertEquals(testUnit, testUnit);
        assertEquals(expectedUnit, testUnit);
        assertNotEquals(testUnit,null);
    }

    @Test
    public abstract void equalsTest();

    protected void checkEquals(IUnit expectedUnit) {
        assertEquals(testUnit, testUnit);
        assertNotSame(expectedUnit, testUnit);
        assertEquals(expectedUnit, testUnit);
    }

    protected void checkNotEquals(IUnit actualUnit) {
        assertNotEquals(testUnit, null);
        assertNotEquals(testUnit, new Object());
        assertNotEquals(testUnit, actualUnit);
    }

    @Test
    public abstract void copyTest();

    protected void checkCopy(IUnit actualUnit, IUnit expectedUnit){
        // Checks that the copied player have the same parameters as the original
        assertEquals(expectedUnit, actualUnit);
        // Checks that the copied player doesn't reference the same object
        assertNotSame(expectedUnit, actualUnit);
    }



    @Test
    public void normaClearTest() {
        aPlayer.normaClear();
        assertEquals(2, aPlayer.getNormaLevel());
        aBoss.normaClear();
        assertEquals(2, aBoss.getNormaLevel());
        aWild.normaClear();
        assertEquals(2, aWild.getNormaLevel());
    }

    // region : consistency tests
    @RepeatedTest(100)
    public void hitPointsConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP = new Random(testSeed).nextInt(4 * testUnit.getMaxHP() + 1)
                - 2 * testUnit.getMaxHP();
        testUnit.setCurrentHP(testHP);
        assertTrue(0 <= testUnit.getCurrentHP()
                        && testUnit.getCurrentHP() <= testUnit.getMaxHP(),
                testUnit.getCurrentHP() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);
    }
    @RepeatedTest(100)
    public void normaClearConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're gonna test for 0 to 5 norma clears
        final int iterations = Math.abs(new Random(testSeed).nextInt(6));
        final int expectedNorma = testUnit.getNormaLevel() + iterations;
        for (int it = 0; it < iterations; it++) {
            testUnit.normaClear();
        }
        assertEquals(expectedNorma, testUnit.getNormaLevel(),
                "Test failed with random seed: " + testSeed);
    }

    @RepeatedTest(100)
    public void rollConsistencyTest() {
        final long testSeed = new Random().nextLong();
        testUnit.getDice().setSeed(testSeed);
        final int roll = testUnit.getDice().roll();
        assertTrue(roll >= 1 && roll <= 6,
                roll + "is not in [1, 6]" + System.lineSeparator()
                        + "Test failed with random seed: " + testSeed);
    }

    @Test
    public void isKOTest(){
        assertFalse(testUnit.isKO());
        testUnit.setCurrentHP(0);
        assertTrue(testUnit.isKO());
    }

    @RepeatedTest(100)
    public void defendAttackTest(){
        assertEquals(aPlayer.getCurrentHP(),5);
        final var testRandom = new Random(testSeed);
        aPlayer.getDice().setSeed(testSeed);
        final int roll=testRandom.nextInt(6)+1;
        aPlayer.defendAttack(7);
        assertEquals(aPlayer.getCurrentHP(), Math.max(0,5-Math.max(1,7-roll+1)));
    }

    @RepeatedTest(100)
    public void evadeAttackTest(){
        assertEquals(aBoss.getCurrentHP(),7);
        final var testRandom= new Random(testSeed);
        aBoss.getDice().setSeed(testSeed);
        final int roll=testRandom.nextInt(6)+1;
        aBoss.evadeAttack(5);
        if(roll+1<=5){
            assertEquals(aBoss.getCurrentHP(), 2);
        }else{
            assertEquals(aBoss.getCurrentHP(),7);
        }
    }

    @Test
    public void trySetValuesTest(){
        aPlayer.increaseStarsBy(5);
        aPlayer.setCurrentHP(0);
        aPlayer.trySetValues(aBoss,2,1);
        assertEquals(aPlayer.getStars(),3);
        assertEquals(aBoss.getWins(),1);
        assertEquals(aBoss.getStars(),2);
        aPlayer.setCurrentHP(1);
        aPlayer.trySetValues(aBoss,2,1);
        assertEquals(aPlayer.getStars(),3);
        assertEquals(aBoss.getWins(),1);
        assertEquals(aBoss.getStars(),2);
    }

    protected void checkAttack(IUnit unitRA, int HP1, int HP2, int atk1, int atk2, int def1, int def2, int evd1, int evd2){
        final var testRandom = new Random(testSeed);
        testUnit.getDice().setSeed(testSeed);
        final int roll1=testRandom.nextInt(6)+1;
        final boolean bool1= testRandom.nextBoolean();
        final int roll12=testRandom.nextInt(6)+1;

        final var testRandom2 = new Random(testSeed2);
        unitRA.getDice().setSeed(testSeed2);
        final boolean bool2=testRandom2.nextBoolean();
        final int roll2= testRandom2.nextInt(6)+1;
        final int roll22= testRandom2.nextInt(6)+1;

        testUnit.attack(unitRA);

        if(!unitRA.isKO()){
            if(bool2){
                assertEquals(unitRA.getCurrentHP(),Math.max(0,HP2-Math.max(1,roll1+atk1-roll2-def2)));
            }else{
                if(roll1+atk1<roll2+evd2) {
                  assertEquals(unitRA.getCurrentHP(), HP2);
                }else{
                    assertEquals(unitRA.getCurrentHP(),Math.max(0,HP2-roll1-atk1));
                }
            }
            if(bool1){
                assertEquals(testUnit.getCurrentHP(),Math.max(0,HP1-Math.max(1,roll22+atk2-roll12-def1)));
            }else{
                if(roll22+atk2<roll12+evd1) {
                    assertEquals(testUnit.getCurrentHP(), HP1);
                }else{
                    assertEquals(testUnit.getCurrentHP(),Math.max(0,HP1-roll22-atk2));
                }
            }

        }
    }

    protected void receiveCounterAttack(int initHP, int def, int evd, int roll, boolean bool, int newAtk){
        if(bool){
            assertEquals(testUnit.getCurrentHP(),Math.max(0,initHP-Math.max(1,newAtk-roll-def)));
        }else{
            if(newAtk<roll+evd) {
                assertEquals(testUnit.getCurrentHP(), initHP);
            }else{
                assertEquals(testUnit.getCurrentHP(),Math.max(0,initHP-newAtk));
            }
        }
    }



}
