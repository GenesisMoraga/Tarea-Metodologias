package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.List;
import java.util.Set;

/**
 * Interface that represents a panel in the board of the game.
 */
public interface IPanel {

    /**
     * Executes the appropriate action to the player according to this panel's type.
     *
     * @param player
     *      the player that activate the panel.
     */
    void activateBy(Player player);

    /**
     * Returns a copy of this panel's next ones.
     */
    Set<IPanel> getNextPanels();

    /**
     * Adds a new adjacent IPanel to this one.
     *
     * @param panel
     *     the IPanel to be added.
     */
    void addNextPanel(final IPanel panel);

    List<Player> getPlayersOn();

    void addPlayerOn(Player player);

    void deletePlayerOn(Player player);
}
