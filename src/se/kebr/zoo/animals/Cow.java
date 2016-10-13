package se.kebr.zoo.animals;


public final class Cow extends Animal {
	
	

	private static final long serialVersionUID = 3796052193775765765L;

	public Cow(String animalID, String animalName) {
		super(animalID, animalName);
		setAnimalType(AnimalType.DOMESTIC);
	}

	@Override
	public void sound() {
		 System.out.println("MOOOOOOOOO");
	}
	@Override
	public String toString(){
		
		return "Cow";
	}

}
