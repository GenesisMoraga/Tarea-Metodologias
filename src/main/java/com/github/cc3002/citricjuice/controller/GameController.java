package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.controller.handlers.*;
import com.github.cc3002.citricjuice.controller.state.*;
import com.github.cc3002.citricjuice.model.unit.Boss;
import com.github.cc3002.citricjuice.model.unit.Player;
import com.github.cc3002.citricjuice.model.unit.Wild;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.NormaGoal;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Class that represents the controller of the game.
 */

public class GameController {
    private List<Player> players = new ArrayList<>();
    private List<IPanel> panels = new ArrayList<>();
    private List<Boss> bosses = new ArrayList<>();
    private List<Wild> wilds = new ArrayList<>();
    private int turn = 0;
    private int chapter = 1;
    private int numberOfDice=0;
    private State state;
    private HashMap<Integer,int[]> panelPosition=new HashMap<>();
    private List<Player> meetPlayers=new ArrayList<>();

    private WinHandler winHandler=new WinHandler(this);
    private MeetHandler meetHandler=new MeetHandler(this);
    private HomeHandler homeHandler=new HomeHandler(this);
    private NextPanelsHandler nextPanelsHandler= new NextPanelsHandler(this);
    private EndTurnHandler endTurnHandler= new EndTurnHandler(this);


    public GameController() {
        this.setState(new StartTurnState());
    }

    /**
     * Creates a new bonus panel and adds it to the game.
     */
    public BonusPanel createBonusPanel(int id) {
        BonusPanel panel = new BonusPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Creates a new boss panel and adds it to the game.
     */
    public BossPanel createBossPanel(int id) {
        BossPanel panel = new BossPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Creates a new drop panel and adds it to the game.
     */
    public DropPanel createDropPanel(int id) {
        DropPanel panel = new DropPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Creates a new encounter panel and adds it to the game.
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel panel = new EncounterPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Creates a new home panel and adds it to the game.
     */
    public HomePanel createHomePanel(int id) {
        HomePanel panel = new HomePanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Creates a new neutral panel and adds it to the game.
     */
    public NeutralPanel createNeutralPanel(int id) {
        NeutralPanel panel = new NeutralPanel(id);
        panels.add(panel);
        return panel;
    }

    /**
     * Creates a new player with a actual panel and adds it to the game.
     */
    public Player createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
        Player player = new Player(name, hitPoints, attack, defense, evasion);
        player.setCPanel(panel);
        players.add(player);
        return player;
    }

    /**
     * Creates a new wild unit and adds it to the game.
     */
    public Wild createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        Wild wild = new Wild(name, hitPoints, attack, defense, evasion);
        wilds.add(wild);
        return wild;
    }

    /**
     * Creates a new boss unit and adds it to the game.
     */
    public Boss createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        Boss boss = new Boss(name, hitPoints, attack, defense, evasion);
        bosses.add(boss);
        return boss;
    }

    /**
     * add panel1 on the next panels of panel
     */
    public void setNextPanel(@NotNull IPanel panel, @NotNull IPanel panel1) {
        panel.addNextPanel(panel1);
    }

    /**
     * Return the panels of the game.
     */
    public List<IPanel> getPanels() {
        return panels;
    }

    /**
     * Return the players of the game.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Return the bosses of the game.
     */
    public List<Boss> getBosses() {
        return bosses;
    }

    /**
     * Return the wild units of the game.
     */
    public List<Wild> getWilds() {
        return wilds;
    }

    /**
     * Set the norma goal of the turn owner.
     */
    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        getTurnOwner().setNormaGoal(goal);
    }

    /**
     * Return the player who is the turn owner.
     */
    public Player getTurnOwner() {
        return players.get(turn);
    }

    /**
     * Return  the turn.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Return  the number of dice.
     */
    public int getNumberOfDice() {
        return numberOfDice;
    }

    /**
     * Return the current panel of the unit.
     */
    public IPanel getPlayerPanel(Player unit) {
        return unit.getCPanel();
    }

    /**
     * move the player one panel if it is possible.
     */
    public void movePlayer() {
        Set<IPanel> vecinos=getTurnOwner().getCPanel().getNextPanels();
        if(vecinos.size()==1) {
            for(IPanel p:panels) {
                IPanel elegido = vecinos.iterator().next();
                if(elegido.equals(p)) {
                    getTurnOwner().setCPanel(p);
                    setNumberOfDice(getNumberOfDice()-1);
                }
            }
        }else{
            getTurnOwner().hasNextPanels();
        }
    }

    /**
     * Set the number of the dice, if is 0 try to end turn
     * @param i new number of the dice
     */
    public void setNumberOfDice(int i) {
        numberOfDice=Math.max(i,0);
        if(numberOfDice==0){
            try {
                state.toEndTurnState();
                getTurnOwner().endTurn();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the home panel of the unit.
     */
    public void setPlayerHome(Player unit, HomePanel panel) {
        unit.setHomePanel(panel);
    }

    /**
     * Return the current chapter of the game.
     */
    public int getChapter() {
        return chapter;
    }


    /**
     * End the turn of the current player and increase the chapter if this is necessary.
     */
    public void endTurn() {
        turn=(turn+1)% players.size();
        if(turn==0) chapter+=1;
    }

    /**
     * try to end the turn of the current player.
     */
    public void tryToEndTurn() {
        try {
            state.endTurn();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }


    public void win() {

    }

    /**
     * Set the state of the controller.
     * @param state new state
     */
    public void setState(@NotNull State state) {
        this.state=state;
        state.setController(this);
    }

    /**
     * Start the game
     */
    public void startNewGame() {
        createGame();

    }

    /**
     * create the units, the panels and its position.
     */
    private void createGame(){
        IPanel p0=createHomePanel(0);
        IPanel p1=createBonusPanel(1);
        IPanel p2=createDropPanel(2);
        IPanel p3=createNeutralPanel(3);
        IPanel p4=createBossPanel(4);
        IPanel p5=createEncounterPanel(5);
        IPanel p6=createBonusPanel(6);
        IPanel p7=createNeutralPanel(7);
        IPanel p8=createBossPanel(8);
        IPanel p9=createDropPanel(9);
        IPanel p10=createBossPanel(10);
        IPanel p11=createBonusPanel(11);
        IPanel p12=createHomePanel(12);
        IPanel p13=createNeutralPanel(13);
        IPanel p14=createBonusPanel(14);
        IPanel p15=createEncounterPanel(15);
        IPanel p16=createDropPanel(16);
        IPanel p17=createBonusPanel(17);
        IPanel p18=createNeutralPanel(18);
        IPanel p19=createDropPanel(19);
        IPanel p20=createNeutralPanel(20);
        IPanel p21=createEncounterPanel(21);
        IPanel p22=createBossPanel(22);
        IPanel p23=createHomePanel(23);
        IPanel p24=createBonusPanel(24);
        IPanel p25=createNeutralPanel(25);
        IPanel p26=createDropPanel(26);
        IPanel p27=createBossPanel(27);
        IPanel p28=createDropPanel(28);
        IPanel p29=createBonusPanel(29);
        IPanel p30=createEncounterPanel(30);
        IPanel p31=createNeutralPanel(31);
        IPanel p32=createBossPanel(32);
        IPanel p33=createEncounterPanel(33);
        IPanel p34=createHomePanel(34);
        IPanel p35=createBonusPanel(35);
        IPanel p36=createDropPanel(36);
        IPanel p37=createNeutralPanel(37);
        IPanel p38=createEncounterPanel(38);
        IPanel p39=createBonusPanel(39);
        IPanel p40=createEncounterPanel(40);
        IPanel p41=createDropPanel(41);
        IPanel p42=createBossPanel(42);
        IPanel p43=createNeutralPanel(43);

        setNextPanel(p0,p1);
        setNextPanel(p1,p2);
        setNextPanel(p2,p3);
        setNextPanel(p3,p4);
        setNextPanel(p4,p5);
        setNextPanel(p5,p6);
        setNextPanel(p5,p12);
        setNextPanel(p6,p7);
        setNextPanel(p7,p8);
        setNextPanel(p8,p9);
        setNextPanel(p9,p10);
        setNextPanel(p10,p11);
        setNextPanel(p11,p13);
        setNextPanel(p12,p11);
        setNextPanel(p13,p14);
        setNextPanel(p14,p15);
        setNextPanel(p15,p16);
        setNextPanel(p16,p17);
        setNextPanel(p16,p23);
        setNextPanel(p17,p18);
        setNextPanel(p18,p19);
        setNextPanel(p19,p20);
        setNextPanel(p20,p21);
        setNextPanel(p21,p22);
        setNextPanel(p22,p24);
        setNextPanel(p23,p22);
        setNextPanel(p24,p25);
        setNextPanel(p25,p26);
        setNextPanel(p26,p27);
        setNextPanel(p27,p28);
        setNextPanel(p27,p34);
        setNextPanel(p28,p29);
        setNextPanel(p29,p30);
        setNextPanel(p30,p31);
        setNextPanel(p31,p32);
        setNextPanel(p32,p33);
        setNextPanel(p33,p35);
        setNextPanel(p34,p33);
        setNextPanel(p35,p36);
        setNextPanel(p36,p37);
        setNextPanel(p37,p38);
        setNextPanel(p38,p39);
        setNextPanel(p38,p0);
        setNextPanel(p39,p40);
        setNextPanel(p40,p41);
        setNextPanel(p41,p42);
        setNextPanel(p42,p43);
        setNextPanel(p43,p1);

        createPlayer("Player1",5,2,3,1,p0);
        createPlayer("Player2", 5,1,3,3,p12);
        createPlayer("Player3",5,3,1,0,p23);
        createPlayer("Player4",5,2,1,3,p34);

        for(Player player:players){
            setPlayerHome(player,(HomePanel) player.getCPanel());
            player.increaseStarsBy(1);
            player.addWinListener(winHandler);
            player.addMeetListener(meetHandler);
            player.addHomeListener(homeHandler);
            player.addNextPanelsListener(nextPanelsHandler);
            player.addEndTurnListener(endTurnHandler);
        }

        panelPosition.put(0, new int[]{2, 2});
        for(int i=1; i<=5; i++){
            panelPosition.put(i,new int[]{1,i+1});
        }
        for(int i=0; i<3; i++){
            panelPosition.put(i+6,new int[]{0,i+6});
            panelPosition.put(i+10,new int[]{2,8-i});
            panelPosition.put(13+i,new int[]{3+i,7});
            panelPosition.put(17+i, new int[]{6+i,8});
            panelPosition.put(21+i, new int[]{8-i,6});
            panelPosition.put(24+i,new int[]{7,5-i});
            panelPosition.put(28+i,new int[]{8,2-i});
            panelPosition.put(32+i,new int[]{6,i});
            panelPosition.put(35+i,new int[]{5-i,1});
            panelPosition.put(39+i,new int[]{2-i,0});
        }
        panelPosition.put(9,new  int[]{1,8});
        panelPosition.put(16,new int[]{6,7});
        panelPosition.put(20, new int[]{8,7});
        panelPosition.put(27,new int[]{7,2});
        panelPosition.put(31,new int[]{7,0});
        panelPosition.put(38, new int[]{2,1});
        panelPosition.put(42,new int[]{0,1});
        panelPosition.put(43, new int[]{0,2});


    }

    /**
     * return the current hit points of the player i.
     * @param i number of the player
     * @return the current HP
     */
    public int getCHP(int i) {
        return players.get(i).getCurrentHP();
    }

    /**
     * return the attack of the player i.
     * @param i number of player
     * @return the attack
     */
    public int getAtk(int i){
        return players.get(i).getAtk();
    }

    /**
     * return the defense of the player i.
     * @param i number of player
     * @return the defense
     */
    public int getDef(int i) {
        return players.get(i).getDef();
    }

    /**
     * return the evasion of the player i.
     * @param i number of player
     * @return the evasion
     */
    public int getEvs(int i) {
        return players.get(i).getEvd();
    }

    /**
     * return the norma level of the player i.
     * @param i number of player
     * @return the norma level
     */
    public int getNorma(int i) {
        return players.get(i).getNormaLevel();
    }

     /**
     * return the stars of the player i.
     * @param i number of player
     * @return the number of stars
     */
    public int getStars(int i) {
        return players.get(i).getStars();
    }

    /**
     * return the wins of the player i.
     * @param i number of player
     * @return the number of wins
     */
    public int getWins(int i) {
        return players.get(i).getWins();
    }

    /**
     * try to roll if it's possible
     */
    public void tryToRoll() {
        try {
            state.roll();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * set the number of dice with the value of the roll of the turn owner
     */
    public void roll() {
        numberOfDice=getTurnOwner().roll();
    }

    /**
     * try to move the turn owner
     */
    public void tryToMove() {
        try {
            state.move();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * try to go to wait panel state
     */
    public void tryToWaitPanelState() {
        try {
            state.toWaitPanelState();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    /**
     * return the horizontal position of the current panel of player i
     * @param i number of the player
     * @return the position
     */
    public int xPos(int i) {
        int panelIndex=panels.indexOf(players.get(i).getCPanel());
        return panelPosition.get(panelIndex)[0];
    }

    /**
     * return the vertical position of the current panel of player i
     * @param i number of the player
     * @return the position
     */
    public int yPos(int i) {
        int panelIndex=panels.indexOf(players.get(i).getCPanel());
        return panelPosition.get(panelIndex)[1];
    }

    /**
     * the turn owner activate panel
     */
    public void activatePanel() {
        getTurnOwner().activatePanel();
    }

    /**
     * the turn owner try to go to the right
     */
    public void tryToGoRight() {
        try {
            state.goRight();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * the turn owner go to the next panel right
     */
    public void moveRight() {
        List<IPanel> vecinos=new ArrayList<>();
        for(IPanel panel:getTurnOwner().getCPanel().getNextPanels()){
            vecinos.add(panel);
        }
        if(panels.indexOf(vecinos.get(0)) < panels.indexOf(vecinos.get(1))) {
            getTurnOwner().setCPanel(vecinos.get(0));
        } else {
            getTurnOwner().setCPanel(vecinos.get(1));
        }
    }

    /**
     * the turn owner try to go to the left
     */
    public void tryToGoLeft() {
        try {
            state.goLeft();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * the turn owner go to the next panel left
     */
    public void moveLeft() {
        List<IPanel> vecinos=new ArrayList<>();
        for(IPanel panel:getTurnOwner().getCPanel().getNextPanels()){
            vecinos.add(panel);
        }
        if(panels.indexOf(vecinos.get(0)) > panels.indexOf(vecinos.get(1))) {
            getTurnOwner().setCPanel(vecinos.get(0));
        } else {
            getTurnOwner().setCPanel(vecinos.get(1));
        }
    }

    public void tryToWaitFightState() {
        try {
            state.toWaitFightState();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    public void tryToAttack() {
        try {
            state.attack();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    public void tryToNotAttack() {
        try {
            state.notAttack();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    public void attack() {
        Player meetPlayer=new Player("",0,0,0,0);
        for (Player player: getTurnOwner().getCPanel().getPlayersOn()){
            if(!player.equals(getTurnOwner()) && !meetPlayers.contains(player)){
                meetPlayer=player;
                meetPlayers.add(player);
                break;
            }
        }
        try {
            state.toCombatState(players.indexOf(meetPlayer));
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }

        getTurnOwner().attack(meetPlayer);
    }

    public void notAttack() {
        for (Player player: getTurnOwner().getCPanel().getPlayersOn()) {
            if (!player.equals(getTurnOwner()) && !meetPlayers.contains(player)) {
                meetPlayers.add(player);
                break;
            }
        }
        if(meetPlayers.size()==(getTurnOwner().getCPanel().getPlayersOn().size()-1)){
            try {
                state.toMoveState();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }

    public void tryToCounterAttack() {
    }

    public void tryToNotCounterAttack() {
    }

    public void tryToEvade() {
    }

    public void tryToDefend() {
    }

    public void tryToStayAtHome() {
        try {
            state.stayAtHome();
        } catch (InvalidActionException | InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    public void tryToNotStayAtHome() {
        try {
            state.notStayAtHome();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    public void notStayAtHome() {
    }

    public void stayAtHome() throws InvalidTransitionException {
        state.toMoveState();
    }

    public void tryToWaitHomeState() throws InvalidTransitionException {
        state.toWaitHomeState();
    }
}
