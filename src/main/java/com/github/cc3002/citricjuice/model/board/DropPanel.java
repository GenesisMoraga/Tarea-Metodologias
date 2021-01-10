package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * Class that represents a DropPanel in the board of the game.
 */
public class DropPanel extends AbstractPanel {

    /**
     * Creates a new DropPanel.
     *
     * @param id
     *      Panel identifier.
     */
    public DropPanel(int id){
        super(id);
    }

    @Override
    public void activateBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
