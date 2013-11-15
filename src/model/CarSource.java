package model;

import properties.PropertyBag;
import timeserver.TimeServer;
import model.Data.Orientation;

public class CarSource implements Agent {
	
	private PropertyBag _propertyBag = PropertyBag.makePropertyBag();
	private Double _generateCarDelay;
	private CarAcceptor _nextRoad;
	private TimeServer _time;
	private Orientation _orientation;
	
	public CarSource(Orientation orientation) {
		this._time = this._propertyBag.getTimeServer();
		this._orientation = orientation;
		
		this._generateCarDelay = Math.random() * _propertyBag.getCarGenerationDelayMax();
		this._generateCarDelay = Math.max(_propertyBag.getCarGenerationDelayMin(), this._generateCarDelay);
		
		this._time.enqueue(this._time.currentTime(), this);
	}

	@Override
	public void run(double _time) {
		Car c = new Car(this._orientation);
		if (this._nextRoad == null) {
			throw new NullPointerException("Next Road Was Not Set");
		}
		Boolean blocker = false;
		for (Vehicle e : this._nextRoad.getCars()) {
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
	
	public void setNextRoad(CarAcceptor l) {
		this._nextRoad = l;
	}
}
