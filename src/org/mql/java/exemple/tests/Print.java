package org.mql.java.exemple.tests;

import org.mql.java.exemple.models.Animal;
import org.mql.java.exemple.models.Bird;
import org.mql.java.exemple.models.Fish;
import org.mql.java.exemple.models.Mammal;

public class Print {

	public static void printDetails(Animal animal) {
		
		 System.out.println("Name: " + animal.getName());
	        System.out.println("Color: " + animal.getColor());
	        if (animal instanceof Mammal) {
	            System.out.println("Number of Legs: " + ((Mammal) animal).getNumberOfLegs());
	        }
	        if (animal instanceof Bird) {
	            System.out.println("Can Fly: " + ((Bird) animal).canFly());
	        }
	        if (animal instanceof Fish) {
	            System.out.println("Habitat: " + ((Fish) animal).getHabitat());
	        }
	        System.out.println();
		
	}

}
