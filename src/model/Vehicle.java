package model;

import java.awt.Color;

import model.Data.Orientation;

public interface Vehicle {
	public CarAcceptor getCurrentRoad();
	public Double getFrontPosition();
	public Orientation getOrientation();
	public void setFrontPosition(Double position);
	public Double getBackPosition();
	public void setCurrentRoad(CarAcceptor roadCarIsOn);
	public void setCurrentIntersection(RoadEnd intersectionCarIsIn);
	public Color getColor(); 
	public Double getLength(); 
	public Double getBrakeDistance(); 
	public Double getMaxVelocity(); 
	public Double getStopDistance(); 
	public Double getTimeStep(); 
	public double getRoadSegmentsTraversed();
}
