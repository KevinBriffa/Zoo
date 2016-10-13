package se.kebr.zoo.animals;

import se.kebr.zoo.security.DangerousAnimal;

public final class Lion extends Animal implements DangerousAnimal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3010682438421756486L;

	/**
	 * 
	 */

	public Lion(String animalID, String animalName) {
		super(animalID, animalName);
		setAnimalType(AnimalType.WILD);
	}

	@Override
	public void sound() {
		System.out.println("ROAAAAR");
	}
	@Override
	public String toString(){
		
		return "Lion";
	}


}
