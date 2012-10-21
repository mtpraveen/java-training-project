package com.finance.derivative;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.finance.insuranceLiability.HealthInsurance;
import com.finance.insuranceLiability.Insurance;
import com.finance.insuranceLiability.VehicleInsurance;

public class DerivateTest {

	Derivative derivative = new Derivative();
	List<Insurance> insurance = derivative.getInsurance();

	@Before
	public void init() {
		for (Insurance i : derivative.getInsurance()) {
			if (i instanceof HealthInsurance) {
				i.setTitle("Health Insurance");
				i.setPrice(999.99);
				i.setRisk(10);
			}
			if (i instanceof VehicleInsurance) {
				i.setTitle("Vehicle Insurance");
				i.setPrice(99.99);
				i.setRisk(30);
			}
		}
	}

	@Test
	public void testCreateInsurance() {
		for (Insurance i : insurance) {
			if (i instanceof HealthInsurance) {
				assertEquals("Health Insurance", i.getTitle());
				assertTrue(i.getPrice() == 999.99);
				assertTrue(i.getRisk() == 10);
			}
			if (i instanceof VehicleInsurance) {
				assertEquals("Vehicle Insurance", i.getTitle());
				assertTrue(i.getPrice() == 99.99);
				assertTrue(i.getRisk() == 30);
			}
		}
	}

	@Test
	public void testPrice() {
		assertTrue(derivative.price() == 1099.98);
	}

	@Test
	public void testSearchByTitle() {
		assertTrue(derivative.search("Health Insurance") instanceof HealthInsurance);
		assertTrue(derivative.search("Vehicle Insurance") instanceof VehicleInsurance);
	}

	@Test
	public void testSearchByPrice() {
		assertTrue(derivative.search(99.99) instanceof VehicleInsurance);
		assertTrue(derivative.search(999.99) instanceof HealthInsurance);
	}

	@Test
	public void testSearchByRisk() {
		assertTrue(derivative.search(10) instanceof HealthInsurance);
		assertTrue(derivative.search(30) instanceof VehicleInsurance);
	}
	
	@Test
	public void testSearchByTitleAndPrice() {
		assertTrue(derivative.search("Health Insurance",999.99) instanceof HealthInsurance);
		assertTrue(derivative.search("Vehicle Insurance",99.99) instanceof VehicleInsurance);
	}

	@Test
	public void testSortInshuranceByRisk() {
		List<Insurance> list = derivative.sortInsurance();
		assertEquals(list.get(0).getRisk(), 30);
		assertEquals(list.get(1).getRisk(), 10);
		assertEquals(insurance.get(0).getRisk(), 10);
		assertEquals(insurance.get(1).getRisk(), 30);
	}
}
