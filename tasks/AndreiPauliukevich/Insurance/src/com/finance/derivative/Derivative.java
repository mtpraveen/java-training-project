package com.finance.derivative;

import com.finance.insuranceLiability.HealthInsurance;
import com.finance.insuranceLiability.Insurance;
import com.finance.insuranceLiability.VehicleInsurance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class Derivative {

	private List<Insurance> insurance = new ArrayList<Insurance>();

	{
		insurance.add(new HealthInsurance());
		insurance.add(new VehicleInsurance());
	}

	/**
	 * Calculates the cost of derivative
	 * 
	 * @return price
	 */
	public double price() {
		double price = 0;
		for (Insurance i : insurance) {
			price += i.getPrice();
		}
		return price;
	}

	/**
	 * Sorting on the basis of reduction of risks
	 * 
	 * @return list of insurance
	 */
	public List<Insurance> sortInsurance() {
		List<Insurance> list = new ArrayList<Insurance>(insurance);
		Collections.sort(list, new DerivativeComparator());
		return list;
	}

	/**
	 * Search by title
	 * 
	 * @param title
	 * @return insurance
	 */
	public Insurance search(String title) {
		Insurance result = null;
		for (Insurance i : insurance) {
			if (i.getTitle().equals(title)) {
				result = i;
			}
		}
		return (result != null ? result : null);
	}

	/**
	 * Search by risk
	 * 
	 * @param risk
	 * @return insurance
	 */
	public Insurance search(int risk) {
		Insurance result = null;
		for (Insurance i : insurance) {
			if (i.getRisk() == risk) {
				result = i;
			}
		}
		return (result != null ? result : null);
	}

	/**
	 * Search by price
	 * 
	 * @param price
	 * @return insurance
	 */
	public Insurance search(double price) {
		Insurance result = null;
		for (Insurance i : insurance) {
			if (i.getPrice() == price) {
				result = i;
			}
		}
		return (result != null ? result : null);
	}

	/**
	 * Search by title and price
	 * 
	 * @param title
	 * @param price
	 * @return insurance
	 */
	public Insurance search(String title, double price) {
		Insurance result = null;
		for (Insurance i : insurance) {
			if (i.getTitle().equals(title) && i.getPrice() == price) {
				result = i;
			}
		}
		return (result != null ? result : null);
	}

	/**
	 * @return the insurance
	 */
	public List<Insurance> getInsurance() {
		return insurance;
	}

	/**
	 * @param insurance
	 *            the insurance to set
	 */
	public void setInsurance(List<Insurance> insurance) {
		this.insurance = insurance;
	}

}
