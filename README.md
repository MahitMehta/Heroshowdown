<img width="636" alt="Screen Shot 2022-02-11 at 11 44 15 PM" src="https://user-images.githubusercontent.com/73650235/153696940-09ff2fe3-bf22-42a9-9c30-fa2b4997be7d.png">


# Build Project

1. mvn clean install
2. Run App.java, located in src/main/java/com/heroshowdown/App.java

# About

This is a Maven and JavaFX project for AP Computer Science Class 2021-22 and it is 
suppose to demonstrate skills of Polymorphism. 

# Modifications

The first modification I added was letter F which documents, "Write a powerDrain() method for either GoodGuy or BadGuy  (or both) and invent reasons why their power might be drained".

To achieve this I created a method called "powerDrain" inside of the GoodGuy class which returns a boolean true with a theoretical propability of 25%. If the method returns true, then when a Good Guy saves a Normal Guy, then the Good Guy experiences a power drain instead of a power boost.

The second modification I added was letter D which documents, "Instead of just using the Scanner to continue or quit, change it to serve another purpose in the simulation". The alternative purpose I gave the Scanner was to implement it so an unfortunate soul can be revived. The way it works is that after a normal guy dies by a villian, there is a 25% chance that he may be lucky enough to come back. If so, then the user is given a choice to revive him by saying yes or no.

To make this functionality, I created a "resurrectedUnfortunateSoul" method which basically has a 25% chance of giving the user a choice to revive the unfortunate soul by using Math.random().


<img width="619" alt="Screen Shot 2022-02-11 at 11 44 33 PM" src="https://user-images.githubusercontent.com/73650235/153696930-8969bd11-13f9-4c9f-a9fa-81551233661e.png">

# Special Sauce

The Special Sauce I decided to include was making a JavaFX application open up when the Good Guy and Bad Guy face off. While I was not able to finish the mini game scene I wanted to create, I did manage to create a map where a Pokemon battler can search the Super Villian in tall grass and eventually engage in a battle scene. 

# Update 

As of 02/14/2022, I added a few finishing touches to the graphical portion of this project just to make the experience complete.