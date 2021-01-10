package com.github.cc3002.citricjuice.model.unit;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a wild unit in the game 99.7% Citric Liquid.
 */
public class Wild extends AbstractUnit {

    /**
     * Creates a new wild unit.
     *
     * @param name
     *     the wild's name.
     * @param maxHP
     *     the initial and max hit points of the wild.
     * @param atk
     *     the base damage the wild does.
     * @param def
     *     the base defense of the wild.
     * @param evd
     *     the base evasion of the wild.
     */
    public Wild(String name, int maxHP, int atk, int def, int evd) {
        super(name, maxHP, atk, def, evd);
    }

    /**
     * Returns a copy of this character.
     */
    public Wild copy() {
        return new Wild(getName(), getMaxHP(), getAtk(), getDef(), getEvd());
    }

    @Override
    public void attack(@NotNull IUnit unit) {
        if(!isKO())
            unit.receiveWildAttack(this, this.roll()+this.getAtk());
    }

    @Override
    public void counterAttack(@NotNull IUnit unit) {
        if(!isKO())
            unit.receiveWildCounterAttack(this, this.roll()+this.getAtk());
    }

    @Override
    public void receivePlayerAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 1, 1);
            counterAttack(unit);
        }
    }

    @Override
    public void receivePlayerCounterAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 1, 1);
        }
    }

    @Override
    public void receiveWildAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 1);
            counterAttack(unit);
        }
    }

    @Override
    public void receiveWildCounterAttack(IUnit unit, int newAtk) {
        if (!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 1);
        }
    }

    @Override
    public void receiveBossAttack(IUnit unit, int newAtk) {
        if (!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 1);
            counterAttack(unit);
        }
    }

    @Override
    public void receiveBossCounterAttack(IUnit unit, int newAtk) {
        if (!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 1);
        }
    }

}
