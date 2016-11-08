package test;

import static org.junit.Assert.*;
import system.*;

import org.junit.Test;

public class TestResourceNeed {

	// Create objects that are needed in the tests
	ERDS erds = new ERDS();
	Emergency emergency = new Emergency("Ottawa", erds);
	Resource resource = new Resource("gloves", "Sterile nitrile", erds);
	Organization organization = new Organization("Red Cross", "Ottawa", erds);

	@Test
	public void test_set_quantity() {
		int initQuantity = 50;
		int zeroQuantity = 0;
		int negativeQuantity = -1;
		int okQuantity = 100;
		
		ResourceNeed rn = new ResourceNeed(initQuantity, resource, emergency);
		
		// Assigning a zero quantity fails, and leaves the quantity unchanged
		boolean wasChanged = rn.setRequiredQuantity(zeroQuantity);
		assert(!wasChanged);
		assert(rn.getRequiredQuantity() == initQuantity);
		
		// Assigning a negative quantity fails, and leaves the quantity unchanged
		wasChanged = rn.setRequiredQuantity(negativeQuantity);
		assert(!wasChanged);
		assert(rn.getRequiredQuantity() == initQuantity);
		
		// Assigning a positive quantity succeeds.
		wasChanged = rn.setRequiredQuantity(okQuantity);
		assert(wasChanged);
		assert(rn.getRequiredQuantity() == okQuantity);
	}

}
