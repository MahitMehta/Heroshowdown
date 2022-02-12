package com.heroshowdown.BattleScene;

import com.utils.ImageLoader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BattleScene {
    private final AudioManager audio;
    private final GraphicsContext ctx;
    private Stage rootStage;  
    private Image background; 
    private HealthBar enemyHB; 
    private HealthBar heroHB; 
    private int stage = 0; 
    private Button button; 

    private int heroPowerLevel;
    private int enemyPowerLevel;

    public BattleScene(GraphicsContext ctx) {
        this.audio = new AudioManager();
        this.ctx = ctx; 
        this.heroHB = new HealthBar(ctx, 375, 325);
        this.enemyHB = new HealthBar(ctx, 50, 75);

        try {
            this.background = ImageLoader.loadFXImage("maps/battlefield.png");
        } catch (Exception e) {
            this.background = null; 
        }
    }

    public void backgroundCanvas() {
        try {
            this.ctx.drawImage(this.background, 0, 0, 640, 480);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void init(Stage stage) {
        this.rootStage = stage; 
    }

    public void messageConsole() {
        this.ctx.setFill(Color.WHITE);
        this.ctx.fillRoundRect(20, 360, 600, 100, 5, 5);

        this.ctx.setFill(Color.rgb(24, 24, 24));
        this.ctx.fillRoundRect(20 + 2, 360 + 2, 500 - 4, 100 - 4, 5, 5);

        this.ctx.setFill(Color.rgb(24, 24, 24));
        this.ctx.fillRoundRect(20 + 2 + 500, 360 + 2, 95, 100 - 4, 5, 5);

        switch (this.stage) {
            case 0: {
                this.ctx.setFill(Color.WHITE);
                this.ctx.fillText("Ultimate Showdown Takes Place. Aardart Meets Bamboon.", 30, 380);
            }
        }

        button.requestFocus();
        // System.out.println(this.button.isFocused());
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("clicked");
                stage += 1; 
            }
        });
    }

    public void render(int heroPowerLevel, int enemyPowerLevel, Button button) {
        this.heroPowerLevel = heroPowerLevel; 
        this.enemyPowerLevel = enemyPowerLevel; 
        this.button = button; 

        this.audio.battleMusic.play();
        this.backgroundCanvas();

        this.heroHB.render(10);
        this.enemyHB.render(5);

        this.ctx.setFill(Color.rgb(165, 234, 136));
        this.ctx.fillOval(35, 315, 200, 85);

        this.ctx.setFill(Color.rgb(165, 234, 136));
        this.ctx.fillOval(375, 135, 200, 85);

        this.ctx.drawImage(ImageLoader.loadFXImage("sprites/aardart-back.png"), 85, 265, 100, 100);
        this.ctx.drawImage(ImageLoader.loadFXImage("sprites/bamboon-front.png"), 425, 95, 100, 100);

        this.messageConsole();
    }
}
