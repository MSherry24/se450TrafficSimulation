package model;

public class Light implements Agent {
	
	private PropertyBag _propertyBag;
	private boolean _state;
	private Long _greenTime;
	private Long _yellowTime;

	public Light(PropertyBag propertyBag) { 
		this._propertyBag = propertyBag;
		
		this._greenTime = Math.round(Math.random() * _propertyBag.getTrafficLightGreenTimeMax());
		this._greenTime = Math.round(Math.max(_propertyBag.getTrafficLightGreenTimeMin(), this.getGreenTime()));
		
		this._yellowTime = Math.round(Math.random() * _propertyBag.getTrafficLightYellowTimeMax());
		this._yellowTime = Math.round(Math.max(_propertyBag.getTrafficLightYellowTimeMin(), this.getYellowTime()));
	} 
	
	public void run(double time) {
		if (time%40==0) {
			_state = !_state;
		}
	}
	public CarAcceptor getCurrentRoad() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getState() {
		return _state;
	}
	public void setState(boolean state) {
		this._state = state;
	}
	public Long getGreenTime() {
		return _greenTime;
	}
	public void setGreenTime(Long greenTime) {
		this._greenTime = greenTime;
	}
	public Long getYellowTime() {
		return _yellowTime;
	}
	public void setYellowTime(Long yellowTime) {
		this._yellowTime = yellowTime;
	}
}
