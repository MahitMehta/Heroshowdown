package com.heroshowdown;

public class GoodGuy extends SuperHero {
    private String catchPhrase; 

    public GoodGuy(String name, String job, String superPower, boolean cape, int powerLevel, String catchPhrase) {
        super(name, job, superPower, cape, powerLevel);
        this.catchPhrase = catchPhrase;
    }

    /**
     * 
     * @return true 25% of the time and false 75% of the time to indicate a goodguy power drain
     */

    public boolean powerDrain() {
        int max = 4; 
        int min = 1;
        int randomInt = ((int) (Math.random() * max)) + min; 
        return randomInt == 4; 
    }

    public String getCatchPhrase() { return this.catchPhrase; }

    public int getCurrentPowerLevel() {
        return super.getPowerLevel();
    }

    /** 
     * Overrides the powerLevel accessor method to increase or decrease power level by a random int
     */

    /* @override */
    public int getPowerLevel() {
        int addition = this.powerDrain() ? -this.randomPowerBoostGen() : this.randomPowerBoostGen();
        return super.getPowerLevel() + addition;
    }

    private int randomPowerBoostGen() {
        int max = 10; 
        int min = 1;
        return ((int) (Math.random() * max)) + min;
    }
}