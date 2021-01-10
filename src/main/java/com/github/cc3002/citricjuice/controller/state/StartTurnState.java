package com.github.cc3002.citricjuice.controller.state;

public class StartTurnState extends  State {

    public StartTurnState() {
        canRoll=true;
    }

    @Override
    public void toRecoveryState() {
        changeState(new RecoveryState());
    }

    @Override
    public void toMoveState() {
        changeState(new MoveState());
    }

    @Override
    public void roll() throws InvalidActionException {
        super.roll();
        toMoveState();
    }
}
