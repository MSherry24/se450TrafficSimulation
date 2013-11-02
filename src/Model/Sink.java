package model;

public class Sink implements CarAcceptor {

	public boolean accept(Car c, Double frontPosition) {
		return true;
	}

	public Double distanceToObstacle(Double fromPosition) {
		return Double.POSITIVE_INFINITY;	
		}

	public Double getEndPosition() {
		return Double.POSITIVE_INFINITY;
	}
	


}

