package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.controller.state.InvalidTransitionException;
import com.github.cc3002.citricjuice.controller.state.WaitHomeState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeHandler implements PropertyChangeListener {
    private GameController controller;

    public HomeHandler(GameController controller) {
        this.controller=controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        try {
            controller.tryToWaitHomeState();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
    }
}
