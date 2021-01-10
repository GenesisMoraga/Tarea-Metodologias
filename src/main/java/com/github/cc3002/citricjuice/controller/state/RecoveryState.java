package com.github.cc3002.citricjuice.controller.state;

public class RecoveryState extends State{

    @Override
    public void toStartTurnState() {
        changeState(new StartTurnState());
    }
}
