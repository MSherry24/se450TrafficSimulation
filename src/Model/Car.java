package model;

public class Car implements Agent {

	private Double _maxVelocity;
	private Double _brakeDistance;
	private Double _stopDistance;
	private Double _length;
	public Double _distanceToObstacle;
	private Double _frontPosition;
	private Road _currentRoad;

	public Car() {
		this._maxVelocity = 0.0;
		this._brakeDistance = 0.0;
		this._stopDistance = 0.0;
		this._length = 5.0;
	}

	public void setCurrentRoad(Road roadCarIsOn) {
		this._currentRoad = roadCarIsOn;
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
//		nextFrontPosition = this._frontPosition + velocity * timeStep;
//		return nextFrontPosition;
		return null;
	}


	public void run() {
		// TODO Auto-generated method stub

	}




}