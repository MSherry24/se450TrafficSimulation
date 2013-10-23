package model;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RoadTEST extends TestCase {
	//TODO
		  public RoadTEST(String name) {
		    super(name);
		  }
		  public void testConstructorAndAttributes() {
			Car c1 = new Car();
			c1.setVelocity(5.0);

			Road r1 = new Road(100.0);
			r1.addVehicle(c1);
			
			assertEquals(r1.getDistanceToNextObject(c1), r1.getLength());
			try { r1.addVehicle(null); fail(); } catch (Exception e) { }
			
		  }
	}