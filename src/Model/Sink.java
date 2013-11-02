package model;

class Sink implements CarAcceptor {

	public boolean accept(Car c, Double frontPosition) {
		return true;
	}

	public Double distanceToObstacle(Double fromPosition) {
		return Double.POSITIVE_INFINITY;	
		}
	


}

