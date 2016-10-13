package se.kebr.zoo.animals;

import se.kebr.zoo.security.DangerousAnimal;

public final class Tiger extends Animal implements DangerousAnimal{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1173172797532755873L;

	public Tiger(String animalID, String animalName) {
		super(animalID, animalName);
		setAnimalType(AnimalType.WILD);
	}

	@Override
	public void sound() {
		System.out.println("GRRRREAAAAATT");
	}
	@Override
	public String toString(){
		
		return "Tiger";
	}

}
