package model;

import model.Data.Orientation;


final class Sink implements RoadEnd {

	public boolean accept(Vehicle c, Double frontPosition) {
		return true;
	}

	public Double distanceToObstacle(Double fromPosition, Orientation orientation) {
		return Double.POSITIVE_INFINITY;
	}

	public CarAcceptor getNextRoad(Orientation orientation) {
		throw new UnsupportedOperationException();
	}

	public void setNextRoad(CarAcceptor nextRoad, Orientation orientation) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Vehicle car) {
		throw new UnsupportedOperationException();
	}

	public Orientation getOrientation() {
		throw new UnsupportedOperationException();
	}

	public Double getEndPosition() {
		return Double.POSITIVE_INFINITY;
	}

	public LightObj getLight() {
		return null;
	}
}

