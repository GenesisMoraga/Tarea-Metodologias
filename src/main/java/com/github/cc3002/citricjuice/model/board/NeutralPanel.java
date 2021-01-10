package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

public class NeutralPanel extends AbstractPanel {

    /**
     * Creates a new NeutralPanel.
     *
     * @param id
     *      Panel identifier.
     */
    public NeutralPanel(int id){
        super(id);
    }

    @Override
    public void activateBy(Player player) {
    }
}
