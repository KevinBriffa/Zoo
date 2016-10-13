package se.kebr.zoo.animals;

public final class Whale extends Animal{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4432430635122794449L;

	public Whale(String animalID, String animalName) {
		super(animalID, animalName);
		setAnimalType(AnimalType.WILD);
	}

	@Override
	public void sound() {
		System.out.println("Whale sound");
	}
	@Override
	public String toString(){
		
		return "Whale";
	}

}
