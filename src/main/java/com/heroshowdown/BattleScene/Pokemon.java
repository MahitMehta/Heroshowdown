package com.heroshowdown.BattleScene;
import com.utils.ImageLoader;

import javafx.scene.image.Image;

public class Pokemon {
    private String name; 
    private int health = 100; 
    private int powerLevel = 0; 
    private final Image sprite; 
    
    public Pokemon(String name, String spritePath) {
        this.name = name; 
        this.sprite = ImageLoader.loadFXImage(spritePath);
    }

    public Image getSprite() {
        return this.sprite; 
    }

    public String getPokemonName() {
        return this.name;
    }

    public int getHealth() {
        return this.health; 
    }

    public int getPowerLevel() {
        return this.powerLevel; 
    }
    
    /**
     * 
     * @param health New Power Level
     * @return Updated Power Level
     */

    public int setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel; 
        return this.powerLevel;
    }

    /**
     * 
     * @param health New Health
     * @return Updated Health
     */

    public int setHealth(int health) {
        this.health = health; 
        return this.health;
    }
}
