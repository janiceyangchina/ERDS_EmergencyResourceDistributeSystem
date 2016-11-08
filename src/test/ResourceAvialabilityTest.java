package test;

import static org.junit.Assert.*;
import system.*;
import org.junit.Test;

public class ResourceAvialabilityTest {

	// Create objects that are needed in the tests
	ERDS erds = new ERDS();
	Resource resource = new Resource("gloves", "Sterile nitrile", erds);
	Organization organization = new Organization("Red Cross", "Ottawa", erds);

	/**
	 * Test that the resource quantity cannot be set to a zero or negative quantity, 
	 * but ensure that setting a valid quantity works properly.
	 */
	@Test
	public void test_set_quantity() {
		int initQuantity = 50;
		int zeroQuantity = 0;
		int negativeQuantity = -1;
		int okQuantity = 100;
		
		ResourceAvailability ra = new ResourceAvailability(initQuantity, organization, resource);
		
		// Assigning a zero quantity fails, and leaves the quantity unchanged
		boolean wasChanged = ra.setQuantity(zeroQuantity);
		assert(!wasChanged);
		assert(ra.getQuantity() == initQuantity);
		
		// Assigning a negative quantity fails, and leaves the quantity unchanged
		wasChanged = ra.setQuantity(negativeQuantity);
		assert(!wasChanged);
		assert(ra.getQuantity() == initQuantity);
		
		// Assigning a positive quantity succeeds.
		wasChanged = ra.setQuantity(okQuantity);
		assert(wasChanged);
		assert(ra.getQuantity() == okQuantity);
	}

}
