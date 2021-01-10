package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractPanelTest {
    protected IPanel testPanel;
    protected long testSeed;
    protected Player player;
    private final static String PLAYER_NAME = "player1";
    private final static int BASE_HP = 4;
    private final static int BASE_ATK = 1;
    private final static int BASE_DEF = -1;
    private final static int BASE_EVD = 2;

    @BeforeEach
    public void setUp() {
        testSeed = new Random().nextLong();
        player = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    }


    @Test
    public abstract void equalsTest();


    @Test
    public void nextPanelTest(){
        assertTrue(testPanel.getNextPanels().isEmpty());

        final var expectedPanel1 = getPanel(PanelType.NEUTRAL,2);
        final var expectedPanel2 = getPanel(PanelType.NEUTRAL,3);

        testPanel.addNextPanel(expectedPanel1);
        assertEquals(1, testPanel.getNextPanels().size());

        testPanel.addNextPanel(expectedPanel2);
        assertEquals(2, testPanel.getNextPanels().size());

        testPanel.addNextPanel(expectedPanel2);
        assertEquals(2, testPanel.getNextPanels().size());

        assertEquals(Set.of(expectedPanel1, expectedPanel2),
                testPanel.getNextPanels());
    }

    protected void checkEquals(IPanel expectedPanel) {
        assertEquals(testPanel, testPanel);
        assertNotSame(expectedPanel, testPanel);
        assertEquals(expectedPanel, testPanel);
    }

    protected void checkNotEquals(IPanel actualPanel) {
        assertNotEquals(testPanel, null);
        assertNotEquals(testPanel, new Object());
        assertNotEquals(testPanel, actualPanel);
    }



    @Test
    public abstract void activateByTest();



    public IPanel getPanel(PanelType type, int id) {
        switch (type) {
            case BONUS:
                return new BonusPanel(id);
            case BOSS:
                return new BossPanel(id);
            case DRAW:
                return new DrawPanel(id);
            case DROP:
                return new DropPanel(id);
            case ENCOUNTER:
                return new EncounterPanel(id);
            case HOME:
                return new HomePanel(id);
            case NEUTRAL:
                return new NeutralPanel(id);
            default:
                throw new IllegalArgumentException();
        }
    }


    protected enum PanelType {
        BONUS, BOSS, DRAW, DROP, ENCOUNTER, HOME, NEUTRAL
    }

    @Test
    public void addAndDeletePlayerOnTest(){
        IPanel panel1=new NeutralPanel(1);
        IPanel panel2=new NeutralPanel(2);
        player.setCPanel(panel1);
        assertEquals(panel1,player.getCPanel());
        assertTrue(panel1.getPlayersOn().contains(player));
        player.setCPanel(panel2);
        assertEquals(panel2, player.getCPanel());
        assertTrue(panel2.getPlayersOn().contains(player));
        assertFalse(panel1.getPlayersOn().contains(player));
    }
}
