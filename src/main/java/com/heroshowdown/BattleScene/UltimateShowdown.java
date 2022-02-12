package com.heroshowdown.BattleScene;
import java.util.Collection;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class UltimateShowdown extends Application {
    private Stage rootStage; 
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private Canvas canvas; 
    private GraphicsContext ctx; 

    // Power Levels
    private int heroPowerLevel; 
    private int enemyPowerLevel; 

    // Characters 
    private final Player player; 

    // Scenes
    private final TallGrassScene tallGrassScene; 
    private final BattleScene battleScene; 

    public void setEnemyPowerLevel(int level) {
        this.enemyPowerLevel = level; 
    }

    public void setHeroPowerLevel(int level) {
        this.heroPowerLevel = level; 
    }

    /** @Override */
    public void start(final Stage rootStage) throws Exception {
        this.rootStage = rootStage;
        this.rootStage.setTitle("Ultimate Showdown");
        this.rootStage.show();
        this.rootStage.setMinHeight(this.HEIGHT);
        this.rootStage.setMinWidth(this.WIDTH);
        this.rootStage.setResizable(false);
        this.tallGrassScene.init(this.rootStage);
        this.battleScene.init(this.rootStage);

        AnimationTimer timer = new AnimationTimer() {
            /** @Override */
            public void handle(long now) {
                ctx.clearRect(0, 0, 640, 480);
                // render

                StackPane pane = new StackPane();
                Scene scene = new Scene(pane);

                Button button = new Button("Next");

                button.setTranslateX(250);
                button.setTranslateY(170);

                pane.getChildren().addAll(canvas);
                if (player.getDisplayBattle()) {
                    pane.getChildren().add(button);
                }

                if (!player.getDisplayBattle()) {
                    tallGrassScene.render();
                    player.render(tallGrassScene.playerIntersectingTallGrass());
                } else {
                    button.requestFocus();
                    tallGrassScene.getAudioManager().fieldMusic.stop();
                    battleScene.render(heroPowerLevel, enemyPowerLevel, button);
                }
            
                rootStage.setScene(scene);
            }
        };

        timer.start();
    }

    public UltimateShowdown() {
        this.canvas = new Canvas(640.0f, 480.0f);
        this.ctx = canvas.getGraphicsContext2D();
    
        this.player = new Player(ctx);
        this.tallGrassScene = new TallGrassScene(ctx, this.player);
        this.battleScene = new BattleScene(ctx);
    }       

    public static void main(String[] args) {
        Application.launch(args);
        new UltimateShowdown();
    }

    /** @override */
    public void stop(){
        //System.out.println("\nArraylists...\n");
    }
}   
