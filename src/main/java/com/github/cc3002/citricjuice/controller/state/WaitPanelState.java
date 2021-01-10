package com.github.cc3002.citricjuice.controller.state;

public class WaitPanelState extends State{

    public WaitPanelState() {
        canGo=true;
    }

    @Override
    public void toMoveState() {
        changeState(new MoveState());
    }

    @Override
    public void goRight() throws InvalidActionException {
        super.goRight();
        toMoveState();
        controller.setNumberOfDice(controller.getNumberOfDice()-1);
    }

    @Override
    public void goLeft() throws InvalidActionException {
        super.goLeft();
        toMoveState();
        controller.setNumberOfDice(controller.getNumberOfDice()-1);
    }
}
