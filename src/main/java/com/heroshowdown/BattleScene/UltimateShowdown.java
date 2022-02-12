package com.heroshowdown.BattleScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.StackPane;

public class UltimateShowdown extends Application {
    private Stage rootStage; 
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private Canvas canvas; 
    private GraphicsContext ctx; 

    // Characters 
    private final Player player; 

    // Scenes
    private final TallGrassScene tallGrassScene; 

    /** @Override */
    public void start(final Stage rootStage) throws Exception {
        this.rootStage = rootStage;
        this.rootStage.setTitle("Ultimate Showdown");
        this.rootStage.show();
        this.rootStage.setMinHeight(this.HEIGHT);
        this.rootStage.setMinWidth(this.WIDTH);
        this.rootStage.setResizable(false);
        this.tallGrassScene.init(this.rootStage);

        AnimationTimer timer = new AnimationTimer() {
            /** @Override */
            public void handle(long now) {
                ctx.clearRect(0, 0, 640, 480);
                // render

                StackPane pane = new StackPane();
                pane.getChildren().add(canvas);
                Scene scene = new Scene(pane);

                tallGrassScene.render();
                player.render();

                rootStage.setScene(scene);
            }
        };

        timer.start();
    }

    public UltimateShowdown() {
        this.canvas = new Canvas(640.0f, 480.0f);
        this.ctx = canvas.getGraphicsContext2D();
    
        this.tallGrassScene = new TallGrassScene(ctx);
        this.player = new Player(ctx);
    }       

    public static void main(String[] args) {
        Application.launch(args);
        new UltimateShowdown();
    }
}   
