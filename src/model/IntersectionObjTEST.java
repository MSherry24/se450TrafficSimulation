package model;

import java.util.Set;

import model.Data.LightState;
import model.Data.Orientation;
import junit.framework.Assert;
import junit.framework.TestCase;
import properties.PropertyBag;
import random.Util;



@SuppressWarnings("deprecation")
public class IntersectionObjTEST extends TestCase {
	Data dataFactory = new Data();

	PropertyBag propertyBag = PropertyBag.makePropertyBag();
	Car cNS, cEW;
	Road rNS, rEW;
	RoadEnd i1;


	public IntersectionObjTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		rNS = Data.makeRoad();
		rEW = Data.makeRoad();
		i1 = Data.makeIntersection();
		Assert.assertNotNull(i1.getLight());
		i1.setNextRoad(rNS, Data.Orientation.NS);
		i1.setNextRoad(rEW, Data.Orientation.EW);
		Assert.assertSame(rNS, i1.getNextRoad(Data.Orientation.NS));
		Assert.assertSame(rEW, i1.getNextRoad(Data.Orientation.EW));
		Assert.assertTrue(i1.getEndPosition() <= propertyBag.getIntersectionLengthMax());
		Assert.assertTrue(i1.getEndPosition() >= propertyBag.getIntersectionLengthMin());
	}
	
	public void testAcceptDistanceRemove() {
		i1 = Data.makeIntersection();
		cNS = Data.makeCar(Data.Orientation.NS);
		cEW = Data.makeCar(Data.Orientation.EW);
		
		Assert.assertTrue(i1.accept(cNS, 0.0));
		Assert.assertTrue(i1.accept(cEW, 0.0));		
	}
}
