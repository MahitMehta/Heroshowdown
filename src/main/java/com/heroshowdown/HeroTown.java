package com.heroshowdown;
import java.util.ArrayList;
 
public class HeroTown
{
  ArrayList<Person> townsPeople;
 
  public HeroTown()
  {
    townsPeople = new ArrayList<Person>();
 
    townsPeople.add(new BadGuy("Xenon","Actuary","Mega Punch",true,7,"Bwha Hee Hee Ha"));
    townsPeople.add(new NormalGuy("Arya Johnson", "Plumber"));
    townsPeople.add(new GoodGuy("Electrogal", "Electrician", "Lightning Fingers", true, 1, "Who's needs a Jolt of energy!"));
   System.out.println("\n Interactions");
    interaction();
  }
 
 public static void main(String[] args) {
     new HeroTown();
 }
 
 
  public void interaction()
  {
 
   // This part should run with people of any type
    System.out.print(townsPeople.get(0).getName() +" has a problem with his sink, so he calls ");
    System.out.println(townsPeople.get(1).getName()+" the "+townsPeople.get(1).getJob()+".");
 
    System.out.print(townsPeople.get(1).getName() +" asks "+ townsPeople.get(0).getName());
    System.out.print(", \"Hey "+townsPeople.get(0).getName()+", what do you do for a living?\"\n");
    System.out.print(townsPeople.get(0).getName()+" replies, \"I'm an "+townsPeople.get(0).getJob()+".\"\n");
 
  
 
   // This part is SuperHero specific
    if(townsPeople.get(2) instanceof GoodGuy) {
     int tempPower = ((SuperHero)townsPeople.get(2)).getPowerLevel();
     System.out.print((townsPeople.get(2)).getName()+" bursts through the door and says, my power level is down to "+tempPower );
      System.out.println(", \""+((GoodGuy)townsPeople.get(2)).getCatchPhrase()+". ");
 
     System.out.println("After "+ ((SuperHero)townsPeople.get(2)).getSuperPower()+" I feel a bit better...");
 
     // Power up
     ((SuperHero)townsPeople.get(2)).powerLevelModification(tempPower+10);
 
     System.out.println("My power is now "+ ((SuperHero)townsPeople.get(2)).getPowerLevel()+"!\n");
 
    
   }
 
    if(townsPeople.get(0) instanceof BadGuy){
     System.out.print(townsPeople.get(0).getName()+" says, ");
     System.out.print(((SuperHero)townsPeople.get(0)).wearsCape()?"\"Behold my cape blowing in the breeze!":"\"Has anyone seen my cape?");
 
      System.out.println("  "+((BadGuy)townsPeople.get(0)).getEvilLaugh()+".\"\n\n");
 
 
 
   }
  }
 
}