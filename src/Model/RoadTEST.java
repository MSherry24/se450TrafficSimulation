package model;

import java.util.Iterator;
import java.util.Set;

import model.Car.Orientation;

import random.Util;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class RoadTEST extends TestCase {

	PropertyBag propertyBag = new PropertyBag();
	TimeServerLinked t1 = new TimeServerLinked();
	Car c1 = new Car(propertyBag, t1, Orientation.EW);
	Road r1 = new Road(propertyBag);
	Road r2 = new Road(propertyBag);

	public RoadTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {

		Assert.assertNotNull(r1.getCars());
		Assert.assertEquals(r1.getCars().size(), 0, Util.EPSILON);
		Assert.assertTrue(r1.getEndPosition() <= propertyBag.getRoadSegmentLengthMax());
		Assert.assertTrue(r1.getEndPosition() >= propertyBag.getRoadSegmentLengthMin());
		r1.setNextRoad(r2);
		Assert.assertEquals(r1.getNextRoad(null), r2);
	}

	public void testCarAcceptAndRemove() {


		Assert.assertEquals(r1.getCars().size(), 0);
		r1.accept(c1, 0.0);
		Assert.assertEquals(r1.getCars().size(), 1);
		Iterator<Car> i1 = r1.getCars().iterator();
		while (i1.hasNext()) {
			Car current = i1.next();
			Assert.assertEquals(current.getCurrentRoad(), r1);
			Assert.assertEquals(current, c1);
		}
		r1.remove(c1);
		Assert.assertEquals(r1.getCars().size(), 0);
	}

	public void testDistanceToObstacle() {	
		r1.setNextRoad(r2);
		r1.accept(c1, r1.getEndPosition() / 2);
		Assert.assertEquals(r1.getCars().size(), 1);
		Assert.assertTrue(Util.isEquals(r1.distanceToObstacle(0.0, c1.getOrientation()), c1.getBackPosition()));
	}
}
