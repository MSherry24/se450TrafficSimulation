package model;

import model.Data.Orientation;

public interface RoadEnd {

	public boolean accept(Vehicle c, Double frontPosition);
	public Double distanceToObstacle(Double fromPosition, Orientation orientation);
	public CarAcceptor getNextRoad(Orientation orientation);
	public void setNextRoad(CarAcceptor nextRoad, Orientation orientation);
	public boolean remove(Vehicle car);
	public Orientation getOrientation();
	public Double getEndPosition();
	public LightObj getLight();
}
