package test;

import static org.junit.Assert.*;

import org.junit.Test;
import system.*;
import system.Package;

public class PackageTest {

	ERDS erds = new ERDS();
	
	@Test
	public void test_package_quantity() {
		String name = "gloves";
		String description = "Nitrile gloves";
		
		int initQuantity = 3;
		int zeroQuantity = 0;
		int negativeQuantity = -1;
		int okQuantity = 5;
		
		Package pack = new Package(name, description, erds, initQuantity, initQuantity, initQuantity);

		// setting the quantity to zero fails and leaves the quantity unchanged
		boolean wasChanged = pack.setQuantityUnits(zeroQuantity);
		assert(!wasChanged);
		assert(pack.getQuantityUnits() == initQuantity);

		// setting the quantity to a negative value fails and leaves the quantity unchanged
		wasChanged = pack.setQuantityUnits(negativeQuantity);
		assert(!wasChanged);
		assert(pack.getQuantityUnits() == initQuantity);
		
		// setting the quantity to a pasitive value succeeds
		wasChanged = pack.setQuantityUnits(okQuantity);
		assert(wasChanged);
		assert(pack.getQuantityUnits() == okQuantity);
		

		// setting the weight to zero fails and leaves the quantity unchanged
		wasChanged = pack.setWeight(zeroQuantity);
		assert(!wasChanged);
		assert(pack.getWeight() == initQuantity);

		// setting the weight to a negative value fails and leaves the quantity unchanged
		wasChanged = pack.setWeight(negativeQuantity);
		assert(!wasChanged);
		assert(pack.getWeight() == initQuantity);
		
		// setting the weight to a pasitive value succeeds
		wasChanged = pack.setWeight(okQuantity);
		assert(pack.getWeight() == okQuantity);
		assert(wasChanged);
		

		// setting the dimension to zero fails and leaves the quantity unchanged
		wasChanged = pack.setDimension(zeroQuantity);
		assert(!wasChanged);
		assert(pack.getDimension() == initQuantity);

		// setting the dimension to a negative value fails and leaves the quantity unchanged
		wasChanged = pack.setDimension(negativeQuantity);
		assert(!wasChanged);
		assert(pack.getDimension() == initQuantity);
		
		// setting the dimension to a pasitive value succeeds
		wasChanged = pack.setDimension(okQuantity);
		assert(wasChanged);
		assert(pack.getDimension() == okQuantity);
		
	}

}
