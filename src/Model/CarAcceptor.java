package model;

import model.Car.Orientation;

public interface CarAcceptor {
	  public boolean accept(Car c, Double frontPosition);
	  public Double distanceToObstacle(Double fromPosition, Orientation orientation);
	  public Double getEndPosition();
	  public CarAcceptor getNextRoad(Orientation orientation);
	  public boolean remove(Car car);	
}
