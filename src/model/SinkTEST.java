package model;

import model.Data.Orientation;
import properties.PropertyBag;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SinkTEST extends TestCase {

	Data dataFactory = new Data();

	PropertyBag propertyBag = PropertyBag.makePropertyBag();
	Car c1;
	RoadEnd s1;


	public SinkTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		s1 = Data.makeSink();
		c1 = Data.makeCar(Data.Orientation.EW);
		Assert.assertTrue(s1.accept(c1, 10.0));
		Assert.assertEquals(s1.distanceToObstacle(0.0, Data.Orientation.EW), Double.POSITIVE_INFINITY);
		Assert.assertEquals(s1.getEndPosition(), Double.POSITIVE_INFINITY);
		Assert.assertEquals(s1.getLight(), null);
	}
}
