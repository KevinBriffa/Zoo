package se.kebr.zoo.animals;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public abstract class Animal implements Serializable {

	private static final long serialVersionUID = 9026911536142266178L;
	private final String animalID;
	private final String animalName;
	private transient String internalCode;
	private AnimalType animalType;
	private Date birthDate;

	public Animal(String animalID, String animalName) {
		this.animalID = animalID;
		this.animalName = animalName;
		this.internalCode = animalID + animalName;
		birthDate = new Date();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeLong(birthDate.getTime());

	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		birthDate = new Date(in.readLong());
	}

	public abstract void sound();

	public String getAnimalID() {
		return new String(animalID);
	}

	public String getAnimalName() {
		return new String(animalName);
	}

	public String getInternalCode() {
		return new String(internalCode);
	}

	protected void setAnimalType(AnimalType animalType) {
		this.animalType = animalType;
	}

	public AnimalType getAnimalType() {
		return animalType;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (this instanceof Animal) {
			Animal otherAnimals = (Animal) other;
			return animalID.equals(otherAnimals.animalID);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * animalID.hashCode();
		return result;
	}

	public static Comparator<Animal> AnimalComparator = new Comparator<Animal>() {

		@Override
		public int compare(Animal o1, Animal o2) {
			return o1.animalID.compareTo(o2.animalID);
		}
	};
	public static enum AnimalType {
		DOMESTIC, WILD

	}
}
