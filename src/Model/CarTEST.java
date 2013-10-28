package model;

import random.Util;
import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class CarTEST extends TestCase {
	//TODO
	public CarTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		PropertyBag propertyBag = new PropertyBag();
		Car c1 = new Car(propertyBag);
		Assert.assertEquals(c1.getCurrentRoad(),null);
		Road r1 = new Road(propertyBag);
		r1.accept(c1, 0.0);
		Assert.assertEquals(r1, c1.getCurrentRoad());
	}
	
	public void testMethods() {
		PropertyBag propertyBag = new PropertyBag();
		Car c1 = new Car(propertyBag);
		Road r1 = new Road(propertyBag);
		r1.accept(c1,0.0);
		Assert.assertTrue(Util.isLessOrEquals(-c1.getBackPosition(), propertyBag.getCarLengthMax()));
	}
}
