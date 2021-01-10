package com.github.cc3002.citricjuice.controller.state;

public class WaitFightState extends State {


    public WaitFightState(){
        canAttack=true;

    }

    @Override
    public void toMoveState() {
        changeState(new MoveState());
    }

    @Override
    public void toCombatState(int i) {
        changeState(new CombatState(i));
    }


}
