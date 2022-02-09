package com.heroshowdown;

public class SuperHero extends Person {
    private String superPower; 
    private boolean cape; 
    private int powerLevel; 

    public SuperHero(String name, String job, String superPower, boolean cape, int powerLevel) {
        super(name, job);
        this.superPower = superPower;
        this.cape = cape; 
        this.powerLevel = powerLevel; 
    }

    public String getSuperPower() {return this.superPower; }

    public boolean wearsCape() { return this.cape; }

    public int getPowerLevel() {return this.powerLevel; }; 

    public void powerLevelModification(int newLevel) {
        this.powerLevel = newLevel;
    }
}
