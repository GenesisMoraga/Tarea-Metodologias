package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncounterPanelTest extends AbstractPanelTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        testPanel = getPanel(PanelType.ENCOUNTER,1);
    }

    @Override
    @Test
    public void equalsTest() {
        checkEquals(getPanel(PanelType.ENCOUNTER,1));
        checkNotEquals(getPanel(PanelType.HOME,2));
    }


    @Override
    @Test
    public void activateByTest() {
        Player copyplayer=player.copy();
        testPanel.activateBy(player);
        assertEquals(copyplayer,player);
        //modificar cuando cambie metodo en EncounterPanel
    }
}
