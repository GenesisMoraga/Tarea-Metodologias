package com.github.cc3002.citricjuice.controller.state;

public class EndTurnState extends State {

    public EndTurnState() {
        canEndTurn=true;
    }

    @Override
    public void toStartTurnState() {
        changeState(new StartTurnState());
    }

    @Override
    public void endTurn() throws InvalidActionException {
        super.endTurn();
        toStartTurnState();
    }
}
