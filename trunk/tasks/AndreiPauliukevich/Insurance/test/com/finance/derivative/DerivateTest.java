package com.finance.derivative;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.finance.model.HealthInsurance;
import com.finance.model.Insurance;
import com.finance.model.VehicleInsurance;

public class DerivateTest {

	private static Derivative derivative = new Derivative();
	private static List<Insurance> result;

	private static HealthInsurance healthInsurance;
	private static VehicleInsurance vehicleInsurance;

	private static final String healthInsuranceTitle = "Health Insurance";
	private static final double healthInsurancePrice = 999.99;
	private static final int healthInsuranceRisk = 10;
	private static final String firstName = "Ivan";
	private static final String middleName = "Ivanovich";
	private static final String lastName = "Ivanov";

	private static final String vehicleInsuranceTitle = "Vehicle Insurance";
	private static final double vehicleInsurancePrice = 99.99;
	private static final int vehicleInsuranceRisk = 30;
	private static final String modelName = "modelCar";
	private static final int yearManufactured = 2005;

	@BeforeClass
	public static void init() {
		healthInsurance = new HealthInsurance(healthInsuranceTitle,
				healthInsurancePrice, healthInsuranceRisk, firstName,
				middleName, lastName);
		derivative.addInsurance(healthInsurance);
		vehicleInsurance = new VehicleInsurance(vehicleInsuranceTitle,
				vehicleInsurancePrice, vehicleInsuranceRisk, modelName,
				yearManufactured);
		derivative.addInsurance(vehicleInsurance);
	}

	@Test
	public void testPrice() {
		assertTrue(derivative.getPrice() == 1099.98);
	}

	@Test
	public void testSearchByTitle() {
		result = new ArrayList<Insurance>();
		assertEquals(result, derivative.searchByTitle("false"));
		result.add(healthInsurance);
		assertEquals(result.get(0).getTitle(),derivative.searchByTitle(healthInsuranceTitle).get(0).getTitle());
		result.clear();
		result.add(vehicleInsurance);
		assertEquals(result.get(0).getTitle(),derivative.searchByTitle(vehicleInsuranceTitle).get(0).getTitle());
	}

	@Test
	public void testSearchByPrice() {
		assertTrue(0 == derivative.searchByPrice(5).size());
		for (Insurance i : derivative.searchByPrice(healthInsurancePrice)) {
			assertTrue(healthInsurancePrice == i.getPrice());
		}
		for (Insurance i : derivative.searchByPrice(vehicleInsurancePrice)) {
			assertTrue(vehicleInsurancePrice == i.getPrice());
		}
	}

	@Test
	public void testSearchByRisk() {
		result = new ArrayList<Insurance>();
		assertEquals(result.size(), derivative.searchByRisk(45).size());
		result.add(healthInsurance);
		assertEquals(result, derivative.searchByRisk(healthInsuranceRisk));
		result.clear();
		result.add(vehicleInsurance);
		assertEquals(result, derivative.searchByRisk(vehicleInsuranceRisk));
	}

	@Test
	public void testSearchByTitleAndPrice() {
		result = new ArrayList<Insurance>();
		assertEquals(result, derivative.searchByTitleAndPrice(
				healthInsuranceTitle, vehicleInsuranceRisk));
		result.add(healthInsurance);
		assertEquals(result, derivative.searchByTitleAndPrice(
				healthInsuranceTitle, healthInsuranceRisk));
	}

	@Test
	public void sortInsurancesByRiskReduction() {
		result = derivative.sortInsurancesByRiskReduction();
		assertTrue(result.get(0).getRisk() >= result.get(1).getRisk());
	}

	@Test
	public void testRemove() {
		result = new ArrayList<Insurance>();
		result.add(healthInsurance);
		derivative.removeInsurance(vehicleInsurance);
		assertEquals(result, derivative.getInsurances());
	}

}
