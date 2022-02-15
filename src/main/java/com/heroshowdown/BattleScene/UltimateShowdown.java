package com.heroshowdown.BattleScene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
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

    // Objects
    private final Button button; 

    // Characters 
    private final Player player; 
    private final Pokemon goodPokemon; 
    private final Pokemon badPokemon; 

    // Scenes
    private final TallGrassScene tallGrassScene; 
    private final BattleScene battleScene; 

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

        Parameters params = getParameters();

        List<String> powerLevels = params.getUnnamed(); 
        
        this.goodPokemon.setPowerLevel(Integer.parseInt(powerLevels.get(0)));
        this.badPokemon.setPowerLevel(Integer.parseInt(powerLevels.get(1))); 

        AnimationTimer timer = new AnimationTimer() {
            /** @Override */
            public void handle(long now) {
                StackPane pane = new StackPane();
                Scene scene = new Scene(pane);

                pane.getChildren().add(canvas);

                if (!player.getDisplayBattle()) {
                    tallGrassScene.render();
                    player.render(tallGrassScene.playerIntersectingTallGrass());
                } else {
                    button.setTranslateX(250);
                    button.setTranslateY(170);

                    pane.getChildren().add(button);

                    tallGrassScene.getFieldMusic().stop();
                    battleScene.render(button);
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
        this.goodPokemon = new Pokemon("Aardart", "sprites/aardart-back.png");
        this.badPokemon = new Pokemon("Bamboom", "sprites/bamboon-front.png");

        this.tallGrassScene = new TallGrassScene(ctx, this.player);
        this.battleScene = new BattleScene(ctx, goodPokemon, badPokemon);

        this.button = new Button("Next");
    }       

    public static void main(String[] args, String heroPowerLevel, String enemyPowerLevel) {
        Application.launch(UltimateShowdown.class, heroPowerLevel, enemyPowerLevel);
    }

    /** @override */
    public void stop(){}
}   
