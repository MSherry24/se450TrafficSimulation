package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Road {
	
	private ArrayList<Vehicle> vehiclesOnThisRoad = new ArrayList<Vehicle>();
	private Double _roadLength;
	
	
	public Road(Double roadLength) {
		vehiclesOnThisRoad = new ArrayList<Vehicle>();
		this._roadLength = roadLength;
	}
	
	public void addVehicle(Vehicle vehicleToAdd) {
		if (vehicleToAdd == null)  throw (new IllegalArgumentException());
		vehiclesOnThisRoad.add(vehicleToAdd);
	}
	
	public Double getDistanceToNextObject(Vehicle vehicle) {
		Iterator<Vehicle> i = vehiclesOnThisRoad.iterator();
		Vehicle current;
		while (i.hasNext()) {
			current = i.next();
			if (current == vehicle) {
				if (i.hasNext()) {
					return i.next().getLocation() - vehicle.getLocation();
				}
				else {
					return getLength() - vehicle.getLocation();
					}
			}
		}
		return this._roadLength;
	}		


	public Double getLength() {
		return this._roadLength;
	}
	
	
	
}
