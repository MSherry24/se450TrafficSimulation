package model;

import java.awt.Color;

public class Car implements Agent {
	private Double _brakeDistance;
	private CarAcceptor _currentRoad;
	private Double _frontPosition;
	private Double _length;
	private Double _maxVelocity;
	private Double _stopDistance;
	private Double _timestep;
	private Integer _roadSegmentsTraversed;
	private Color _color;
	private Orientation _orientation;
	private TimeServer _time;
	
	public enum Orientation {
		NS, EW
	}

	public Car(PropertyBag propertyBag, TimeServer time, Orientation orientation) {
		this._brakeDistance = Math.random() * propertyBag.getCarBrakeDistanceMax();
		this._brakeDistance = Math.max(propertyBag.getCarBrakeDistanceMin(), this._brakeDistance);
		
		this._length = Math.random() * propertyBag.getCarLengthMax();
		this._length = Math.max(propertyBag.getCarLengthMin(), this._length);
		
		this._maxVelocity = Math.random() * propertyBag.getCarMaxVelocityMax();
		this._maxVelocity = Math.max(propertyBag.getCarMaxVelocityMin(), this._maxVelocity);
				
		this._stopDistance = Math.random() * propertyBag.getCarStopDistanceMax();
		this._stopDistance = Math.max(propertyBag.getCarStopDistanceMin(), this._stopDistance);
		
		this._color = new java.awt.Color(255,191,100);
		this._timestep = propertyBag.getTimeStep();	
		this._frontPosition = 0.0;
		this._roadSegmentsTraversed = 0;
		
		this._orientation = orientation;
		this._time = time;
	}

	public void setFrontPosition(Double position) {
		Double roadEnd = this._currentRoad.getEndPosition();
		if (position > roadEnd) {
			CarAcceptor currentRoad = this._currentRoad;
			this._currentRoad.getNextRoad().accept(this, position - roadEnd);
			currentRoad.remove(this);
			this._roadSegmentsTraversed++;
			return;
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
		Double distanceToObstacle = this._currentRoad.distanceToObstacle(this._frontPosition, this._orientation);
		if (distanceToObstacle == Double.POSITIVE_INFINITY) {
			return this._frontPosition + this._maxVelocity * this._timestep;
		}
		if (distanceToObstacle < this._maxVelocity && 
				distanceToObstacle > this._brakeDistance)
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
		// TODO Auto-generated method stub
		// find closest obstacle
		// calculate newVelocity
		// calculate newFrontPosition
		Double currentVelocity = getCurrentVelocity();
		setFrontPosition(currentVelocity);
		this._time.enqueue(this._time.currentTime() + this._timestep, this);

	}
	
	public void setCurrentRoad(CarAcceptor roadCarIsOn) {
		this._currentRoad = roadCarIsOn;
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
}