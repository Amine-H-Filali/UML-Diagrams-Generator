package org.mql.java.exemple.models;

import org.mql.java.exemple.enums.Color;

public class Eagle extends Bird implements SoundProducible {
    private double wingspan;

    public Eagle(String name, boolean canFly, double wingspan, Color color) {
        super(name, canFly, color);
        this.wingspan = wingspan;
    }

    @Override
    public void makeSound() {
        System.out.println("Screech! Screech!");
    }

    @Override
    public void fly() {
        System.out.println("Soaring high in the sky");
    }

    public double getWingspan() {
        return wingspan;
    }
}