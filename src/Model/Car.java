package model;

public class Car implements Vehicle {
	
	private Double _velocity;
	private Double _location;
	
	public Car() {
		this._velocity = 0.0;
		this._location = 0.0;
	}
	
	public Double getVelocity() {
		//TODO
		return _velocity;
	}
	
	public void setVelocity(Double velocity) {
		//TODO
		this._velocity = velocity;	
	}

	public Double getLocation() {
		// TODO
		return this._location;
	}

	@Override
	public Double setVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double setLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
