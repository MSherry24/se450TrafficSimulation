package model;

import properties.PropertyBag;
import timeserver.TimeServer;

import model.Data.LightState;
import model.Data.Orientation;

public class LightObj implements Agent, Light {

	private PropertyBag _propertyBag = PropertyBag.makePropertyBag();
	private TimeServer _time;
	private boolean _state;
	private LightState _lightState;
	private Long _greenTimeNS;
	private Long _yellowTimeNS;
	private Long _greenTimeEW;
	private Long _yellowTimeEW;

	

	public LightObj() {
		this._time = this._propertyBag.getTimeServer();

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
