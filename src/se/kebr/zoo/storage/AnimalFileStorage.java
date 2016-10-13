package se.kebr.zoo.storage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import se.kebr.zoo.animals.Animal;

public final class AnimalFileStorage {

	private FileStorageInfo fileStorageInfo;
	private File file;
	private List<Animal> allAnimals = new ArrayList<>();

	public AnimalFileStorage(FileStorageInfo fileStorageInfo) {
		this.fileStorageInfo = fileStorageInfo;

		file = new File(this.fileStorageInfo.getDirectoryName());
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(this.fileStorageInfo.getDirectoryName() + "/" + this.fileStorageInfo.getFileName()
				+ this.fileStorageInfo.getFileExtension());
		try {
			if (!this.file.exists()) {
				this.file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(Collection<Animal> animal) {

		try (ObjectOutputStream obWriter = new ObjectOutputStream(new FileOutputStream(file))) {
			obWriter.writeObject(animal);

		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void remove(String info) {

		try (ObjectInputStream objReader = new ObjectInputStream(new FileInputStream(file))) {

			allAnimals = (ArrayList<Animal>) objReader.readObject();
			for (Animal currentAnimal : allAnimals) {
				if (currentAnimal.getAnimalID().equals(info)) {
					allAnimals.remove(currentAnimal);
					System.out.println(currentAnimal.getAnimalID() + " " + currentAnimal.getAnimalName() + " removed.");
					break;
				}
			}
			add(allAnimals);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Collection<Animal> getAll() {

		try (ObjectInputStream objReader = new ObjectInputStream(new FileInputStream(file))) {

			allAnimals = (ArrayList<Animal>) objReader.readObject();

		} catch (EOFException e) {
			System.err.println("No animals in file");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found");
		}
		return allAnimals;
	}

}
