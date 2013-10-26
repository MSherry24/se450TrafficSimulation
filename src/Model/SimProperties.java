package model;

public class SimProperties {
	public enum TrafficType {
	    ALTERNATING, SIMPLE
	}
	
	private Double timeStep = 0.1;
	private Double runTime = 1000.0;
	private Integer gridRow = 1;
	private Integer gridColumn = 0;
	private TrafficType trafficPattern = TrafficType.ALTERNATING;
	private Double carGenerationDelayMin = 2.0;
	private Double carGenerationDelayMax = 25.0;
	private Double roadSegmentLengthMin = 200.0;
	private Double roadSegmentLengthMax = 500.0;
	private Double intersectionLengthMin = 10.0;
	private Double intersectionLengthMax = 15.0;
	private Double carLengthMin = 5.0;
	private Double carLengthMax = 10.0;
	private Double carMaxVelocityMin = 10.0;
	private Double carMaxVelocityMax = 30.0;	
	private Double carStopDistanceMin = 0.5;
	private Double carStopDistanceMax = 5.0;
	private Double carBrakeDistanceMin = 0.5;
	private Double carBrakeDistanceMax = 5.0;
	private Double trafficLightGreenTimeMin = 30.0;
	private Double trafficLightGreenTimeMax = 180.0;
	private Double trafficLightYellowTimeMin = 4.0;
	private Double trafficLightYellowTimeMax = 5.0;	
	
	public SimProperties() {
		
	}

	public Double getTimeStep() {
		return timeStep;
	}

	public void setTimeStep(Double timeStep) {
		this.timeStep = timeStep;
	}

	public Double getRunTime() {
		return runTime;
	}

	public void setRunTime(Double runTime) {
		this.runTime = runTime;
	}

	public Integer getGridRow() {
		return gridRow;
	}

	public void setGridRow(Integer gridRow) {
		this.gridRow = gridRow;
	}

	public Integer getGridColumn() {
		return gridColumn;
	}

	public void setGridColumn(Integer gridColumn) {
		this.gridColumn = gridColumn;
	}

	public TrafficType getTrafficPattern() {
		return trafficPattern;
	}

	public void setTrafficPattern(TrafficType trafficPattern) {
		this.trafficPattern = trafficPattern;
	}

	public Double getCarGenerationDelayMin() {
		return carGenerationDelayMin;
	}

	public void setCarGenerationDelayMin(Double carGenerationDelayMin) {
		this.carGenerationDelayMin = carGenerationDelayMin;
	}

	public Double getCarGenerationDelayMax() {
		return carGenerationDelayMax;
	}

	public void setCarGenerationDelayMax(Double carGenerationDelayMax) {
		this.carGenerationDelayMax = carGenerationDelayMax;
	}

	public Double getRoadSegmentLengthMin() {
		return roadSegmentLengthMin;
	}

	public void setRoadSegmentLengthMin(Double roadSegmentLengthMin) {
		this.roadSegmentLengthMin = roadSegmentLengthMin;
	}

	public Double getRoadSegmentLengthMax() {
		return roadSegmentLengthMax;
	}

	public void setRoadSegmentLengthMax(Double roadSegmentLengthMax) {
		this.roadSegmentLengthMax = roadSegmentLengthMax;
	}

	public Double getIntersectionLengthMin() {
		return intersectionLengthMin;
	}

	public void setIntersectionLengthMin(Double intersectionLengthMin) {
		this.intersectionLengthMin = intersectionLengthMin;
	}

	public Double getIntersectionLengthMax() {
		return intersectionLengthMax;
	}

	public void setIntersectionLengthMax(Double intersectionLengthMax) {
		this.intersectionLengthMax = intersectionLengthMax;
	}

	public Double getCarLengthMin() {
		return carLengthMin;
	}

	public void setCarLengthMin(Double carLengthMin) {
		this.carLengthMin = carLengthMin;
	}

	public Double getCarLengthMax() {
		return carLengthMax;
	}

	public void setCarLengthMax(Double carLengthMax) {
		this.carLengthMax = carLengthMax;
	}

	public Double getCarMaxVelocityMin() {
		return carMaxVelocityMin;
	}

	public void setCarMaxVelocityMin(Double carMaxVelocityMin) {
		this.carMaxVelocityMin = carMaxVelocityMin;
	}

	public Double getCarMaxVelocityMax() {
		return carMaxVelocityMax;
	}

	public void setCarMaxVelocityMax(Double carMaxVelocityMax) {
		this.carMaxVelocityMax = carMaxVelocityMax;
	}

	public Double getCarStopDistanceyMin() {
		return carStopDistanceMin;
	}

	public void setCarStopDistanceyMin(Double carStopDistanceyMin) {
		this.carStopDistanceMin = carStopDistanceyMin;
	}

	public Double getCarStopDistanceMax() {
		return carStopDistanceMax;
	}

	public void setCarStopDistanceMax(Double carStopDistanceyMax) {
		this.carStopDistanceMax = carStopDistanceyMax;
	}

	public Double getCarBrakeDistanceMin() {
		return carBrakeDistanceMin;
	}

	public void setCarBrakeDistanceMin(Double carBrakeDistanceyMin) {
		this.carBrakeDistanceMin = carBrakeDistanceyMin;
	}

	public Double getCarBrakeDistanceMax() {
		return carBrakeDistanceMax;
	}

	public void setCarBrakeDistanceMax(Double carBrakeDistanceyMax) {
		this.carBrakeDistanceMax = carBrakeDistanceyMax;
	}

	public Double getTrafficLightGreenTimeMin() {
		return trafficLightGreenTimeMin;
	}

	public void setTrafficLightGreenTimeMin(Double trafficLightGreenTimeMin) {
		this.trafficLightGreenTimeMin = trafficLightGreenTimeMin;
	}

	public Double getTrafficLightGreenTimeMax() {
		return trafficLightGreenTimeMax;
	}

	public void setTrafficLightGreenTimeMax(Double trafficLightGreenTimeMax) {
		this.trafficLightGreenTimeMax = trafficLightGreenTimeMax;
	}

	public Double getTrafficLightYellowTimeMin() {
		return trafficLightYellowTimeMin;
	}

	public void setTrafficLightYellowTimeMin(Double trafficLightYellowTimeMin) {
		this.trafficLightYellowTimeMin = trafficLightYellowTimeMin;
	}

	public Double getTrafficLightYellowTimeMax() {
		return trafficLightYellowTimeMax;
	}

	public void setTrafficLightYellowTimeMax(Double trafficLightYellowTimeMax) {
		this.trafficLightYellowTimeMax = trafficLightYellowTimeMax;
	}
}
