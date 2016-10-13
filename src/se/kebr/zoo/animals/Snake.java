package se.kebr.zoo.animals;

import se.kebr.zoo.security.DangerousAnimal;

public final class Snake extends Animal implements DangerousAnimal{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2381557359351082188L;

	public Snake(String animalID, String animalName) {
		super(animalID, animalName);
		setAnimalType(AnimalType.WILD);
	}

	@Override
	public void sound() {
		System.out.println("ZZSSZZSZSZSZS");
	}
	@Override
	public String toString(){
		
		return "Snake";
	}

}
