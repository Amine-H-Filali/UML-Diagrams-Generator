package org.mql.java.exemple.models;

import org.mql.java.exemple.enums.Color;

public class Dog extends Mammal implements SoundProducible {
    private String breed;

    public Dog(String name, int numberOfLegs, String breed, Color color) {
        super(name, numberOfLegs, color);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }

    @Override
    public void giveBirth() {
        System.out.println("Giving birth to puppies");
    }

    public String getBreed() {
        return breed;
    }
}
