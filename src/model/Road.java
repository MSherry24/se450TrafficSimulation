package model;

import java.util.HashSet;
import java.util.Set;

import properties.PropertyBag;

import model.Data.Orientation;
import model.LightObj.LightState;

final class Road implements CarAcceptor {

	private Set<Vehicle> _cars;
	private Double _endPosition;
	private RoadEnd _nextRoad;
	private PropertyBag _propertyBag = PropertyBag.makePropertyBag();

	public Road() {
		this._endPosition = Math.random() * this._propertyBag.getRoadSegmentLengthMax();
		this._endPosition = Math.max(this._endPosition, this._propertyBag.getRoadSegmentLengthMin());
		this._cars = new HashSet<Vehicle>();
	}

	public boolean accept(Vehicle c, Double frontPosition) {
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
	
	public boolean remove(Vehicle c) {
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
//			LightState lightState = (this._nextRoad).getLight().getLightState();
//			if (orientation == Orientation.NS && 
//					(lightState == LightState.EWGREEN ||
//					lightState == LightState.EWYELLOW)) {
//				return distanceToEnd;
//			}
//			if (orientation == Orientation.EW && 
//					(lightState == LightState.NSGREEN ||
//					lightState == LightState.NSYELLOW)) {
//				return distanceToEnd;
//		}
		obstaclePosition = _nextRoad.distanceToObstacle(0.0, orientation) + distanceToEnd;
		return obstaclePosition;
		}
		return obstaclePosition - fromPosition;
	}
	
	private Double distanceToObstacleBack(Double fromPosition) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Vehicle c : _cars)
			if (c.getBackPosition() >= fromPosition &&
			c.getBackPosition() < carBackPosition)
				carBackPosition = c.getBackPosition();
		return carBackPosition;
	}

	public Set<Vehicle> getCars() {
		return _cars;
	}

	public Double getEndPosition() {
		return _endPosition;
	}

	public RoadEnd getNextRoad(Orientation orientation) {
		return _nextRoad;
	}
	
	public void setNextRoad(RoadEnd nextRoad) {
		this._nextRoad = nextRoad;
	}
}

