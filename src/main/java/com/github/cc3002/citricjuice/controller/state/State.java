package com.github.cc3002.citricjuice.controller.state;

import com.github.cc3002.citricjuice.controller.GameController;
import org.jetbrains.annotations.NotNull;

public class State {
    protected GameController controller;
    protected boolean canRoll=false;
    protected boolean canMove=false;
    protected boolean canEndTurn=false;
    protected boolean canGo=false;
    protected boolean canAttack=false;
    protected boolean canStay=false;

    public void setController(final @NotNull GameController controller){
        this.controller=controller;
    }

    protected void changeState(State state) {
        controller.setState(state);
    }

    public void toCombatState(int i) throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Combat State");
    }

    public void toEndTurnState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to End Turn State");
    }

    public void toMoveState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Move State");
    }

    public void toRecoveryState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Recovery State");
    }

    public void toStartTurnState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Start Turn State");
    }

    public void toWaitFightState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Wait Fight State");
    }

    public void toWaitHomeState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Wait Home State");
    }

    public void toWaitPanelState() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Wait Panel State");
    }

    public void roll() throws InvalidActionException {
        if (!canRoll) {
            throw new InvalidActionException("You can't roll now.");
        }
        controller.roll();
    }

    public void move() throws InvalidActionException {
        if (!canMove) {
            throw new InvalidActionException("You can't move now.");
        }
        controller.movePlayer();
    }

    public void endTurn() throws InvalidActionException {
        if (!canEndTurn) {
            throw new InvalidActionException("You can't end turn now.");
        }
        controller.activatePanel();
        controller.endTurn();
    }

    public void goRight() throws InvalidActionException {
        if(!canGo) {
            throw  new  InvalidActionException("You can't move in this direction");
        }
        controller.moveRight();
    }

    public void goLeft() throws InvalidActionException {
        if(!canGo) {
            throw  new  InvalidActionException("You can't move in this direction");
        }
        controller.moveLeft();
    }

    public void attack() throws InvalidActionException {
        if(!canAttack) {
            throw  new  InvalidActionException("You can't attack");
        }
        controller.attack();
    }

    public void notAttack() throws InvalidActionException {
        if(!canAttack) {
            throw  new  InvalidActionException("You can't not attack");
        }
        controller.notAttack();
    }

    public void stayAtHome() throws InvalidActionException, InvalidTransitionException {
        if(!canStay) {
            throw  new  InvalidActionException("You can't stay at home");
        }
        controller.stayAtHome();
    }

    public void notStayAtHome() throws InvalidActionException {
        if(!canStay) {
            throw  new  InvalidActionException("You can't stay at home");
        }
        controller.notStayAtHome();
    }
}
