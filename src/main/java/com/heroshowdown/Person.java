package com.heroshowdown;
abstract public class Person {
    private String name; 
    private String job; 

    public Person(String name, String job) {
        this.name = name; 
        this.job = job; 
    }

    public String getName() {return this.name; }

    public String getJob() { return this.job; }
}