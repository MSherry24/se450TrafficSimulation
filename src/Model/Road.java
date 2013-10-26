package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Road implements CarAcceptor {

	Set cars;
	Double endPosition;
	CarAcceptor nextRoad;

	public boolean accept(Car c, Double frontPosition) {
		cars.remove(c);
		if(frontPosition>endPosition) {
			return nextRoad.accept(c,frontPosition-endPosition);
		} else {
			c.setCurrentRoad(this);
			c.setFrontPosition(frontPosition);
			cars.add(c);
			return true;
		}
	}

}
