package com.github.cc3002.citricjuice.model.board;
import com.github.cc3002.citricjuice.model.unit.Player;

import java.util.*;

/**
 * Class that represents a AbstractPanel in the board of the game.
 */
public abstract class AbstractPanel implements IPanel{
    private int id;
    private Set<IPanel> nextPanels = new HashSet<>();
    private List<Player> playersOn= new ArrayList<>();

    /**
     * Creates a new AbstractPanel.
     *
     * @param id
     *      Panel identifier.
     */
    public AbstractPanel(int id){
        this.id=id;
    }

    /**
     *Returns the panel´s id.
     */
    private int getId() {
        return id;
    }

    @Override
    public Set<IPanel> getNextPanels() {
        return Set.copyOf(nextPanels);
    }


    @Override
    public void addNextPanel(final IPanel panel) {
        nextPanels.add(panel);
    }

    @Override
    public List<Player> getPlayersOn() {
        return List.copyOf(playersOn);
    }

    @Override
    public void addPlayerOn(Player player) {
        playersOn.add(player);
    }

    @Override
    public void deletePlayerOn(Player player){
        playersOn.remove(player);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPanel that = (AbstractPanel) o;
        return id==that.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClass());
    }



}
