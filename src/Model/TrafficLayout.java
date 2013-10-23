package model;

import java.util.List;
import java.util.Scanner;

public class TrafficLayout {
	
	private Double vehicleArrivalFrequencyNorth;
	private Double vehicleArrivalFrequencySouth;
	private Double vehicleArrivalFrequencyEast;
	private Double vehicleArrivalFrequencyWest;
	
	private Double _vehicleLength;
	private Double _maxVehicleVelocity;
	private Double _maxObstructionInfluenceDistance;
	private Double _minimumDistanceFromObstruction;
	private Double _signalTiming;
	private Double _timeStep;
	private Integer _simulationSteps;
	private Double _roadSegmentLength;

	private List<Road> gridOfRoads;
	
	public TrafficLayout() {
		String response;
		System.out.println("Traffic Grid Initialized");
		System.out.println("Please enter the vehicle length in meters (default = 5.0m)");
		Scanner sc = new Scanner(System.in);
	    response = sc.nextLine();
	    if (response.trim() == "") {
	    	this._vehicleLength = 5.0;
	    }
	    sc.reset();
	    
		System.out.println("Please enter the maximum vehicle velocity in m/s (default = 50.0m/s)");
	    response = sc.nextLine();
	    if (response.trim() == "") {
	    	this._maxVehicleVelocity = 50.0;
	    }
	    sc.reset();

	    
	    
		sc.close();
		
	}
	
	public static void main(String args[]) {
		
	}
	
}
