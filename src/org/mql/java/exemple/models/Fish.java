package org.mql.java.exemple.models;

import org.mql.java.exemple.enums.Color;

public class Fish extends Animal implements Moveable,SoundProducible {
    private String habitat;

    public Fish(String name, String habitat, Color color) {
        super(name, color);
        this.habitat = habitat;
    }

    @Override
    public void makeSound() {
        System.out.println("Blub! Blub!");
    }

    public String getHabitat() {
        return habitat;
    }

    public void swim() {
        System.out.println("Swimming in water");
    }
    
    @Override
    public void move() {
        swim();
    }
}

   