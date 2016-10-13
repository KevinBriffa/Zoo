package se.kebr.zoo.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import se.kebr.zoo.animals.Animal;
import se.kebr.zoo.security.DangerousAnimal;
import se.kebr.zoo.security.ZooException;

public final class AnimalStorage implements Storage<Animal> {

	private final List<Animal> animalList = new ArrayList<>();
	private AnimalFileStorage animalFileStorage;

	public AnimalStorage(AnimalFileStorage animalFileStorage) {
		this.animalFileStorage = animalFileStorage;
	}
	@Override
	public void add(Animal animal) {

		try {

			if (animal instanceof DangerousAnimal) {
				throw new ZooException("No dangerous animals allowed.");
			}
			if (existsInStorage(animal)) {
				throw new ZooException("Already in storage");
			}
			animalList.add(animal);
			sortAnimalsByName(animalList);
			addAnimalsToFile(animalList);
		} catch (ZooException ex) {
			System.err.println(ex.getMessage());
		}
	}
	@Override
	public void remove(String animalID) {
		animalFileStorage.remove(animalID);
	}
	public void printAnimals() {
		System.err.println("FROM CACHE");
		for (Animal animalsFromList : animalList) {
			System.out.println(animalsFromList.getAnimalID() + " " + animalsFromList.getAnimalName());
		}
	}
	public void addAnimalsToFile(List<Animal> allAnimals) {
		animalFileStorage.add(allAnimals);
	}
	@Override
	public Collection<Animal> getAll() {
		return animalFileStorage.getAll();
	}
	public void sortAnimalsByName(List<Animal> animalList) {
		Collections.sort(animalList, Animal.AnimalComparator);
	}
	public boolean existsInStorage(Animal animal) {
		for (Animal animals : animalList) {
			if (animals.equals(animal)) {
				return true;
			}
		}

		return false;
	}
}
