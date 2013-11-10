package model;

import java.util.HashSet;
import java.util.Set;

import properties.PropertyBag;

import model.Car.Orientation;
import model.Light.LightState;

public class Road implements CarAcceptor {

	private Set<Car> _cars;
	private Double _endPosition;
	private CarAcceptor _nextRoad;
	private PropertyBag _propertyBag = PropertyBag.makePropertyBag();

	public Road() {
		this._endPosition = Math.random() * this._propertyBag.getRoadSegmentLengthMax();
		this._endPosition = Math.max(this._endPosition, this._propertyBag.getRoadSegmentLengthMin());
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

			if (this._nextRoad instanceof Light) {
				if (orientation == Orientation.NS && 
						(((Light) this._nextRoad).getLightState() == LightState.EWGREEN ||
						((Light) this._nextRoad).getLightState() == LightState.EWYELLOW)) {
					return distanceToEnd;
				}
				if (orientation == Orientation.EW && 
						(((Light) this._nextRoad).getLightState() == LightState.NSGREEN ||
						((Light) this._nextRoad).getLightState() == LightState.NSYELLOW)) {
					return distanceToEnd;
				}
			}
			obstaclePosition = _nextRoad.distanceToObstacle(0.0, orientation) + distanceToEnd;
			return obstaclePosition;
		}
		return obstaclePosition - fromPosition;
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

