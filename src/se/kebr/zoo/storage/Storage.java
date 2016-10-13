package se.kebr.zoo.storage;

import java.util.Collection;

public interface Storage<T>{
	  
	
	void add(T type);
	void remove(String info);
	Collection<T> getAll();
	
	  		
	  		
}	  
