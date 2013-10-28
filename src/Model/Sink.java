package model;

class Sink implements CarAcceptor {
	public double distanceToObstacle(double fromPosition) {
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public boolean accept(Car c, Double frontPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Double distanceToObstacle(Double fromPosition) {
		// TODO Auto-generated method stub
		return Double.POSITIVE_INFINITY;
	}
}

