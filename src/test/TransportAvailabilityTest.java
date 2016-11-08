package test;

import static org.junit.Assert.*;
import system.*;			

import org.junit.Test;

public class TransportAvailabilityTest {

	// Create objects that are needed in the tests
	ERDS erds = new ERDS();
	Transportation transportation = new Transportation("Truck", "18-wheeler", erds, "20 m3");
	Company company = new Company("Acme Trucking", "Montreal", erds);
	TransportationType transpoType = new TransportationType("", "", "Land");

	@Test
	public void test_set_geographic_range() {
		int quantity = 1;
		String initGeographicRange = "Mantreal Island";
		String longRange = "Montreal;, Laval, Dorval";
		String shortRange = "a";
		String okRange = "Quebec";
		
		TransportAvailability transpoAvailability = new TransportAvailability(
			quantity, (Organization)company, (Resource)transportation, transpoType, initGeographicRange);
		
		// Assigning a long geographic range fails and leaves the range unchanged
		boolean wasChanged = transpoAvailability.setGeographicRange(longRange);
		assert(!wasChanged);
		assert(transpoAvailability.getGeographicRange() == initGeographicRange);
		
		// Assigning a short geographic range fails and leaves the range unchanged
		wasChanged = transpoAvailability.setGeographicRange(shortRange);
		assert(!wasChanged);
		assert(transpoAvailability.getGeographicRange() == initGeographicRange);
		
		// Assigning an appropriate-length geographic range succeeds
		wasChanged = transpoAvailability.setGeographicRange(okRange);
		assert(wasChanged);
		assert(transpoAvailability.getGeographicRange() == okRange);
		
	}

}
