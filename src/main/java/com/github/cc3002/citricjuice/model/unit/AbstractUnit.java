package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.Dice;

/**
 * This class represents a abstract unit in the game 99.7% Citric Liquid.
 */
public abstract class AbstractUnit implements IUnit {
    private final Dice dice;
    private final String name;
    private final int maxHP;
    protected int atk;
    protected int def;
    protected int evd;
    private int normaLevel;
    private int stars;
    private int wins;
    private int currentHP;

    /**
     * Creates a new character.
     *
     * @param name
     *     the character's name.
     * @param maxHP
     *     the initial and max hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */
    public AbstractUnit(String name, int maxHP, int atk, int def, int evd) {
        this.name = name;
        this.maxHP = currentHP = maxHP;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        this.stars=0;
        this.wins=0;
        normaLevel = 1;
        dice = new Dice();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUnit that = (AbstractUnit) o;
        return getMaxHP() == that.getMaxHP() &&
                getAtk() == that.getAtk() &&
                getDef() == that.getDef() &&
                getEvd() == that.getEvd() &&
                getNormaLevel() == that.getNormaLevel() &&
                getStars() == that.getStars() &&
                getWins() == that.getWins() &&
                getCurrentHP() == that.getCurrentHP() &&
                getName().equals(that.getName());
    }


    @Override
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    @Override
    public void increaseWinsBy(final int amount) {
        wins += amount;
    }

    /**
     * Returns this unit's star count.
     */
    public int getStars() {
        return stars;
    }


    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return dice.roll();
    }

    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the current character's attack points.
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Returns the current character's defense points.
     */
    public int getDef() {
        return def;
    }

    /**
     * Returns the current character's evasion points.
     */
    public int getEvd() {
        return evd;
    }

    /**
     * Returns the number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Returns the current norma level
     */
    public int getNormaLevel() {
        return normaLevel;
    }

    /**
     * Performs a norma clear action; the {@code norma} counter increases in 1.
     */
    public void normaClear() {
        normaLevel++;
    }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(final int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }

    /**
     * Reduces this unit's star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }


    /**
     * Returns the dice.
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * Returns true if the unit is K.O.
     */
    public boolean isKO(){
        return getCurrentHP()==0;
    }

    /**
     * Receives an attack that will decrease depending on its defense and luck.
     *
     * @param atk
     *      attack received
     */
    public void defendAttack(int atk){
        setCurrentHP(getCurrentHP()-Math.max(1,atk-this.roll()-this.getDef()));
    }

    /**
     * Receives an attack that will be evaded or not according to its evasion and its luck.
     *
     * @param atk
     *      attack received
     */
    public void evadeAttack(int atk){
        if(this.roll()+getEvd()<=atk) setCurrentHP(getCurrentHP()-atk);
    }

    /**
     * Randomly decide whether to evade or defend an attack.
     *
     * @param atk
     *      attack received
     */
    public void defendOrEvade(int atk) { //redefinir para player más adelante
        if ((getDice().bool())) {
            defendAttack(atk);
        } else {
            evadeAttack(atk);
        }
    }

    /**
     * If this unit was killed it loses stars and the other unit can get stars and victories.
     *
     * @param otherUnit
     *      unit that could win the stars and wins.
     * @param pStars
     *      fraction of stars that could get otherUnit.
     * @param numWins
     *      wins that could get otherUnit.
     */
    public void trySetValues(IUnit otherUnit,int pStars, int numWins) {
        if (isKO()) {
            otherUnit.increaseStarsBy(getStars()/pStars);
            otherUnit.increaseWinsBy(numWins);
            reduceStarsBy(getStars() / pStars);
        }
    }

}
