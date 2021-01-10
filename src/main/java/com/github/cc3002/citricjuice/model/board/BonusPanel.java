package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * Class that represents a BonusPanel in the board of the game.
 */
public class BonusPanel extends AbstractPanel{

    /**
     * Creates a new BonusPanel.
     *
     * @param id
     *      Panel identifier.
     */
    public BonusPanel(int id){
        super(id);
    }

    @Override
    public void activateBy(Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }
}
