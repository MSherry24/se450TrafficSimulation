package model;

import java.util.Iterator;
import properties.PropertyBag;
import model.Data.Orientation;
import random.Util;
import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class RoadTEST extends TestCase {
	Data dataFactory = new Data();

	PropertyBag propertyBag = PropertyBag.makePropertyBag();
	Car c1 = dataFactory.makeCar(Orientation.EW);
	Road r1 = dataFactory.makeRoad();
	Road r2 = dataFactory.makeRoad();
	RoadEnd i1 = dataFactory.makeIntersection();


	public RoadTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		Assert.assertNotNull(r1.getCars());
		Assert.assertEquals(r1.getCars().size(), 0, Util.EPSILON);
		Assert.assertTrue(r1.getEndPosition() <= propertyBag.getRoadSegmentLengthMax());
		Assert.assertTrue(r1.getEndPosition() >= propertyBag.getRoadSegmentLengthMin());
		r1.setNextRoad(i1);
		Assert.assertEquals(r1.getNextRoad(null), i1);
	}

	public void testCarAcceptAndRemove() {
		Assert.assertEquals(r1.getCars().size(), 0);
		r1.accept(c1, 0.0);
		Assert.assertEquals(r1.getCars().size(), 1);
		Iterator<Vehicle> i1 = r1.getCars().iterator();
		while (i1.hasNext()) {
			Vehicle current = i1.next();
			Assert.assertEquals(current.getCurrentRoad(), r1);
			Assert.assertEquals(current, c1);
		}
		r1.remove(c1);
		Assert.assertEquals(r1.getCars().size(), 0);
	}

	public void testDistanceToObstacle() {	
		r1.setNextRoad(i1);
		r1.accept(c1, r1.getEndPosition() / 2);
		Assert.assertEquals(r1.getCars().size(), 1);
		Assert.assertTrue(Util.isEquals(r1.distanceToObstacle(0.0, c1.getOrientation()), c1.getBackPosition()));
	}
}
