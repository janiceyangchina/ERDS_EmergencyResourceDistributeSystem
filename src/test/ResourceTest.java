package test;

import system.*;
import org.junit.Test;

public class ResourceTest {

	private ERDS erds = new ERDS();

	/**
	 * Create a resource normally.  Ensure that the name and description are set
	 * and that the resource was added to the ERDS.
	 */
	@Test
	public void test_normal_resource_creation() {
		String testName = "gloves";
		String testDescription = "Sterile nitrile gloves.";
		Resource resource = new Resource(testName, testDescription, this.erds);
		
		assert(erds.getResource(0) == resource);
		assert(resource.getName() == testName);
		assert(resource.getDescription() == testDescription);
	}
	
	/**
	 * Try assigning a description that is too long to a resource.  Ensure that
	 * it fails and that the resource name is unchanged.
	 */
	@Test
	public void test_resource_description_too_long() {
		String testName = "gloves";
		String initDescription = "Sterile, latex.";
		String longDescription = "supercallafrajalisticexpialladocious";
		String shortDescription = "s";
		String okDescription = "Sterile, nitrile.";
		assert(testName.length() == 36);
		
		Resource resource = new Resource(testName, initDescription, erds);
		
		// Assigning a description that is too long fails, and leaves the description unchanged.
		boolean wasChanged = resource.setDescription(longDescription);
		assert(!wasChanged);
		assert(resource.getDescription() == initDescription);
		
		// Assigning a description that is too short fails, and leaves the description unchanged.
		wasChanged = resource.setDescription(shortDescription);
		assert(!wasChanged);
		assert(resource.getDescription() == initDescription);
		
		// Assigning a description of an appropriate length succeeds.
		wasChanged = resource.setDescription(okDescription);
		assert(wasChanged);
		assert(resource.getDescription() == okDescription);
		
	}

}
