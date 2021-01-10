package com.github.cc3002.citricjuice.model.unit;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a boss unit in the game 99.7% Citric Liquid.
 */

public class Boss extends AbstractUnit {
    /**
     * Creates a new boss.
     *
     * @param name
     *     the boss's name.
     * @param maxHP
     *     the initial and max hit points of the boss.
     * @param atk
     *     the base damage the boss does.
     * @param def
     *     the base defense of the boss.
     * @param evd
     *     the base evasion of the boss.
     */
    public Boss(String name, int maxHP, int atk, int def, int evd) {
        super(name, maxHP, atk, def, evd);
    }

    /**
     * Returns a copy of this character.
     */
    public Boss copy() {
        return new Boss(getName(), getMaxHP(), getAtk(), getDef(), getEvd());
    }


    @Override
    public void attack(@NotNull IUnit unit) {
        if(!isKO())
            unit.receiveBossAttack(this, this.roll()+this.getAtk());
    }

    @Override
    public void counterAttack(@NotNull IUnit unit) {
        if(!isKO())
            unit.receiveBossCounterAttack(this, this.roll()+this.getAtk());
    }

    @Override
    public void receivePlayerAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 1, 3);
            counterAttack(unit);
        }
    }

    @Override
    public void receivePlayerCounterAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 1, 3);
        }
    }


    @Override
    public void receiveWildAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 3);
            counterAttack(unit);
        }
    }

    @Override
    public void receiveWildCounterAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 3);
        }
    }


    @Override
    public void receiveBossAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 3);
            counterAttack(unit);
        }
    }

    @Override
    public void receiveBossCounterAttack(IUnit unit, int newAtk) {
        if(!isKO()) {
            defendOrEvade(newAtk);
            trySetValues(unit, 2, 3);
        }
    }

}
