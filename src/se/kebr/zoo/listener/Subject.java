package se.kebr.zoo.listener;

import se.kebr.zoo.animals.Animal;

public interface Subject {
	
	public void register(Observer observer);
	public void unregister(Observer observer);
	public void notifyAnimalAdded(Animal animal);
	public void notifyRemovedAnimal(String id);

}
