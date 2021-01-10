package com.github.cc3002.citricjuice.model;

import com.github.cc3002.citricjuice.model.unit.Wild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WildTest extends AbstractUnitTest {
    private final static String wildName = "testWild";


    @BeforeEach
    public void setUp() {
        super.setUp();
        testUnit = new Wild(wildName, 5, 3, -1, 3);
    }


    @Override
    @Test
    public void equalsTest() {
        checkEquals(new Wild(wildName,5,3,-1,3));
        checkNotEquals(aWild);
    }


    @Test
    public void copyTest() {
        final var expectedUnit = new Wild(wildName, 5, 3, -1, 3);
        final var actualUnit = testUnit.copy();
        checkCopy(actualUnit,expectedUnit);
    }


    @RepeatedTest(100)
    public void attackTest(){
        checkAttack(aPlayer,5,5,3,1,-1,-1,3,3);
        testUnit.setCurrentHP(5);
        checkAttack(aWild,5,4,3,2,-1,1,3,2);
        testUnit.setCurrentHP(5);
        checkAttack(aBoss,5,7,3,3,-1,1,3,1);
    }

    @RepeatedTest(100)
    public void receivePlayerCounterAttackTest(){
        assertEquals(testUnit.getCurrentHP(),5);

        final var testRandom = new Random(testSeed);
        testUnit.getDice().setSeed(testSeed);
        final boolean bool= testRandom.nextBoolean();
        final int roll=testRandom.nextInt(6)+1;

        final var testRandom2 = new Random(testSeed2);
        aPlayer.getDice().setSeed(testSeed2);
        final int playerRoll= testRandom2.nextInt(6)+1;
        int atk=aPlayer.getAtk()+playerRoll;

        testUnit.receivePlayerCounterAttack(aPlayer, atk);
        receiveCounterAttack(5,-1,3,roll,bool,atk);
    }

    @RepeatedTest(100)
    public void receiveWildCounterAttackTest(){
        assertEquals(testUnit.getCurrentHP(),5);

        final var testRandom = new Random(testSeed);
        testUnit.getDice().setSeed(testSeed);
        final boolean bool= testRandom.nextBoolean();
        final int roll=testRandom.nextInt(6)+1;

        final var testRandom2 = new Random(testSeed2);
        aWild.getDice().setSeed(testSeed2);
        final int wildRoll= testRandom2.nextInt(6)+1;
        int atk=aWild.getAtk()+wildRoll;

        testUnit.receiveWildCounterAttack(aWild, atk);
        receiveCounterAttack(5,-1,3,roll,bool,atk);
    }

    @RepeatedTest(100)
    public void receiveBossCounterAttackTest(){
        assertEquals(testUnit.getCurrentHP(),5);

        final var testRandom = new Random(testSeed);
        testUnit.getDice().setSeed(testSeed);
        final boolean bool= testRandom.nextBoolean();
        final int roll=testRandom.nextInt(6)+1;

        final var testRandom2 = new Random(testSeed2);
        aBoss.getDice().setSeed(testSeed2);
        final int bossRoll= testRandom2.nextInt(6)+1;
        int atk=aBoss.getAtk()+bossRoll;

        testUnit.receiveBossCounterAttack(aBoss, atk);
        receiveCounterAttack(5,-1,3,roll,bool,atk);
    }


}
