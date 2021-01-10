package com.github.cc3002.citricjuice.controller.state;

public class WaitHomeState extends State{

    public WaitHomeState(){
        canStay=true;
    }

    @Override
    public void toMoveState() {
        changeState(new MoveState());
    }

    @Override
    public void toEndTurnState() {
        changeState(new EndTurnState());
    }

    @Override
    public void stayAtHome() throws InvalidActionException, InvalidTransitionException {
        super.stayAtHome();
        controller.setNumberOfDice(0);
    }
}
