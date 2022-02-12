package com.heroshowdown;
import java.util.ArrayList;
import java.util.Scanner;
import com.heroshowdown.BattleScene.UltimateShowdown;

public class HeroShowdown {
    private ArrayList<Person> townsPeople, safePeople, unfortunateSouls;
    public static Scanner scan = new Scanner(System.in);
    private boolean showdownEventOccured = false; 

    public HeroShowdown(String[] args) {
        this.townsPeople = new ArrayList<Person>();
        this.safePeople = new ArrayList<Person>();
        this.unfortunateSouls = new ArrayList<Person>();

        // Normal Guys
        this.townsPeople.add(new NormalGuy("Liam", "Receptionist"));
        this.townsPeople.add(new NormalGuy("Noah", "Account Executive"));
        this.townsPeople.add(new NormalGuy("Oliver", "SEO Manager"));
        this.townsPeople.add(new NormalGuy("Elijah", "Brand Manager"));
        this.townsPeople.add(new NormalGuy("William", "Public Relations"));
        this.townsPeople.add(new NormalGuy("Tim Cook", "Apple CEO"));
        this.townsPeople.add(new NormalGuy("Elon Musk", "CEO of Tesla"));
        this.townsPeople.add(new NormalGuy("Daniel", "Real Estate Broker"));
        this.townsPeople.add(new NormalGuy("Henry", "Investor"));
        this.townsPeople.add(new NormalGuy("Alexander", "Electrician"));

        // Good Guys
        Person aardart = new GoodGuy("Aardart", "Lighting Boss", "Thunder Jolt", false, 10, "Pika Pika");
        this.townsPeople.add(aardart);

        // Bad Guys 
        Person bamboon = new BadGuy("Bamboon", "Destroy Aardart", "Teravolt", false, 15, "Mwahahahaha");
        this.townsPeople.add(bamboon);

        System.out.println("\nWelcome to Hero Showdown by Mahit Mehta!\n");
        System.out.println("To play, press ENTER after each statement.");

        while (this.continueGame()) {
            this.roundInteraction(args);
            if (this.continueGame()) {
                System.out.print("\nPress any key to continue: ");
                scan.nextLine();
                System.out.println("");
            }
        }
    }

    private boolean continueGame() {
        return this.townsPeople.size() > 1 && !this.showdownEventOccured;
    }

    public static void main(String[] args) {
        new HeroShowdown(args);
    }

    private void roundInteraction(String[] args) {
        Person p1 = this.townsPeople.remove((int) (Math.random() * this.townsPeople.size()));
        Person p2 = this.townsPeople.remove((int) (Math.random() * this.townsPeople.size()));

        if (p1 instanceof NormalGuy && p2 instanceof NormalGuy) {
            this.normalGuyInteraction((NormalGuy) p1, (NormalGuy) p2);

            this.townsPeople.add(p1);
            this.townsPeople.add(p2);
        } else if (p1 instanceof SuperHero && p2 instanceof SuperHero) {
            if (p1 instanceof GoodGuy && p2 instanceof GoodGuy) {
            } else if (p1 instanceof BadGuy && p2 instanceof BadGuy) {
            } else {
                if (p1 instanceof GoodGuy) {
                    this.ultimateShowDown((GoodGuy) p1, (BadGuy) p2, args);
                } else {
                    this.ultimateShowDown((GoodGuy) p2, (BadGuy) p1, args);
                }
            }
        } else if (p1 instanceof SuperHero && p2 instanceof NormalGuy) {
            if (p1 instanceof GoodGuy) {
                this.goodGuyWithNormalGuyInteraction((GoodGuy) p1, (NormalGuy) p2);
                this.safePeople.add(p2);
                this.townsPeople.add(p1);
            } else {
                this.badGuyWithNormalGuyInteraction((BadGuy) p1, (NormalGuy) p2);
                if (this.resurrectedUnfortunateSoul((NormalGuy) p2)) {
                    this.townsPeople.add(p2);
                } else {
                    this.unfortunateSouls.add(p2);
                }
                this.townsPeople.add(p1);
            }
        } else if (p1 instanceof NormalGuy && p2 instanceof SuperHero) {
            if (p2 instanceof GoodGuy) {
                this.goodGuyWithNormalGuyInteraction((GoodGuy) p2, (NormalGuy) p1);
                this.safePeople.add(p1);
                this.townsPeople.add(p2);
            } else {
                this.badGuyWithNormalGuyInteraction((BadGuy) p2, (NormalGuy) p1);
                if (this.resurrectedUnfortunateSoul((NormalGuy) p1)) {
                    this.townsPeople.add(p1);
                } else {
                    this.unfortunateSouls.add(p1);
                }
                this.townsPeople.add(p2);
            }
        }
    }

    private boolean resurrectedUnfortunateSoul(NormalGuy p1) {
        int max = 4; 
        int min = 1;
        int randomInt = ((int) (Math.random() * max)) + min; 
        
        if (randomInt == 4) {
            System.out.print("\nLucks in the air, " + p1.getName() + " was resurrected!");
            scan.nextLine();
            System.out.println();
        }
        return randomInt == 4; 
    }

    private void ultimateShowDown(GoodGuy p1, BadGuy p2, String[] args) {
        System.out.println("\nUltimate showdown commences\n");

        System.out.print("The Super Hero's Power Level is at " + p1.getCurrentPowerLevel());
        scan.nextLine(); 
        System.out.print("\nThe Super Villian's Power Level is at " + p2.getPowerLevel());
        scan.nextLine(); 

        if (p2.getPowerLevel() > p1.getCurrentPowerLevel()) {
            System.out.print("\n" + p2.getEvilLaugh() + ", " + p2.getName() + " Wins!");
        } else if (p2.getPowerLevel() < p1.getCurrentPowerLevel()) {
            System.out.print("\n" + p1.getName() + " Wins and Saves the Town Once Again!");
        } else {
            System.out.print("\n" + p1.getName() + " uses all his strength and beats " + p2.getName() + ".");
        }
        scan.nextLine(); 

        System.out.println("\nArraylists: ");
        System.out.println("\nTownsPeople: " + this.townsPeople);
        System.out.println("\nSafe People: " + this.safePeople);
        System.out.println("\nUnfortunate Souls: " + this.unfortunateSouls + "\n");
        // System.out.println(p1.getCurrentPowerLevel());
        // System.out.println(p2.getPowerLevel());
        this.showdownEventOccured = true;

        UltimateShowdown.main(args); 
    }

    private void badGuyWithNormalGuyInteraction(BadGuy p1, NormalGuy p2) {
        System.out.print("\n" + p1.getName() + " uses " + p1.getSuperPower() + "!");
        scan.nextLine();
        System.out.print("\n" + p2.getName() + " is eliminated. RIP");
        scan.nextLine();
        System.out.print("\n" + p1.getEvilLaugh());
        scan.nextLine();
        int newPowerLevel = p1.getPowerLevel() + (int)(Math.random() * 5) + 1;
        p1.powerLevelModification( newPowerLevel );
        System.out.print("\n" + p1.getName() + " levels up to " + p1.getPowerLevel());
        scan.nextLine();
    }

    private void goodGuyWithNormalGuyInteraction(GoodGuy p1, NormalGuy p2) {
        System.out.print("\nDon't Worry " + p2.getName() + ", I'm here to save you!");
        scan.nextLine();
        System.out.print("\n" + p1.getName() + " quickly saves the normal person from evil.");
        scan.nextLine();
        int currentLevel = p1.getCurrentPowerLevel();
        int newLevel = p1.getPowerLevel();
        p1.powerLevelModification(newLevel);
        if (newLevel >= currentLevel) {
            System.out.print("\n" + p1.getName() + " levels up to " + newLevel);
        } else {
            System.out.print("\nOh No... " + p1.getName() + " is tired, power level degrades to " + newLevel);
        }       
        scan.nextLine();
    }

    private void normalGuyInteraction(NormalGuy p1, NormalGuy p2) {
        String[] activities = {
            "Let's go to the movies.",
            "Let's go to a waterpark.",
            "Let's go to France!",
        };

        System.out.print("\nHi, my name is " + p1.getName() + ". What is your name? ");
        scan.nextLine();
        System.out.print("\nMy name is " + p2.getName() + ". Nice to Meet You!");
        scan.nextLine();

        String activity = this.getActivity(activities);
        System.out.print("\n" + activity);
        scan.nextLine();
    }

    private String getActivity(String[] activities) {
        return activities[(int) (Math.random() * activities.length)];
    }
}
