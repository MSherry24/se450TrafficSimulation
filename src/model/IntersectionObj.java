package model;

import java.util.HashSet;
import java.util.Set;

import properties.PropertyBag;

import model.Data.Orientation;
import model.Data.LightState;

final class IntersectionObj implements RoadEnd {
	private Set<Vehicle> _carsNS;
	private Set<Vehicle> _carsEW;
	private Double _endPosition;
	private CarAcceptor _nextRoadNS;
	private CarAcceptor _nextRoadEW;
	private PropertyBag _propertyBag = PropertyBag.makePropertyBag();
	private LightObj _light;

	
	IntersectionObj() {
		this._endPosition = Math.random() * this._propertyBag.getIntersectionLengthMax();
		this._endPosition = Math.max(this._endPosition, this._propertyBag.getIntersectionLengthMin());
		this._carsNS = new HashSet<Vehicle>();
		this._carsEW = new HashSet<Vehicle>();
		this._light = Data.makeLight();
	}
	
	public LightObj getLight() {
		return this._light;
	}

	public boolean accept(Vehicle c, Double frontPosition) {
		if (c.getOrientation() == Orientation.EW)
			if(frontPosition> this._endPosition) {
				return this._nextRoadEW.accept(c,frontPosition-this._endPosition);
			} else {
				c.setCurrentIntersection(this);
				c.setFrontPosition(frontPosition);
				this._carsEW.add(c);
				return true;
			}
		else {
			if(frontPosition> this._endPosition) {
				return this._nextRoadNS.accept(c,frontPosition-this._endPosition);
			} else {
				c.setCurrentIntersection(this);
				c.setFrontPosition(frontPosition);
				this._carsNS.add(c);
				return true;
			}
		}
	}
	
	public Double distanceToObstacle(Double fromPosition,
			Orientation orientation) {
		if (orientation == Orientation.EW) {
			if (this._light.getLightState() == LightState.EWGREEN || this._light.getLightState() == LightState.EWYELLOW) {
				double obstaclePosition = this.distanceToObstacleBack(fromPosition, this._carsEW);
				if (obstaclePosition == Double.POSITIVE_INFINITY) {
					double distanceToEnd = this._endPosition - fromPosition;
					obstaclePosition = _nextRoadEW.distanceToObstacle(0.0, Orientation.EW) + distanceToEnd;
				}
				return obstaclePosition-fromPosition;	
			}
			else {
				return 0.0;
			}
		}

		else {
			if (this._light.getLightState() == LightState.NSGREEN || this._light.getLightState() == LightState.NSYELLOW) {
				double obstaclePosition = this.distanceToObstacleBack(fromPosition, this._carsNS);
				if (obstaclePosition == Double.POSITIVE_INFINITY) {
					double distanceToEnd = this._endPosition - fromPosition;
					obstaclePosition = _nextRoadNS.distanceToObstacle(0.0, Orientation.NS) + distanceToEnd;
				}
				return obstaclePosition-fromPosition;	
			}
			else {
				return 0.0;
			}
		}
	}
	
	private Double distanceToObstacleBack(Double fromPosition, Set<Vehicle> cars) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Vehicle c : cars)
			if (c.getBackPosition() >= fromPosition &&
			c.getBackPosition() < carBackPosition)
				carBackPosition = c.getBackPosition();
		return carBackPosition;
	}
	
	public CarAcceptor getNextRoad(Orientation orientation) {
		if (orientation == Orientation.EW){
			return this._nextRoadEW;
		}
		return this._nextRoadNS;
	}

	public void setNextRoad(CarAcceptor nextRoad, Orientation orientation) {
		if (orientation == Orientation.EW) {
			this._nextRoadEW = nextRoad;
		}
		this._nextRoadNS = nextRoad;
	}
	
	public boolean remove(Vehicle car) {
		if (this._carsEW.contains(car)) {
			this._carsEW.remove(car);
			return true;
		}
		if (this._carsNS.contains(car)) {
			this._carsNS.remove(car);
			return true;
		}
		else {
			return false;
		}
	}

	public Double getEndPosition() {
		return this._endPosition;
	}

	public Orientation getOrientation() {
		throw new UnsupportedOperationException();
	}
}
