package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.Dice;

/**
 * Interface that represents a unit in the game.
 */
public interface IUnit {

    /**
     *This unit attack other unit.
     *
     * @param unit
     *      unit that is attacked.
     */
    void attack(IUnit unit);

    /**
     *This unit counter attack other unit.
     *
     * @param unit
     *      unit that is counter attacked.
     */
    void counterAttack(IUnit unit);

    /**
     *Receives an attack from a player unit.
     *
     * @param unit
     *      attacking unit.
     * @param newAtk
     *      attack received.
     */
    void receivePlayerAttack(IUnit unit, int newAtk);

    /**
     *Receives an counter attack from a player unit.
     *
     * @param unit
     *      counter attacking unit.
     * @param newAtk
     *      counter attack received.
     */
    void receivePlayerCounterAttack(IUnit unit, int newAtk);

    /**
     *Receives an attack from a wild unit.
     *
     * @param unit
     *      attacking unit.
     * @param newAtk
     *      attack received.
     */
    void receiveWildAttack(IUnit unit, int newAtk);

    /**
     *Receives an counter attack from a wild unit.
     *
     * @param unit
     *      counter attacking unit.
     * @param newAtk
     *      counter attack received.
     */
    void receiveWildCounterAttack(IUnit unit, int newAtk);

    /**
     *Receives an attack from a boss unit.
     *
     * @param unit
     *      attacking unit.
     * @param newAtk
     *      attack received.
     */
    void receiveBossAttack(IUnit unit, int newAtk);

    /**
     *Receives an counter attack from a boss unit.
     *
     * @param unit
     *      counter attacking unit.
     * @param newAtk
     *      counter attack received.
     */
    void receiveBossCounterAttack(IUnit unit, int newAtk);


    /**
     * Increases this unit's star count by an amount.
     *
     * @param amount
     *      amount in which the stars increase.
     */
    void increaseStarsBy(int amount);

    /**
     * Increases this unit's wins count by an amount.
     *
     * @param amount
     *     amount in which the wins increase.
     */
    void increaseWinsBy(int amount);

    /**
     * return a copy of the unit.
     */
    IUnit copy();

    /**
     * return the max hit points of the unit.
     */
    int getMaxHP();

    /**
     * return the current hit points of the unit.
     */
    int getCurrentHP();

    /**
     * Sets the current character's hit points.
     */
    void setCurrentHP(int i);

    /**
     * return the norma level of the unit.
     */
    int getNormaLevel();

    /**
     * One norma level up
     */
    void normaClear();

    /**
     * return the dice of the unit
     */
    Dice getDice();

    /**
     * @return true if unit's current hit points are at zero, else false.
     */
    boolean isKO();

    String getName();

    int getAtk();

    int getDef();

    int getEvd();
}
