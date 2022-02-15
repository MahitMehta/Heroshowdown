package com.heroshowdown.BattleScene;

import com.utils.ImageLoader;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BattleScene {
    private final GraphicsContext ctx;
    private Stage rootStage;  
    private Image background; 
    private HealthBar enemyHB; 
    private HealthBar heroHB; 
    private int stage = 0; 
    private Button button; 

    private Pokemon goodPokemon; 
    private Pokemon badPokemon; 
    private boolean heroTurn;

    private final MessageConsole messageConsole; 
    private String attackMessage;

    private final MediaPlayer battleMusic = AudioManager.battleMusic;
    private final MediaPlayer victoryMusic = AudioManager.victoryMusic;

    public BattleScene(GraphicsContext ctx, Pokemon goodPokemon, Pokemon badPokemon) {
        this.ctx = ctx; 
        this.messageConsole = new MessageConsole(ctx, 20, 360, true);
        this.heroHB = new HealthBar(ctx, 50, 75);
        this.enemyHB = new HealthBar(ctx, 375, 325); 
        this.goodPokemon = goodPokemon; 
        this.badPokemon = badPokemon; 

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

    public void playAudio() {
        if (this.goodPokemon.getHealth() <= 0 || this.badPokemon.getHealth() <= 0) {
            this.battleMusic.stop();
            this.victoryMusic.play();
        } else {
            this.battleMusic.play();
            this.victoryMusic.stop();
        }
    }

    public void init(Stage stage) {
        this.rootStage = stage; 
    }

    public void messageConsole() {
        switch (this.stage) {
            case 0: {
                this.messageConsole.setMessage("Ultimate Showdown Takes Place. " + this.goodPokemon.getPokemonName() + " Meets " + this.badPokemon.getPokemonName() + ".");
                break;
            } case 1: {
                this.messageConsole.setMessage(this.attackMessage);
                break; 
            } case 2: {
                if (this.goodPokemon.getHealth() <= 0) {
                    this.messageConsole.setMessage(this.badPokemon.getPokemonName() + " beats " + this.goodPokemon.getPokemonName() + "!");
                } else {
                    this.messageConsole.setMessage( this.goodPokemon.getPokemonName() + " beats " + this.badPokemon.getPokemonName() + "!");
                }
              
                button.setText("Exit");
                break; 
            }
        }

        this.button.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                switch (stage) {
                    case 0: {
                        stage += 1;
                        attack();
                        break;
                    } case 1: {
                        attack();
                        break; 
                    } case 2: {
                        rootStage.close();
                        break; 
                    }
                } 
            }
        });

        this.messageConsole.render();
    }

    public void attack() {
        int attack, maxDamage; 

        if (heroTurn) {
            maxDamage = goodPokemon.getPowerLevel() >= badPokemon.getHealth() && badPokemon.getPowerLevel() > goodPokemon.getPowerLevel() ? badPokemon.getHealth() : goodPokemon.getPowerLevel();
            attack = generateAttack(0, maxDamage);    
            int badPokemonHealth = badPokemon.getHealth() - attack;
            attackMessage = goodPokemon.getPokemonName() + " attacks " + badPokemon.getPokemonName() + " and does " + attack + " hit points.";
            badPokemon.setHealth(badPokemonHealth);
            if (badPokemonHealth <= 0) stage = 2; 
        } else {
            maxDamage = badPokemon.getPowerLevel() >= goodPokemon.getHealth() && goodPokemon.getPowerLevel() >= badPokemon.getPowerLevel() ? goodPokemon.getHealth() : badPokemon.getPowerLevel();
            attack = generateAttack(0, maxDamage);    
            int goodPokemonHealth = goodPokemon.getHealth() - attack; 
            attackMessage = badPokemon.getPokemonName() + " attacks " + goodPokemon.getPokemonName() +" and does " + attack + " hit points";
            goodPokemon.setHealth(goodPokemonHealth);
            if (goodPokemonHealth <= 0) stage = 2; 
        }

        heroTurn = !heroTurn;
    }

    public int generateAttack(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public void render(Button button) {
        this.enemyHB.setPercent(this.badPokemon.getHealth());
        this.heroHB.setPercent(this.goodPokemon.getHealth());

        this.button = button; 

        this.playAudio();
        this.backgroundCanvas();

        this.heroHB.render(this.goodPokemon.getPowerLevel());
        this.enemyHB.render(this.badPokemon.getPowerLevel());

        this.ctx.setFill(Color.rgb(165, 234, 136));
        this.ctx.fillOval(35, 315, 200, 85);

        this.ctx.setFill(Color.rgb(165, 234, 136));
        this.ctx.fillOval(375, 135, 200, 85);

        this.ctx.drawImage(goodPokemon.getSprite(), 85, 265, 100, 100);
        this.ctx.drawImage(badPokemon.getSprite(), 425, 95, 100, 100);

        this.messageConsole();
    }
}
