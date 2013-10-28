package model;

public interface CarAcceptor {
	  public boolean accept(Car c, Double frontPosition);
	  public Double distanceToObstacle(Double fromPosition);

}
