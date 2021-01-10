package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricliquid.gui.nodes.MovableNode;
import com.github.cc3002.citricliquid.gui.nodes.MovableNodeBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class that represents the graphics interface of the game
 */

public class GameGui extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private final GameController controller = new GameController();
    private final Group root = new Group();
    private Label turnLabel;

    private Label diceLabel;

    private Label player1Label;
    private Label player2Label;
    private Label player3Label;
    private Label player4Label;

    private MovableNode sprite1;
    private MovableNode sprite2;
    private MovableNode sprite3;
    private MovableNode sprite4;

    private Button rollButton;
    private Button moveButton;
    private Button attackButton;
    private Button notAttackButton;
    private Button counterAttackButton;
    private Button notCounterAttackButton;
    private Button defendButton;
    private Button evadeButton;
    private Button leftPanelButton;
    private Button rightPanelButton;
    private Button stayAtHomeButton;
    private Button notStayAtHomeButton;

    @Override
    public void start(@NotNull Stage stage) throws FileNotFoundException {
        controller.startNewGame();
        stage.setTitle("99.7% Citric Liquid");
        stage.setResizable(false);
        Scene scene = createScene();
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Create the scene of the game with the sprites, the labels and the buttons.
     * @return the scene
     * @throws FileNotFoundException
     */
    private @NotNull Scene createScene() throws FileNotFoundException {
        Scene scene = new Scene(root, 1280, 720);

        sprite1 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite.png")
                .setPosition(275, 225)
                .setSize(50, 50)
                .build();
        sprite2 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite2.png")
                .setPosition(275, 465)
                .setSize(50, 50)
                .build();
        sprite3 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite3.png")
                .setPosition(515, 465)
                .setSize(50, 50)
                .build();
        sprite4 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "sprite4.png")
                .setPosition(515, 225)
                .setSize(50, 50)
                .build();

        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));

        var board = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "gameboard.png")));
        board.setX(150);
        board.setY(100);
        board.setFitHeight(541);
        board.setFitWidth(541);

        root.getChildren().add(background);
        root.getChildren().add(board);
        root.getChildren().add(sprite1.getNode());
        root.getChildren().add(sprite2.getNode());
        root.getChildren().add(sprite3.getNode());
        root.getChildren().add(sprite4.getNode());

        turnLabel = createLabel(950, 150);
        turnLabel.setFont(new Font(18));
        diceLabel = createLabel(1000, 100);

        player1Label=createLabel(15,100);
        player2Label=createLabel(15,450);
        player3Label=createLabel(750,450);
        player4Label=createLabel(750,100);

        rollButton = createButton("Roll",950,100);
        rollButton.setOnAction(event -> controller.tryToRoll());
        root.getChildren().add(rollButton);

        moveButton = createButton("Move",950,200);
        moveButton.setOnAction(event -> controller.tryToMove());
        root.getChildren().add(moveButton);

        attackButton = createButton("Attack",950,250);
        attackButton.setOnAction(event -> controller.tryToAttack());
        root.getChildren().add(attackButton);

        notAttackButton = createButton("Not attack", 1010,250);
        notAttackButton.setOnAction(event -> controller.tryToNotAttack());
        root.getChildren().add(notAttackButton);

        counterAttackButton = createButton("Counter attack",950,275);
        counterAttackButton.setOnAction(event -> controller.tryToCounterAttack());
        root.getChildren().add(counterAttackButton);

        notCounterAttackButton = createButton("Not counter attack", 1050,275);
        notCounterAttackButton.setOnAction(event -> controller.tryToNotCounterAttack());
        root.getChildren().add(notCounterAttackButton);

        evadeButton = createButton("Evade",950,300);
        evadeButton.setOnAction(event -> controller.tryToEvade());
        root.getChildren().add(evadeButton);

        defendButton = createButton("Defend",1010,300);
        defendButton.setOnAction(event -> controller.tryToDefend());
        root.getChildren().add(defendButton);

        leftPanelButton= createButton("Go to left",1100,200);
        leftPanelButton.setOnAction(event -> controller.tryToGoLeft());
        root.getChildren().add(leftPanelButton);

        rightPanelButton= createButton("Go to right",1010,200);
        rightPanelButton.setOnAction(event -> controller.tryToGoRight());
        root.getChildren().add(rightPanelButton);

        stayAtHomeButton= createButton("Stay at home",950,350);
        stayAtHomeButton.setOnAction(event -> controller.tryToStayAtHome());
        root.getChildren().add(stayAtHomeButton);

        notStayAtHomeButton= createButton("Not stay at home",1050,350);
        notStayAtHomeButton.setOnAction(event -> controller.tryToNotStayAtHome());
        root.getChildren().add(notStayAtHomeButton);

        startAnimator();
        return scene;
    }


    /**
     * Update player values and move the sprites.
     */
    private void startAnimator() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                int numberPlayer = controller.getTurn()+1;
                int numberDice = controller.getNumberOfDice();
                turnLabel.setText("Currently player: " + numberPlayer);
                diceLabel.setText("Dice: " + numberDice);

                player1Label.setText("Player 1 \n"+
                        "HP : "+controller.getCHP(0)+ "\n"+
                        "Atk: "+controller.getAtk(0)+ "\n"+
                        "Def: "+controller.getDef(0)+ "\n"+
                        "Evs: "+controller.getEvs(0)+ "\n"+
                        "Norma: "+controller.getNorma(0)+ "\n"+
                        "Stars: "+controller.getStars(0)+ "\n"+
                        "Wins : "+controller.getWins(0)
                );
                player2Label.setText("Player 2 \n"+
                        "HP : "+controller.getCHP(1)+ "\n"+
                        "Atk: "+controller.getAtk(1)+ "\n"+
                        "Def: "+controller.getDef(1)+ "\n"+
                        "Evs: "+controller.getEvs(1)+ "\n"+
                        "Norma: "+controller.getNorma(1)+ "\n"+
                        "Stars: "+controller.getStars(1)+ "\n"+
                        "Wins : "+controller.getWins(1)
                );
                player3Label.setText("Player 3 \n"+
                        "HP : "+controller.getCHP(2)+ "\n"+
                        "Atk: "+controller.getAtk(2)+ "\n"+
                        "Def: "+controller.getDef(2)+ "\n"+
                        "Evs: "+controller.getEvs(2)+ "\n"+
                        "Norma: "+controller.getNorma(2)+ "\n"+
                        "Stars: "+controller.getStars(2)+ "\n"+
                        "Wins : "+controller.getWins(2)
                );
                player4Label.setText("Player 4 \n"+
                        "HP : "+controller.getCHP(3)+ "\n"+
                        "Atk: "+controller.getAtk(3)+ "\n"+
                        "Def: "+controller.getDef(3)+ "\n"+
                        "Evs: "+controller.getEvs(3)+ "\n"+
                        "Norma: "+controller.getNorma(3)+ "\n"+
                        "Stars: "+controller.getStars(3)+ "\n"+
                        "Wins : "+controller.getWins(3)
                );

                sprite1.moveLeft(155+controller.xPos(0)*60-sprite1.getHPos());
                sprite1.moveDown(100+controller.yPos(0)*60-sprite1.getVPos());
                sprite2.moveLeft(155+controller.xPos(1)*60-sprite2.getHPos());
                sprite2.moveDown(100+controller.yPos(1)*60-sprite2.getVPos());
                sprite3.moveLeft(155+controller.xPos(2)*60-sprite3.getHPos());
                sprite3.moveDown(100+controller.yPos(2)*60-sprite3.getVPos());
                sprite4.moveLeft(155+controller.xPos(3)*60-sprite4.getHPos());
                sprite4.moveDown(100+controller.yPos(3)*60-sprite4.getVPos());


            }
        };
        timer.start();
    }

    /**
     * Create a label on the position (xPos,yPos) and add to the root
     * @param xPos
     * @param yPos
     * @return the label
     */
    private @NotNull Label createLabel(int xPos, int yPos) {
        Label label = new Label();
        label.setLayoutX(xPos);
        label.setLayoutY(yPos);
        root.getChildren().add(label);
        return label;
    }

    /**
     * Create a button with a text:txt on the position(xPos,yPos)
     * @param txt
     * @param xPos
     * @param yPos
     * @return the button
     */
    private @NotNull Button createButton(String txt, int xPos, int yPos) {
        Button button=new Button(txt);
        button.setLayoutX(xPos);
        button.setLayoutY(yPos);
        return button;
    }
}
