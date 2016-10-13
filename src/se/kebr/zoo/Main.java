package se.kebr.zoo;

import se.kebr.zoo.animals.Cow;
import se.kebr.zoo.animals.Whale;
import se.kebr.zoo.listener.ZooKeeper;

public final class Main {

	public static void main(String[] args) {
		
		
		Zoo zoo = new Zoo(new ZooKeeper());
		zoo.addAnimal(new Cow("1001", "Willy"))
				.addAnimal(new Cow("1003", "Cowish"))
				.addAnimal(new Cow("1008", "Cowish"))
				.addAnimal(new Cow("1006", "Cowish"))
				.addAnimal(new Whale("1014", "Valish"))
				.addAnimal(new Whale("1041", "Valen"))
				.addAnimal(new Whale("1091", "Ville"));
				
		zoo.removeAnimal("1001");	
		zoo.hearAnimalSound();
		
		
	}
}
