package model;

import java.util.HashSet;
import java.util.Set;

public class Road implements CarAcceptor {

	private Set<Car> cars;
	private Double endPosition;
	private CarAcceptor nextRoad;

	public Road(PropertyBag propertyBag) {
		this.endPosition = Math.max(propertyBag.getRoadSegmentLengthMax(),
				Math.random() * propertyBag.getRoadSegmentLengthMin());
		this.cars = new HashSet<Car>();
	}

	public boolean accept(Car c, Double frontPosition) {
		if (this.cars != null) {
			this.cars.remove(c);
		}
		if(frontPosition>endPosition) {
			return nextRoad.accept(c,frontPosition-endPosition);
		} else {
			c.setCurrentRoad(this);
			c.setFrontPosition(frontPosition);
			cars.add(c);
			return true;
		}
	}
	
	public boolean remove(Car c) {
		if (this.cars.contains(c)) {
			this.cars.remove(c);
			return true;
		}
		else {
			return false;
		}
	}

	private Double distanceToCarBack(Double fromPosition) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Car c : cars)
			if (c.getBackPosition() >= fromPosition &&
			c.getBackPosition() < carBackPosition)
				carBackPosition = c.getBackPosition();
		return carBackPosition;
	}

	public Double distanceToObstacle(Double fromPosition) {
		double obstaclePosition = this.distanceToCarBack(fromPosition);
		if (obstaclePosition == Double.POSITIVE_INFINITY) {
			double distanceToEnd = this.endPosition - fromPosition;
			if (nextRoad == null) {
				nextRoad = new Sink();
			}
			obstaclePosition = nextRoad.distanceToObstacle(distanceToEnd);
		}
		return obstaclePosition-fromPosition;	
	}

	public Set<Car> getCars() {
		return cars;
	}

	public Double getEndPosition() {
		return endPosition;
	}

	public CarAcceptor getNextRoad() {
		return nextRoad;
	}
	
	public void setNextRoad(CarAcceptor nextRoad) {
		this.nextRoad = nextRoad;
	}
}

