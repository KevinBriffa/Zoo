package se.kebr.zoo.listener;

import se.kebr.zoo.animals.Animal;

public interface Observer {
	
	public void animalAdded(Animal animal);
	public void animalRemoved(String id);

}
