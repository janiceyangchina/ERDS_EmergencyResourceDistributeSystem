package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import system.*;


public class TransportTest {

	ERDS erds = new ERDS();
	
	/**
	 * Test making a transport normally.  Ensure that the transport properties are
	 * assigned correctly by the constructor, and that the tranpsort is registered
	 * to the ERDS
	 */
	@Test
	public void test_create_transport() {
		String testName = "Truck";
		String testDescription = "16-wheeler transport";
		String testCargo = "20 m3";
		Transportation transportation = new Transportation(testName, testDescription, erds, testCargo);

		assert(transportation.getName() == testName);
		assert(transportation.getDescription() == testDescription);
		assert(transportation.getCargoCapacity() == testCargo);
		assert(transportation.getERDS() == erds);
		
		assert(erds.getResource(0) == transportation);
	}
	
	/**
	 * Test assigning a name that is too long or too short, and likewise for the description
	 * Ensure that assigning an appropriate-lengthed name succeeds.
	 */
	public void test_name_desc_long_short() {
		String initName = "Truck";
		String longName = "My cousin's eighteen wheeler";
		String shortName = "t";
		String medName = "Tanker";
		
		String initDescription = "16-wheeler";
		String longDescription = "16-wheeler transport truck";
		String shortDescription = "a";
		String medDescription = "18-wheeler";
		
		String testCargo = "20 m3";

		Transportation transportation = new Transportation(initName, initDescription, erds, testCargo);
		
		// Setting a name that is too long fails and leaves the name unchanged
		boolean wasChanged = transportation.setName(longName);
		assert(!wasChanged);
		assert(transportation.getName() == initName);
		
		// Setting a name that is too short fails and leaves the name unchanged
		wasChanged = transportation.setName(shortName);
		assert(!wasChanged);
		assert(transportation.getName() == initName);
		
		// Setting an appropriate-length name succeeds
		wasChanged = transportation.setName(medName);
		assert(wasChanged);
		assert(transportation.getName() == medName);
		
		// Setting a description that is too long fails and leaves the description unchanged
		wasChanged = transportation.setDescription(longDescription);
		assert(!wasChanged);
		assert(transportation.getDescription() == initDescription);
		
		// Setting a description that is too short fails and leaves the description unchanged
		wasChanged = transportation.setDescription(shortDescription);
		assert(!wasChanged);
		assert(transportation.getDescription() == initDescription);
		
		// Setting an appropriate-length description succeeds
		wasChanged = transportation.setDescription(medDescription);
		assert(wasChanged);
		assert(transportation.getDescription() == medDescription);
	}

}
