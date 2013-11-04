package model;

import java.util.HashSet;
import java.util.Set;

import model.Car.Orientation;

public class Road implements CarAcceptor {

	private Set<Car> _cars;
	private Double _endPosition;
	private CarAcceptor _nextRoad;

	public Road(PropertyBag propertyBag) {
		this._endPosition = Math.random() * propertyBag.getRoadSegmentLengthMax();
		this._endPosition = Math.max(this._endPosition, propertyBag.getRoadSegmentLengthMin());
		this._cars = new HashSet<Car>();
	}

	public boolean accept(Car c, Double frontPosition) {
		if (this._cars != null) {
			this._cars.remove(c);
		}
		if(frontPosition>_endPosition) {
			return _nextRoad.accept(c,frontPosition-_endPosition);
		} else {
			c.setCurrentRoad(this);
			c.setFrontPosition(frontPosition);
			_cars.add(c);
			return true;
		}
	}
	
	public boolean remove(Car c) {
		if (this._cars.contains(c)) {
			this._cars.remove(c);
			return true;
		}
		else {
			return false;
		}
	}

	public Double distanceToObstacle(Double fromPosition, Orientation orientation) {
		double obstaclePosition = this.distanceToObstacleBack(fromPosition);
		if (obstaclePosition == Double.POSITIVE_INFINITY) {
			double distanceToEnd = this._endPosition - fromPosition;
			obstaclePosition = _nextRoad.distanceToObstacle(0.0, orientation) + distanceToEnd;
		}
		return obstaclePosition;	
	}
	
	private Double distanceToObstacleBack(Double fromPosition) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Car c : _cars)
			if (c.getBackPosition() >= fromPosition &&
			c.getBackPosition() < carBackPosition)
				carBackPosition = c.getBackPosition();
		return carBackPosition;
	}

	public Set<Car> getCars() {
		return _cars;
	}

	public Double getEndPosition() {
		return _endPosition;
	}

	public CarAcceptor getNextRoad(Orientation orientation) {
		return _nextRoad;
	}
	
	public void setNextRoad(CarAcceptor nextRoad) {
		this._nextRoad = nextRoad;
	}
	
}

