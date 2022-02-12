package com.heroshowdown.BattleScene;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

import com.utils.ImageLoader;

public class Player {
    private final GraphicsContext ctx; 
    private final Canvas canvas; 

    private final ArrayList<Image> framesRight; 
    private final ArrayList<Image> framesLeft; 
    private final ArrayList<Image> framesBack; 
    private final ArrayList<Image> framesFront; 

    private String direction = "front"; 
    private int x; 
    private int y;
    private int speed = 2; 
    private double frame = 0; 
    private boolean playerIntersectingTallGrass = false; 
    private boolean displayBattle = false; 

    public boolean getDisplayBattle() { return this.displayBattle; }

    public Player(GraphicsContext ctx) {
        this.ctx = ctx; 
        this.canvas = this.ctx.getCanvas();

        this.x = 312; 
        this.y = 385;

        this.framesRight = new ArrayList<Image>();

        this.framesRight.add(ImageLoader.loadFXImage("sprites/right1.png"));
        this.framesRight.add(ImageLoader.loadFXImage("sprites/right2.png"));
        this.framesRight.add(ImageLoader.loadFXImage("sprites/right3.png"));

        this.framesLeft = new ArrayList<Image>();

        this.framesLeft.add(ImageLoader.loadFXImage("sprites/left1.png"));
        this.framesLeft.add(ImageLoader.loadFXImage("sprites/left2.png"));
        this.framesLeft.add(ImageLoader.loadFXImage("sprites/left3.png"));

        this.framesBack = new ArrayList<Image>();

        this.framesBack.add(ImageLoader.loadFXImage("sprites/back1.png"));
        this.framesBack.add(ImageLoader.loadFXImage("sprites/back2.png"));
        this.framesBack.add(ImageLoader.loadFXImage("sprites/back3.png"));

        this.framesFront = new ArrayList<Image>();

        this.framesFront.add(ImageLoader.loadFXImage("sprites/front1.png"));
        this.framesFront.add(ImageLoader.loadFXImage("sprites/front2.png"));
        this.framesFront.add(ImageLoader.loadFXImage("sprites/front3.png"));
    }

    public Rectangle2D getPlayerBoundary() {
        return new Rectangle2D(this.x, this.y, 16, 16);
    }

    public void render(boolean playerIntersectingTallGrass) {
        this.playerIntersectingTallGrass = playerIntersectingTallGrass; 
        this.movementListener();

        if (this.direction.equals("right")) {
            this.ctx.drawImage(this.framesRight.get((int) this.frame), this.x, this.y, 16, 16);
        } else if (this.direction.equals("left")) {
            this.ctx.drawImage(this.framesLeft.get((int) this.frame), this.x, this.y, 16, 16);
        } else if (this.direction.equals("back")) {
            this.ctx.drawImage(this.framesBack.get((int) this.frame), this.x, this.y, 16, 16);
        } else if (this.direction.equals("front")) {
            this.ctx.drawImage(this.framesFront.get((int) this.frame), this.x, this.y, 16, 16);
        }
    }

    public void attemptIntersectPokemon() {
        int randomChance = (int) (Math.random() * 50);
        if (randomChance == 9) {
            this.displayBattle = true; 
        }
    }

    public void movementListener() {
        this.canvas.requestFocus();
        this.canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    direction = "back";
                    y -= speed; 
                    if (frame < framesBack.size() - 1) {
                        frame += 0.5;
                    } else frame = 0; 

                    if (playerIntersectingTallGrass) attemptIntersectPokemon();
                } else if (event.getCode() == KeyCode.RIGHT) {
                    direction = "right";
                    x+= speed; 
                    if (frame < framesRight.size() - 1) {
                        frame += 0.5;
                    } else frame = 0; 

                    if (playerIntersectingTallGrass) attemptIntersectPokemon();
                } else if (event.getCode() == KeyCode.DOWN) {
                    direction = "front";
                    y += speed; 
                    if (frame < framesFront.size() - 1) {
                        frame += 0.5;
                    } else frame = 0; 

                    if (playerIntersectingTallGrass) attemptIntersectPokemon();
                } else if (event.getCode() == KeyCode.LEFT) {
                    direction = "left";
                    x-= speed; 
                    if (frame < framesLeft.size() - 1) {
                        frame += 0.5;
                    } else frame = 0;
                    
                    if (playerIntersectingTallGrass) attemptIntersectPokemon();
                }
            }
        });
    }
}
