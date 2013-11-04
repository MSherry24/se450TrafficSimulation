package model;

import model.Car.Orientation;

public class CarSource implements Agent {
	
	private PropertyBag _propertyBag;
	private Double _generateCarDelay;
	private Road _nextRoad;
	private TimeServer _time;
	private Orientation _orientation;
	
	public CarSource(PropertyBag propertyBag, TimeServer time, Orientation orientation) {
		this._propertyBag = propertyBag;
		this._time = time;
		this._orientation = orientation;
		
		this._generateCarDelay = Math.random() * propertyBag.getCarGenerationDelayMax();
		this._generateCarDelay = Math.max(propertyBag.getCarGenerationDelayMin(), this._generateCarDelay);
		
		this._time.enqueue(this._time.currentTime(), this);
	}

	@Override
	public void run(double _time) {
		Car c = new Car(this._propertyBag, this._time, this._orientation);
		if (this._nextRoad == null) {
			throw new NullPointerException("Next Road Was Not Set");
		}
		Boolean blocker = false;
		for (Car e : this._nextRoad.getCars()) {
			if (e.getFrontPosition() <= c.getLength() + c.getStopDistance()) {
				blocker = true;
			}
		}
		if (blocker == false) {
			this._nextRoad.accept(c, 0.0);
			this._time.enqueue(this._time.currentTime() + this._propertyBag.getTimeStep(), c);
		}
		this._time.enqueue(this._time.currentTime() + this._generateCarDelay, this);
	}

	@Override
	public CarAcceptor getCurrentRoad() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setNextRoad(Road road) {
		this._nextRoad = road;
	}
}
