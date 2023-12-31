package org.mql.java.exemple.models;

import org.mql.java.exemple.enums.Color;

public abstract class Mammal extends Animal implements Moveable {
    private int numberOfLegs;

    public Mammal(String name, int numberOfLegs, Color color) {
        super(name, color);
        this.numberOfLegs = numberOfLegs;
    }

    public abstract void giveBirth();

    public int getNumberOfLegs() {
        return numberOfLegs;
    }
    
    @Override
    public void move() {
        System.out.println("Walking on " + getNumberOfLegs() + " legs");
    }
}

