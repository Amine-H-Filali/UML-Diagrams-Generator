package org.mql.java.exemple.models;

import org.mql.java.exemple.enums.Color;

public abstract class Bird extends Animal implements Moveable {
    private boolean canFly;

    public Bird(String name, boolean canFly, Color color) {
        super(name, color);
        this.canFly = canFly;
    }

    public abstract void fly();

    public boolean canFly() {
        return canFly;
    }
    
    @Override
    public void move() {
        if (canFly()) {
            System.out.println("Flying in the sky");
        } else {
            System.out.println("Walking on the ground");
        }
    }
}