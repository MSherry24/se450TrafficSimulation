package model;

public class Car implements Agent {
	private Double _brakeDistance;
	private Road _currentRoad;
	public Double _distanceToObstacle;
	private Double _frontPosition;
	private Double _length;
	private Double _maxVelocity;
	private Double _stopDistance;
	private Double _timestep;

	public Car() {
		
	}
	
	public void setValues(PropertyBag propertyBag) {
		this._brakeDistance = Math.max(propertyBag.getCarBrakeDistanceMax(),
				Math.random() * propertyBag.getCarBrakeDistanceMin());
		this._length = Math.max(propertyBag.getCarLengthMin(),
				Math.random() * propertyBag.getCarLengthMax());
		this._maxVelocity = Math.max(propertyBag.getCarMaxVelocityMax(),
				Math.random() * propertyBag.getCarMaxVelocityMin());
		this._stopDistance = Math.max(propertyBag.getCarStopDistanceMax(),
				Math.random() * propertyBag.getCarStopDistanceMin());
		this._timestep = propertyBag.getTimeStep();	
	}

	public void setCurrentRoad(Road roadCarIsOn) {
		this._currentRoad = roadCarIsOn;
	}
	
	public Road getCurrentRoad() {
		return this._currentRoad;
	}

	public void setFrontPosition(Double position) {
		this._frontPosition = position;
	}
	
	public Double getVelocity() {
		Double velocity;
		if (this._distanceToObstacle < this._maxVelocity && 
				this._distanceToObstacle > this._brakeDistance)
			velocity = this._distanceToObstacle / 2;
		else {
			velocity = (this._maxVelocity / (this._brakeDistance - this._stopDistance))
				* (this._distanceToObstacle - this._stopDistance);
		}
		velocity = Math.max(0.0, velocity);
		velocity = Math.min(this._maxVelocity, velocity);
		Double nextFrontPosition = this._frontPosition + velocity * this._timestep;
		return nextFrontPosition;
	}


	public void run() {
		// TODO Auto-generated method stub

	}
}