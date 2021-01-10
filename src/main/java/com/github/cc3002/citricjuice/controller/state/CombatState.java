package com.github.cc3002.citricjuice.controller.state;

public class CombatState extends State {
    int indexOtherPlayer;

    public CombatState(int index){
        this.indexOtherPlayer=index;
    }

    @Override
    public void toEndTurnState() {
        changeState(new EndTurnState());
    }
}
