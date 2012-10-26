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

	@Before
	public void init() {
		HealthInsurance h = new HealthInsurance("Health Insurance", 999.99, 10,
				"Ivan", "Ivanovich", "Ivanov");
		derivative.addInsurance(h);
		VehicleInsurance v = new VehicleInsurance("Vehicle Insurance", 99.99,
				30, "modelCar", 2005);
		derivative.addInsurance(v);
	}

	@Test
	public void testCreateInsurance() throws CloneNotSupportedException {
		for (Insurance i : derivative.getInsurances()) {
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
		assertTrue(derivative.searchByTitle("Health Insurance") instanceof HealthInsurance);
		assertTrue(derivative.searchByTitle("Vehicle Insurance") instanceof VehicleInsurance);
	}

	@Test
	public void testSearchByPrice() {
		assertTrue(derivative.searchByPrice(99.99) instanceof VehicleInsurance);
		assertTrue(derivative.searchByPrice(999.99) instanceof HealthInsurance);
	}

	@Test
	public void testSearchByRisk() {
		assertTrue(derivative.searchByRisk(10) instanceof HealthInsurance);
		assertTrue(derivative.searchByRisk(30) instanceof VehicleInsurance);
	}

	@Test
	public void testSearchByFirstName() {
		assertEquals(derivative.searchByFirstName("Ivan").getFirstName(), "Ivan");
	}

	@Test
	public void testSearchByTitleAndPrice() {
		assertTrue(derivative.searchByTitleAndPrice("Health Insurance", 999.99) instanceof HealthInsurance);
		assertTrue(derivative.searchByTitleAndPrice("Vehicle Insurance", 99.99) instanceof VehicleInsurance);
	}

	@Test
	public void testSortInshuranceByRisk() throws CloneNotSupportedException {
		List<Insurance> list = derivative.sortInsurances();
		assertEquals(list.get(0).getRisk(), 30);
		assertEquals(list.get(1).getRisk(), 10);
		List<Insurance> insurances = derivative.getInsurances();
		assertEquals(insurances.get(0).getRisk(), 10);
		assertEquals(insurances.get(1).getRisk(), 30);
	}

	@Test
	public void removeInsuranceByTitle() throws CloneNotSupportedException {
		assertTrue(derivative.getInsurances().size() == 2);
		derivative.removeInsuranceByTitle("Health Insurance");
		assertTrue(derivative.getInsurances().size() == 1);
	}
}
