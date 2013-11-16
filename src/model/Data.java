package model;

public class Data {
	
	public enum Orientation {
		NS, EW
	}

	public Data() {
		
	}
	
	static public final Car makeCar(Orientation orientation) {
		return new Car(orientation);
	}
	
	static public final Road makeRoad() {
		return new Road();
	}
	
	static public final RoadEnd makeIntersection() {
		return new IntersectionObj();
	}
	
	static public final RoadEnd makeSink() {
		return new Sink();
	}
	
	static public final CarSource makeSource(Orientation orientation) {
		return new CarSourceObj(orientation);
	}
	
	static public final LightObj makeLight() {
		return new LightObj();
	}
}
