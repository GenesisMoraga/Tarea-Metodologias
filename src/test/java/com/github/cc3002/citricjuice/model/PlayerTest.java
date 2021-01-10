package com.github.cc3002.citricjuice.model;


import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the players of the game.
 *
 */
public class PlayerTest extends AbstractUnitTest{
  private final static String playerName = "Suguri";

  @BeforeEach
  public void setUp() {
     super.setUp();
     testUnit = new Player(playerName, 4, 1, -1, 2);
  }


  @Override
  @Test
  public void equalsTest() {
    checkEquals(new Player(playerName,4,1,-1,2));
    checkNotEquals(aPlayer);
  }

  @Test
  public void setTest(){
    assertEquals(aPlayer.getAtk(),1);
    aPlayer.setAtk(3);
    assertEquals(aPlayer.getAtk(),3);

    assertEquals(aPlayer.getEvd(),3);
    aPlayer.setEvd(-1);
    assertEquals(aPlayer.getEvd(),-1);

    assertEquals(aPlayer.getDef(),-1);
    aPlayer.setDef(2);
    assertEquals(aPlayer.getDef(),2);
  }


  @Test
  public void copyTest() {
    final var expectedUnit = new Player(playerName, 4, 1, -1, 2);
    final var actualUnit = testUnit.copy();
    checkCopy(actualUnit,expectedUnit);
  }

  @Test
  public void hitPointsTest() {
    assertEquals(testUnit.getMaxHP(), testUnit.getCurrentHP());
    testUnit.setCurrentHP(2);
    assertEquals(2, testUnit.getCurrentHP());
    testUnit.setCurrentHP(-1);
    assertEquals(0, testUnit.getCurrentHP());
    testUnit.setCurrentHP(5);
    assertEquals(4, testUnit.getCurrentHP());
  }

  @RepeatedTest(100)
  public void attackTest(){
    checkAttack(aPlayer,4,5,1,1,-1,-1,2,3);
    testUnit.setCurrentHP(4);
    checkAttack(aWild,4,4,1,2,-1,1,2,2);
    testUnit.setCurrentHP(4);
    checkAttack(aBoss,4,7,1,3,-1,1,2,1);
  }

  @RepeatedTest(100)
  public void receivePlayerCounterAttackTest(){
    assertEquals(testUnit.getCurrentHP(),4);

    final var testRandom = new Random(testSeed);
    testUnit.getDice().setSeed(testSeed);
    final boolean bool= testRandom.nextBoolean();
    final int roll=testRandom.nextInt(6)+1;

    final var testRandom2 = new Random(testSeed2);
    aPlayer.getDice().setSeed(testSeed2);
    final int playerRoll= testRandom2.nextInt(6)+1;
    int atk=aPlayer.getAtk()+playerRoll;

    testUnit.receivePlayerCounterAttack(aPlayer, atk);
    receiveCounterAttack(4,-1,2,roll,bool,atk);
  }

  @RepeatedTest(100)
  public void receiveWildCounterAttackTest(){
    assertEquals(testUnit.getCurrentHP(),4);

    final var testRandom = new Random(testSeed);
    testUnit.getDice().setSeed(testSeed);
    final boolean bool= testRandom.nextBoolean();
    final int roll=testRandom.nextInt(6)+1;

    final var testRandom2 = new Random(testSeed2);
    aWild.getDice().setSeed(testSeed2);
    final int wildRoll= testRandom2.nextInt(6)+1;
    int atk=aWild.getAtk()+wildRoll;

    testUnit.receiveWildCounterAttack(aWild, atk);
    receiveCounterAttack(4,-1,2,roll,bool,atk);
  }

  @RepeatedTest(100)
  public void receiveBossCounterAttackTest(){
    assertEquals(testUnit.getCurrentHP(),4);

    final var testRandom = new Random(testSeed);
    testUnit.getDice().setSeed(testSeed);
    final boolean bool= testRandom.nextBoolean();
    final int roll=testRandom.nextInt(6)+1;

    final var testRandom2 = new Random(testSeed2);
    aBoss.getDice().setSeed(testSeed2);
    final int bossRoll= testRandom2.nextInt(6)+1;
    int atk=aBoss.getAtk()+bossRoll;

    testUnit.receiveBossCounterAttack(aBoss, atk);
    receiveCounterAttack(4,-1,2,roll,bool,atk);
  }

}
