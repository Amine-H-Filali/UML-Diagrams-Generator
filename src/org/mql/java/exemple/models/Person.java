package org.mql.java.exemple.models;

import org.mql.java.exemple.annotations.PetAnnotation;

@PetAnnotation("CatOwner")
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void ownPet(Animal pet) {
        System.out.println(name + " owns a pet named " + pet.getName());
        pet.makeSound();
    }

    public int getAge() {
        return age;
    }
}