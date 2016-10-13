package se.kebr.zoo;

import java.util.ArrayList;
import java.util.List;
import se.kebr.zoo.animals.Animal;
import se.kebr.zoo.animals.Animal.AnimalType;
import se.kebr.zoo.listener.Observer;
import se.kebr.zoo.listener.Subject;
import se.kebr.zoo.listener.ZooKeeper;
import se.kebr.zoo.security.ZooException;
import se.kebr.zoo.storage.AnimalFileStorage;
import se.kebr.zoo.storage.AnimalStorage;
import se.kebr.zoo.storage.FileStorageInfo;

public final class Zoo implements Subject {

	private AnimalStorage wildAnimalStorage;
	private AnimalStorage domesticAnimalStorage;
	private List<Observer> animalObserver = new ArrayList<>();
	private ZooKeeper animalKeeper;

	public Zoo(ZooKeeper animalKeeper) {
		this.animalKeeper = animalKeeper;
		register(animalKeeper);
		domesticAnimalStorage = new AnimalStorage(new AnimalFileStorage(new FileStorageInfo("Zoo", "DomesticAnimals", ".txt")));
		wildAnimalStorage = new AnimalStorage(new AnimalFileStorage(new FileStorageInfo("Zoo", "WildAnimals", ".txt")));
		init();
	}

	public Zoo addAnimal(Animal animal) {
		try {
			if (domesticAnimalStorage.existsInStorage(animal) || wildAnimalStorage.existsInStorage(animal)) {
				throw new ZooException(animal.getAnimalID() + " already exists");
			}
			if (animal.getAnimalType().equals(AnimalType.DOMESTIC)) {
				domesticAnimalStorage.add(animal);
				notifyAnimalAdded(animal);
			} else if (animal.getAnimalType().equals(AnimalType.WILD)) {
				wildAnimalStorage.add(animal);
				notifyAnimalAdded(animal);
			}
		} catch (ZooException ex) {
			System.err.println(ex.getMessage());
		}
		return this;

	}

	public void removeAnimal(String id) {
		wildAnimalStorage.remove(id);
		domesticAnimalStorage.remove(id);
		notifyRemovedAnimal(id);
	}

	public void hearAnimalSound() {

		System.err.println(AnimalType.DOMESTIC + "\n" + "Animal content: ");
		for (Animal animal : domesticAnimalStorage.getAll()) {
			System.out.println(animal.getAnimalID());
		}

		System.err.println(AnimalType.WILD + "\n" + "Animal content: ");
		for (Animal animal : wildAnimalStorage.getAll()) {
			System.out.println(animal.getAnimalID());
		}
		System.out.println("\nAnimal Journal: ");
		this.animalKeeper.getAnimalJournal();

	}

	@Override
	public void register(Observer observer) {
		animalObserver.add(observer);
	}

	@Override
	public void unregister(Observer observer) {
		animalObserver.remove(observer);
	}

	@Override
	public void notifyAnimalAdded(Animal animal) {
		for (Observer ob : animalObserver) {
			System.out.println(animal.toString() + " added." + " ID added " + animal.getAnimalID());
			ob.animalAdded(animal); // update
		}
	}

	public void notifyRemovedAnimal(String id) {
		for (Observer ob : animalObserver) {
			ob.animalRemoved(id);
		}
	}

	public void init() {

		for (Animal animal : wildAnimalStorage.getAll()) {
			wildAnimalStorage.add(animal);
			notifyAnimalAdded(animal);
		}
		for (Animal animal : domesticAnimalStorage.getAll()) {
			domesticAnimalStorage.add(animal);
			notifyAnimalAdded(animal);
		}
	}

}