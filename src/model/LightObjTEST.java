package model;

import junit.framework.Assert;
import junit.framework.TestCase;
import properties.PropertyBag;

public class LightObjTEST extends TestCase {
	Data dataFactory = new Data();

	PropertyBag propertyBag = PropertyBag.makePropertyBag();
	Light l1;


	public LightObjTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		l1 = Data.makeLight();
		

		Assert.assertTrue(l1.getGreenTimeEW() <= propertyBag.getTrafficLightGreenTimeMax());
		Assert.assertTrue(l1.getGreenTimeEW() >= propertyBag.getTrafficLightGreenTimeMin());
		
		Assert.assertTrue(l1.getGreenTimeNS() <= propertyBag.getTrafficLightGreenTimeMax());
		Assert.assertTrue(l1.getGreenTimeNS() >= propertyBag.getTrafficLightGreenTimeMin());
		
		Assert.assertTrue(l1.getYellowTimeEW() <= propertyBag.getTrafficLightYellowTimeMax());
		Assert.assertTrue(l1.getYellowTimeEW() >= propertyBag.getTrafficLightYellowTimeMin());

		Assert.assertTrue(l1.getYellowTimeNS() <= propertyBag.getTrafficLightYellowTimeMax());
		Assert.assertTrue(l1.getYellowTimeNS() >= propertyBag.getTrafficLightYellowTimeMin());
	}
}
