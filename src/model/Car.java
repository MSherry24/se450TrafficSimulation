package model;

import java.awt.Color;

import model.Data.Orientation;

import properties.PropertyBag;
import timeserver.TimeServer;

final class Car implements Agent, Vehicle {
	private Double _brakeDistance;
	private CarAcceptor _currentRoad;
	private RoadEnd _currentIntersection;
	private boolean _inIntersection;
	private Double _frontPosition;
	private Double _length;
	private Double _maxVelocity;
	private Double _stopDistance;
	private Double _timestep;
	private Integer _roadSegmentsTraversed;
	private Color _color;
	private Orientation _orientation;
	private TimeServer _time;
	private PropertyBag _propertyBag = PropertyBag.makePropertyBag();

	
	Car(Orientation orientation) {
				
		this._length = Math.random() * _propertyBag.getCarLengthMax();
		this._length = Math.max(_propertyBag.getCarLengthMin(), this._length);
		
		this._maxVelocity = Math.random() * _propertyBag.getCarMaxVelocityMax();
		this._maxVelocity = Math.max(_propertyBag.getCarMaxVelocityMin(), this._maxVelocity);
				
		this._stopDistance = Math.random() * _propertyBag.getCarStopDistanceMax();
		this._stopDistance = Math.max(_propertyBag.getCarStopDistanceMin(), this._stopDistance);
		this._stopDistance = Math.max(_propertyBag.getCarLengthMax() / 2, this._stopDistance);
		
		
		this._brakeDistance = Math.random() * _propertyBag.getCarBrakeDistanceMax();
		this._brakeDistance = Math.max(_propertyBag.getCarBrakeDistanceMin(), this._brakeDistance);
		this._brakeDistance = Math.max(this._stopDistance, this._brakeDistance);
		
		this._color = getRandomColor();
		this._timestep = _propertyBag.getTimeStep();	
		this._frontPosition = 0.0;
		this._roadSegmentsTraversed = 0;
		
		this._orientation = orientation;
		this._time = this._propertyBag.getTimeServer();
	}

	public void setFrontPosition(Double position) {
		Double roadEnd = this._currentRoad.getEndPosition();
		if (position > roadEnd) {
			CarAcceptor currentRoad;
			if (this._inIntersection) {
				RoadEnd currentIntersection = this._currentIntersection;
				this._currentIntersection.getNextRoad(this._orientation).accept(this, position - roadEnd);
				currentIntersection.remove(this);
				this._roadSegmentsTraversed++;
				return;
			}
			else {
				CarAcceptor road = this._currentRoad;
				this._currentRoad.getNextRoad(this._orientation).accept(this, position - roadEnd);
				road.remove(this);
				this._roadSegmentsTraversed++;
				return;
			}
		}
		else {
			this._frontPosition = position;
		}
	}

	public Double getFrontPosition() {
		return this._frontPosition;
	}

	public Double getBackPosition() {
		return this._frontPosition - this._length;
	}

	private Double getCurrentVelocity() {
		Double velocity;
		Double distanceToObstacle;
		if (this._inIntersection) {
			distanceToObstacle = this._currentIntersection.distanceToObstacle(this._frontPosition, this._orientation);
		}
		else {
			 distanceToObstacle = this._currentRoad.distanceToObstacle(this._frontPosition, this._orientation);
		}
		if (distanceToObstacle == Double.POSITIVE_INFINITY) {
			return this._frontPosition + this._maxVelocity * this._timestep;
		}
		if (distanceToObstacle < this._maxVelocity && 
				(distanceToObstacle > this._brakeDistance || distanceToObstacle > this._stopDistance))
			velocity = distanceToObstacle / 2;
		else {
			velocity = (this._maxVelocity / (this._brakeDistance - this._stopDistance))
					* (this._currentRoad.distanceToObstacle(this._frontPosition, this._orientation) - this._stopDistance);
		}
		velocity = Math.max(0.0, velocity);
		velocity = Math.min(this._maxVelocity, velocity);
		Double nextFrontPosition = this._frontPosition + velocity * this._timestep;
		return nextFrontPosition;
	}

	public void run(double _time) {
		Double currentVelocity = getCurrentVelocity();
		setFrontPosition(currentVelocity);
		this._time.enqueue(this._time.currentTime() + this._timestep, this);

	}
	
	public void setCurrentRoad(CarAcceptor roadCarIsOn) {
		this._currentRoad = roadCarIsOn;
		this._inIntersection = false;
	}
	
	public void setCurrentIntersection(RoadEnd intersectionCarIsIn) {
		this._currentIntersection = intersectionCarIsIn;
		this._inIntersection = true;
	}

	public CarAcceptor getCurrentRoad() {
		return this._currentRoad;
	}

	public Color getColor() {
		return _color;
	}
	
	public Double getLength() {
		return this._length;
	}
	
	public Double getBrakeDistance() {
		return this._brakeDistance;
	}
	
	public Double getMaxVelocity() {
		return this._maxVelocity;
	}
	
	public Double getStopDistance() {
		return this._stopDistance;
	}
	
	public Double getTimeStep() {
		return this._timestep;
	}

	public double getRoadSegmentsTraversed() {
		return this._roadSegmentsTraversed;
	}
	
	public Orientation getOrientation() {
		return this._orientation;
	}
	
	private Color getRandomColor() {		
		Color a = new java.awt.Color(255,191,100);
		Color b = new java.awt.Color(255,255,51);
		Color c = new java.awt.Color(51,255,255);
		Color d = new java.awt.Color(255,102,178);

		Double r = Math.random();
		
		if (r < 0.25) {
			return a;
		}
		if (r < 0.5) {
			return b;
		}
		if (r < 0.75) {
			return c;
		}
		return d;

		
	}
}