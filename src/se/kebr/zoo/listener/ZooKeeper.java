package se.kebr.zoo.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import se.kebr.zoo.animals.Animal;

public final class ZooKeeper implements Observer {

	private Map<String, String> animalJournal = new HashMap<>();
	public ZooKeeper() {

	}

	@Override
	public void animalAdded(Animal animal) {
		animalJournal.put(animal.getAnimalID(), animal.getAnimalName());
	}

	@Override
	public void animalRemoved(String id) {
		animalJournal.remove(id);
	}

	public void getAnimalJournal() {

		Iterator iterator = animalJournal.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry) iterator.next();
			System.out.println(pair.getKey() + " " + pair.getValue());
		}

	}

}
