package org.mql.java.exemple.models;

import org.mql.java.exemple.enums.Color;

public abstract class Animal {
    private String name;
    private Color color;

    public Animal(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public abstract void makeSound();

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
