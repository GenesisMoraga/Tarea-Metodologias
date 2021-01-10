package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.Boss;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Wild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {
    GameController controller;
    BonusPanel bonusPanel;
    BossPanel bosspanel;
    DropPanel dropPanel;
    EncounterPanel encounterPanel;
    HomePanel homePanel;
    NeutralPanel neutralPanel;
    Player player1;
    Player player2;
    Boss boss;
    Wild wild;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        bonusPanel = controller.createBonusPanel(1);
        bosspanel = controller.createBossPanel(2);
        dropPanel = controller.createDropPanel(3);
        encounterPanel = controller.createEncounterPanel(4);
        homePanel = controller.createHomePanel(5);
        neutralPanel = controller.createNeutralPanel(6);
        player1 = controller.createPlayer("p1", 5, 2, 1, 1, bonusPanel);
        player2 = controller.createPlayer("p2", 6, 1, 2, 3, encounterPanel);
        boss = controller.createBossUnit("boss", 7, 3, 2, 3);
        wild = controller.createWildUnit("wild", 5, 2, 3, 1);
    }

    @Test
    public void createPanelTest() {
        assertEquals(bonusPanel, new BonusPanel(1));
        assertTrue(controller.getPanels().contains(bonusPanel));
        assertEquals(bosspanel, new BossPanel(2));
        assertTrue(controller.getPanels().contains(bosspanel));
        assertEquals(dropPanel, new DropPanel(3));
        assertTrue(controller.getPanels().contains(dropPanel));
        assertEquals(encounterPanel, new EncounterPanel(4));
        assertTrue(controller.getPanels().contains(encounterPanel));
        assertEquals(homePanel, new HomePanel(5));
        assertTrue(controller.getPanels().contains(homePanel));
        assertEquals(neutralPanel, new NeutralPanel(6));
        assertTrue(controller.getPanels().contains(neutralPanel));
    }

    @Test
    public void createPlayerTest() {
        assertEquals(player1, new Player("p1", 5, 2, 1, 1));
        assertTrue(controller.getPlayers().contains(player1));
        assertEquals(bonusPanel, player1.getCPanel());
        assertTrue(bonusPanel.getPlayersOn().contains(player1));
    }

    @Test
    public void createBossTest(){
        assertEquals(boss, new Boss("boss", 7, 3, 2, 3));
        assertTrue(controller.getBosses().contains(boss));
    }

    @Test
    public void createWildTest(){
        assertEquals(wild, new Wild("wild", 5, 2, 3, 1));
        assertTrue(controller.getWilds().contains(wild));
    }

    @Test
    public void setCurrPlayerNormaGoalTest(){
        assertEquals(player1.getNormaGoal(),NormaGoal.STARS);
        controller.setCurrPlayerNormaGoal(NormaGoal.WINS);
        assertEquals(player1.getNormaGoal(),NormaGoal.WINS);
    }

    @Test
    public void turnTest(){
        assertEquals(player1,controller.getTurnOwner());
        controller.endTurn();
        assertEquals(player2,controller.getTurnOwner());
    }

    @Test
    public void setPlayerHomeTest() {
        controller.setPlayerHome(player1,homePanel);
        assertEquals(player1.getHomePanel(),homePanel);
    }

    @Test
    public void getChapterTest() {
        assertEquals(1, controller.getChapter());
    }

    @Test
    public void moveTest(){
        controller.setNextPanel(neutralPanel,bonusPanel);
        controller.setNextPanel(bonusPanel,dropPanel);
        controller.setNextPanel(dropPanel,encounterPanel);
        controller.setNextPanel(encounterPanel,homePanel);
        assertEquals(controller.getPlayerPanel(player1),bonusPanel);
        controller.movePlayer();
        assertEquals(controller.getPlayerPanel(player1),dropPanel);
        assertTrue(controller.getPanels().get(2).getPlayersOn().contains(player1));
        controller.endTurn();
        assertEquals(player2,controller.getTurnOwner());
        assertEquals(player2.getCPanel(),encounterPanel);
        controller.movePlayer();
        assertEquals(homePanel, player2.getCPanel());
    }

}

