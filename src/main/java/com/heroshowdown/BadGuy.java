package com.heroshowdown;
public class BadGuy extends SuperHero {
    private String evilLaugh; 
    
    public BadGuy(String name, String job, String superPower, boolean cape, int powerLevel, String evilLaugh) {
        super(name, job, superPower, cape, powerLevel);
        this.evilLaugh = evilLaugh; 
    }

    public String getEvilLaugh() { return this.evilLaugh; }
}
