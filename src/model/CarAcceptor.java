package model;

import java.util.ArrayList;
import java.util.Set;

import model.Data.Orientation;

public interface CarAcceptor {
	  public boolean accept(Vehicle c, Double frontPosition);
	  public Double distanceToObstacle(Double fromPosition, Orientation orientation);
	  public Double getEndPosition();
	  public RoadEnd getNextRoad(Orientation orientation);
	  public void setNextRoad(RoadEnd road);
	  public boolean remove(Vehicle car);
	  public Set<Vehicle> getCars();	
}
