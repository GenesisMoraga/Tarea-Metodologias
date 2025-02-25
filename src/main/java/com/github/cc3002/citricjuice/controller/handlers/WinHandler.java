package com.github.cc3002.citricjuice.controller.handlers;

import com.github.cc3002.citricjuice.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WinHandler implements PropertyChangeListener {
    private GameController controller;

    public WinHandler(GameController controller){
        this.controller=controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        controller.win();
    }
}
