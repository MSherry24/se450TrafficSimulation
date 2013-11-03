package model;

public class CarSource implements Agent {
	
	private PropertyBag _propertyBag;
	private Double _generateCarDelay;
	private Road _nextRoad;
	private TimeServer _time;
	
	public CarSource(PropertyBag propertyBag, TimeServer time) {
		this._propertyBag = propertyBag;
		this._time = time;
		
		this._generateCarDelay = Math.random() * propertyBag.getCarGenerationDelayMax();
		this._generateCarDelay = Math.max(propertyBag.getCarGenerationDelayMin(), this._generateCarDelay);
		
		this._time.enqueue(this._time.currentTime(), this);
	}

	@Override
	public void run(double _time) {
		Car c = new Car(this._propertyBag, this._time);
		if (this._nextRoad == null) {
			throw new NullPointerException("Next Road Was Not Set");
		}
		if (this._nextRoad.distanceToObstacle(0.0) > c.getLength()) {
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
