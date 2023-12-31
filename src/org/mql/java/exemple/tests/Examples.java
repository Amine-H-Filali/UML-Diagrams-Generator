package org.mql.java.exemple.tests;

import org.mql.java.exemple.enums.Color;
import org.mql.java.exemple.models.*;
import static org.mql.java.exemple.tests.Print.printDetails;


public class Examples {

	
	
	public Examples() {
		exp01();
	}
	
	
	void exp01() {
		
		Dog myDog = new Dog("Buddy", 4, "Golden Retriever", Color.YELLOW);
        Eagle myEagle = new Eagle("Thunder", true, 2.5, Color.BROWN);
        Fish myFish = new Fish("Nemo", "Coral Reef", Color.BLUE);

        Person person = new Person("Amine", 25);
        person.ownPet(myDog);
        person.ownPet(myEagle);
        person.ownPet(myFish);

        System.out.println("\n///////////////Details about the pets:////////////////");
        printDetails(myDog);
        printDetails(myEagle);
        printDetails(myFish);
	}
	
	
	

	public static void main(String[] args) {
		new Examples();

	}

}
