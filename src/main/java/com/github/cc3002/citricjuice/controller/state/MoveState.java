package com.github.cc3002.citricjuice.controller.state;

public class MoveState extends State {

    public MoveState() {
        canMove=true;
    }

    @Override
    public void toWaitFightState() {
        changeState(new WaitFightState());
    }

    @Override
    public void toWaitHomeState() {
        changeState(new WaitHomeState());
    }

    @Override
    public void toWaitPanelState() {
        changeState(new WaitPanelState());
    }

    @Override
    public void toEndTurnState() {
        changeState(new EndTurnState());
    }

}
