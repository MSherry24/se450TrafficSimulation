package model;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CarTEST extends TestCase {
//TODO
	  public CarTEST(String name) {
	    super(name);
	  }
	  public void testConstructorAndAttributes() {
		Car c1 = new Car();
		c1.setVelocity(5.0);

	    Assert.assertEquals(c1.getVelocity(), 5.0);
	    Assert.assertEquals(c1.getLocation(), 0.0);


	  }
}
