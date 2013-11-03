package model;

import model.Car.Orientation;


public class Sink implements CarAcceptor {

	public boolean accept(Car c, Double frontPosition) {
		return true;
	}

	public Double distanceToObstacle(Double fromPosition, Orientation orientation) {
		return Double.POSITIVE_INFINITY;	
		}

	public Double getEndPosition() {
		return Double.POSITIVE_INFINITY;
	}

	public CarAcceptor getNextRoad() {
		return null;
	}

	public boolean remove(Car car) {
		return true;
	}
}

