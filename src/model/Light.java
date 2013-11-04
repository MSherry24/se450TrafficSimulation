package model;

import java.util.HashSet;
import java.util.Set;

import model.Car.Orientation;

public class Light implements Agent, CarAcceptor{

	private PropertyBag _propertyBag;
	private TimeServer _time;
	private boolean _state;
	private LightState _lightState;
	private Long _greenTimeNS;
	private Long _yellowTimeNS;
	private Long _greenTimeEW;
	private Long _yellowTimeEW;

	private Set<Car> _carsNS;
	private Set<Car> _carsEW;
	private Double _endPosition;
	private CarAcceptor _nextRoadNS;
	private CarAcceptor _nextRoadEW;


	public enum LightState {
		NSGREEN, NSYELLOW, EWGREEN, EWYELLOW
	}

	public Light(PropertyBag propertyBag, TimeServer time) {
		this._propertyBag = propertyBag;
		this._time = time;

		this._endPosition = Math.random() * propertyBag.getIntersectionLengthMax();
		this._endPosition = Math.max(this._endPosition, propertyBag.getIntersectionLengthMin());
		this._carsNS = new HashSet<Car>();
		this._carsEW = new HashSet<Car>();

		this._greenTimeNS = Math.round(Math.random() * _propertyBag.getTrafficLightGreenTimeMax());
		this._greenTimeNS = Math.round(Math.max(_propertyBag.getTrafficLightGreenTimeMin(), this.getGreenTimeNS()));
		this._greenTimeEW = Math.round(Math.random() * _propertyBag.getTrafficLightGreenTimeMax());
		this._greenTimeEW = Math.round(Math.max(_propertyBag.getTrafficLightGreenTimeMin(), this.getGreenTimeEW()));

		this._yellowTimeNS = Math.round(Math.random() * _propertyBag.getTrafficLightYellowTimeMax());
		this._yellowTimeNS = Math.round(Math.max(_propertyBag.getTrafficLightYellowTimeMin(), this.getYellowTimeNS()));
		this._yellowTimeEW = Math.round(Math.random() * _propertyBag.getTrafficLightYellowTimeMax());
		this._yellowTimeEW = Math.round(Math.max(_propertyBag.getTrafficLightYellowTimeMin(), this.getYellowTimeEW()));

		this._lightState = LightState.EWGREEN;
		this._state = true;
		this._time.enqueue(this._time.currentTime() + this._greenTimeEW, this);
	} 

	public void run(double time) {
		switch (this._lightState) {
		case EWGREEN:	this._lightState = LightState.EWYELLOW;
		this._time.enqueue(this._time.currentTime() + this._yellowTimeEW, this);
		break;
		case EWYELLOW:	this._lightState = LightState.NSGREEN;
		this._time.enqueue(this._time.currentTime() + this._greenTimeNS, this);
		this._state = !this._state;
		break;
		case NSGREEN:	this._lightState = LightState.NSYELLOW;
		this._time.enqueue(this._time.currentTime() + this._yellowTimeNS, this);
		break;
		case NSYELLOW:	this._lightState = LightState.EWGREEN;
		this._time.enqueue(this._time.currentTime() + this._greenTimeEW, this);
		this._state = !this._state;
		break;	
		default:	this._lightState = LightState.EWGREEN;
		this._time.enqueue(this._time.currentTime() + this._greenTimeEW, this);
		break;
		}
	}

	@Override
	public boolean accept(Car c, Double frontPosition) {
		if (c.getOrientation() == Orientation.EW)
			if(frontPosition> this._endPosition) {
				return this._nextRoadEW.accept(c,frontPosition-this._endPosition);
			} else {
				c.setCurrentRoad(this);
				c.setFrontPosition(frontPosition);
				this._carsEW.add(c);
				return true;
			}
		else {
			if(frontPosition> this._endPosition) {
				return this._nextRoadNS.accept(c,frontPosition-this._endPosition);
			} else {
				c.setCurrentRoad(this);
				c.setFrontPosition(frontPosition);
				this._carsNS.add(c);
				return true;
			}
		}
	}

	@Override
	public Double distanceToObstacle(Double fromPosition,
			Orientation orientation) {
		if (orientation == Orientation.EW) {
			if (this._lightState == LightState.EWGREEN || this._lightState == LightState.EWYELLOW) {
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
			if (this._lightState == LightState.NSGREEN || this._lightState == LightState.NSYELLOW) {
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

	private Double distanceToObstacleBack(Double fromPosition, Set<Car> cars) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Car c : cars)
			if (c.getBackPosition() >= fromPosition &&
			c.getBackPosition() < carBackPosition)
				carBackPosition = c.getBackPosition();
		return carBackPosition;
	}


	public CarAcceptor getNextRoad(Orientation orientation) {
		if (orientation == orientation.EW){
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
	
	public boolean remove(Car car) {
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

	public CarAcceptor getCurrentRoad() {
		return this;
	}
	public LightState getLightState() {
		return _lightState;
	}
	public void setLightState(LightState state) {
		this._lightState = state;
	}
	public boolean getState() {
		return _state;
	}
	public void setState(boolean state) {
		this._state = state;
	}
	public Long getGreenTimeNS() {
		return _greenTimeNS;
	}
	public void setGreenTimeNS(Long greenTime) {
		this._greenTimeNS = greenTime;
	}
	public Long getGreenTimeEW() {
		return _greenTimeEW;
	}
	public void setGreenTimeEW(Long greenTime) {
		this._greenTimeEW = greenTime;
	}
	public Long getYellowTimeNS() {
		return _yellowTimeNS;
	}
	public void setYellowTimeNS(Long yellowTime) {
		this._yellowTimeNS = yellowTime;
	}
	public Long getYellowTimeEW() {
		return _yellowTimeEW;
	}
	public void setYellowTimeEW(Long yellowTime) {
		this._yellowTimeEW = yellowTime;
	}

	public Orientation getOrientation() {
		if (this._lightState == LightState.EWGREEN || this._lightState == LightState.EWYELLOW) {
			return Orientation.EW;
		}
		return Orientation.NS;
	}







}
