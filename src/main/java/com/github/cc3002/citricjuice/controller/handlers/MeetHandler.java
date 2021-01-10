package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.controller.state.WaitFightState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MeetHandler  implements PropertyChangeListener {
    private GameController controller;

    public MeetHandler(GameController controller){
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        controller.tryToWaitFightState();
    }
}
