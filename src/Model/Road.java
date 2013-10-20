package Model;

import java.util.List;

public class Road {
	
	private List<Car> carsOnThisRoad;
	
	public Road() {
		
	}
	
	public void add(Car carToAdd) {
		if (carToAdd == null)  throw (new IllegalArgumentException());
		carsOnThisRoad.add(carToAdd);
	}
	
	
	
}
