package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * Class that represents a DrawPanel in the board of the game.
 */
public class DrawPanel extends AbstractPanel {

    /**
     * Creates a new DrawPanel.
     *
     * @param id
     *       Panel identifier.
     */
    public DrawPanel(int id){
        super(id);
    }


    @Override
    public void activateBy(Player player) {
        //roba una carta del mazo y la agrega a la mano
    }
}
