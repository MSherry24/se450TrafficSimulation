package model;

import java.util.Iterator;

import random.Util;

import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class RoadTEST extends TestCase {
	//TODO
	public RoadTEST(String name) {
		super(name);
	}
	public void testConstructorAndAttributes() {
		PropertyBag propertyBag = new PropertyBag();
		Car c1 = new Car(propertyBag);
		Road r1 = new Road(propertyBag);
		
		Assert.assertEquals(r1.getCars().size(), 0);
		r1.accept(c1, 0.0);
		Assert.assertEquals(r1.getCars().size(), 1);
		Iterator<Car> i1 = r1.getCars().iterator();
		while (i1.hasNext()) {
			Car current = i1.next();
			Assert.assertEquals(current.getCurrentRoad(), r1);
			Assert.assertEquals(current, c1);
		}
		
		r1.accept(c1, 15.0);
		Assert.assertEquals(r1.getCars().size(), 1);
		Assert.assertTrue(Util.isEquals(r1.distanceToObstacle(0.0), c1.getBackPosition()));
		
		
		
	}
}