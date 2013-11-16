package model;

import model.Data.Orientation;

	public interface Intersection {
		public boolean accept(Vehicle c, Double frontPosition);
		public Double distanceToObstacle(Double fromPosition, Orientation orientation);
		public Double getEndPosition();
		public CarAcceptor getNextRoad(Orientation orientation);
		public void setNextRoad(CarAcceptor road, Orientation orientation);
		public boolean remove(Vehicle car);
	}
