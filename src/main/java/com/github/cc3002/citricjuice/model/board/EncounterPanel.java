package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * Class that represents a EncounterPanel in the board of the game.
 */
public class EncounterPanel extends AbstractPanel {

    /**
     * Creates a new EncounterPanel.
     *
     * @param id
     *      Panel identifier.
     */
    public EncounterPanel(int id){
        super(id);
    }


    @Override
    public void activateBy(Player player) {
        //player entra en batalla con un wild unit aleatorio
    }
}
