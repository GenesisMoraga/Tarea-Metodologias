package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * Class that represents a BossPanel in the board of the game.
 */
public class BossPanel extends AbstractPanel {

    /**
     * Creates a new BossPanel.
     *
     * @param id
     *      Panel identifier.
     */
    public BossPanel(int id){
        super(id);
    }


    @Override
    public void activateBy(Player player) {
        //player entra en batalla con un Boss Unit aleatorio
    }
}
