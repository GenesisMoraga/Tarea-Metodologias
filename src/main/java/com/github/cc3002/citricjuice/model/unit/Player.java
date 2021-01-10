package com.github.cc3002.citricjuice.model.unit;

import com.github.cc3002.citricjuice.model.board.HomePanel;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.NormaGoal;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 */
public class Player extends AbstractUnit{
    private NormaGoal normaGoal;
    private IPanel cPanel;
    private HomePanel homePanel;

    private PropertyChangeSupport winNotification=new PropertyChangeSupport(this);
    private PropertyChangeSupport meetNotification=new PropertyChangeSupport(this);
    private PropertyChangeSupport homeNotification=new PropertyChangeSupport(this);
    private PropertyChangeSupport nextPanelsNotification=new PropertyChangeSupport(this);
    private PropertyChangeSupport endTurnNotification=new PropertyChangeSupport(this);

  /**
   * Creates a new player.
   *
   * @param name
   *     the player's name.
   * @param hp
   *     the initial and max hit points of the player.
   * @param atk
   *     the base damage the player does.
   * @param def
   *     the base defense of the player.
   * @param evd
   *     the base evasion of the player.
   */
  public Player(final String name, final int hp, int atk, int def, int evd) {
    super(name, hp, atk, def, evd);
    normaGoal=NormaGoal.STARS;
  }

  /**
   * Set atk of this player.
   *
   * @param atk
   *    new atk
   */
  public void setAtk(int atk){
      this.atk=atk;
  }

  /**
   * Set def of this player.
   *
   * @param def
   *    new def
   */
  public void setDef(int def){
      this.def=def;
  }

  /**
   * Set evd of this player.
   *
   * @param evd
   *    new evd
   */
  public void setEvd(int evd){
      this.evd=evd;
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(getName(), getMaxHP(), getAtk(), getDef(), getEvd());
  }



  @Override
  public void attack(@NotNull IUnit unit){
     if(!isKO())
        unit.receivePlayerAttack(this, this.roll()+this.getAtk());
  }

  @Override
  public void counterAttack(@NotNull IUnit unit){
      if (!isKO())
          unit.receivePlayerCounterAttack(this, this.roll()+this.getAtk());
  }

  @Override
  public void receivePlayerAttack(IUnit unit, int newAtk){
      if(!isKO()) {
          defendOrEvade(newAtk);
          trySetValues(unit, 2, 2);
          counterAttack(unit);
      }
  }
  @Override
  public void receivePlayerCounterAttack(IUnit unit, int newAtk){
      if(!isKO()) {
          defendOrEvade(newAtk);
          trySetValues(unit, 2, 2);
      }
  }

  @Override
  public void receiveWildAttack(IUnit unit, int newAtk) {
      if(!isKO()) {
          defendOrEvade(newAtk);
          trySetValues(unit, 2, 2);
          counterAttack(unit);
      }
  }

  @Override
  public void receiveWildCounterAttack(IUnit unit, int newAtk) {
      if(!isKO()) {
          defendOrEvade(newAtk);
          trySetValues(unit, 2, 2);
      }
  }

  @Override
  public void receiveBossAttack(IUnit unit, int newAtk) {
      if(!isKO()) {
          defendOrEvade(newAtk);
          trySetValues(unit, 2, 2);
          counterAttack(unit);
      }
  }

  @Override
  public void receiveBossCounterAttack(IUnit unit, int newAtk) {
      if(!isKO()) {
          defendOrEvade(newAtk);
          trySetValues(unit, 2, 2);
      }
  }

  public NormaGoal getNormaGoal() {
      return normaGoal;
  }

  public void setNormaGoal(NormaGoal normagoal){
      normaGoal=normagoal;
  }

  public void setHomePanel(HomePanel hPanel) {
      homePanel=hPanel;
  }

  public HomePanel getHomePanel() {
      return homePanel;
  }

  public void setCPanel(IPanel panel) {
      if(cPanel!=null){
          cPanel.deletePlayerOn(this);
      }
      cPanel=panel;
      cPanel.addPlayerOn(this);
      if(cPanel.getPlayersOn().size()>1) meetNotification.firePropertyChange("meetSomeone",null,null);
      if(cPanel.equals(homePanel)) homeNotification.firePropertyChange("isOnHomePanel",null,null);
  }

  public IPanel getCPanel()  {
      return cPanel;
  }

  public void normaCheck() {///arreglar
      if(getNormaLevel()==1 && normaGoal==NormaGoal.STARS && getStars()>=10){
          normaClear();
      }
      if(getNormaLevel()==6) winNotification.firePropertyChange("win",null,null);
  }

  public void addWinListener(PropertyChangeListener listener) {
      winNotification.addPropertyChangeListener(listener);
  }

  public void addMeetListener(PropertyChangeListener listener) {
      meetNotification.addPropertyChangeListener(listener);
  }

  public void addHomeListener(PropertyChangeListener listener) {
      homeNotification.addPropertyChangeListener(listener);
  }

  public void addNextPanelsListener(PropertyChangeListener listener){
      nextPanelsNotification.addPropertyChangeListener(listener);
  }

  public void addEndTurnListener(PropertyChangeListener listener){
      endTurnNotification.addPropertyChangeListener(listener);
  }

  public void hasNextPanels() {
      nextPanelsNotification.firePropertyChange("nextPanels", null, null);
  }

  public void endTurn(){
      endTurnNotification.firePropertyChange("endTurn",null,null);
  }

    public void activatePanel() {
      cPanel.activateBy(this);
    }
}
