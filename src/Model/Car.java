package model;

import java.awt.Color;

public class Car implements Agent {
	private Double _brakeDistance;
	private Road _currentRoad;
	private Double _frontPosition;
	private Double _length;
	private Double _maxVelocity;
	private Double _stopDistance;
	private Double _timestep;

	public Car(PropertyBag propertyBag) {
		this._brakeDistance = Math.max(propertyBag.getCarBrakeDistanceMax(),
				Math.random() * propertyBag.getCarBrakeDistanceMin());
		this._length = Math.max(propertyBag.getCarLengthMin(),
				Math.random() * propertyBag.getCarLengthMax());
		this._maxVelocity = Math.max(propertyBag.getCarMaxVelocityMax(),
				Math.random() * propertyBag.getCarMaxVelocityMin());
		this._stopDistance = Math.max(propertyBag.getCarStopDistanceMax(),
				Math.random() * propertyBag.getCarStopDistanceMin());
		this._timestep = propertyBag.getTimeStep();	
		this._frontPosition = 0.0;
	}

	public void setCurrentRoad(Road roadCarIsOn) {
		this._currentRoad = roadCarIsOn;
	}

	public CarAcceptor getCurrentRoad() {
		return this._currentRoad;
	}

	public void setFrontPosition(Double position) {
		Double roadEnd = this._currentRoad.getEndPosition();
		if (position > roadEnd) {
			this._currentRoad.getNextRoad().accept(this, position - roadEnd);
			this._currentRoad.remove(this);
			this._currentRoad = null;
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



	public Double getVelocity() {
		Double velocity;
		Double distanceToObstacle = this._currentRoad.distanceToObstacle(this._frontPosition);
		if (distanceToObstacle < this._maxVelocity && 
				distanceToObstacle > this._brakeDistance)
			velocity = distanceToObstacle / 2;
		else {
			velocity = (this._maxVelocity / (this._brakeDistance - this._stopDistance))
					* (this._currentRoad.distanceToObstacle(this._frontPosition) - this._stopDistance);
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
		Double carVelocity = getVelocity();
		setFrontPosition(carVelocity); 

	}

	public Double getPosition() {
		// TODO Auto-generated method stub
		return this._frontPosition;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return new Color(150,0,50);
	}
}