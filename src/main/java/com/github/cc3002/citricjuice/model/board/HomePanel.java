package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

/**
 * Class that represents a HomePanel in the board of the game.
 */
public class HomePanel extends AbstractPanel{
    /**
     * Creates a new HomePanel.
     *
     * @param id
     *      Panel identifier.
     */
    public HomePanel(int id){
        super(id);
    }

    @Override
    public void activateBy(Player player) {
        player.setCurrentHP(player.getCurrentHP() + 1);
        player.normaCheck();
    }
}
