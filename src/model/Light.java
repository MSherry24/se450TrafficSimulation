package model;

public class Light implements Agent{

	/**
	 * A light has a boolean state.
	 */
	public Light() { } // Created only by this package

	private boolean _state;

	public boolean getState() {
		return _state;
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
}
