package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePanelTest extends AbstractPanelTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        testPanel = getPanel(PanelType.HOME,1);
    }

    @Override
    @Test
    public void equalsTest() {
        checkEquals(getPanel(PanelType.HOME,1));
        checkNotEquals(getPanel(PanelType.NEUTRAL,2));
    }


    @Override
    @Test
    public void activateByTest() {
        assertEquals(player.getMaxHP(), player.getCurrentHP());
        testPanel.activateBy(player);
        assertEquals(player.getMaxHP(), player.getCurrentHP());

        player.setCurrentHP(1);
        testPanel.activateBy(player);
        assertEquals(2, player.getCurrentHP());

    }
}
