package com.finance.derivative;

import com.finance.insuranceLiability.Insurance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class Derivative {

	private List<Insurance> insurances = new ArrayList<Insurance>();

	/**
	 * Calculates the cost of derivative
	 * 
	 * @return price
	 */
	public double price() {
		double price = 0;
		for (Insurance i : insurances) {
			price += i.getPrice();
		}
		return price;
	}

	/**
	 * Sorting on the basis of risks reduction
	 * 
	 * @return list of insurance
	 */
	public List<Insurance> sortInsurance() {
		List<Insurance> list = new ArrayList<Insurance>(insurances);
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
		for (Insurance i : insurances) {
			if (i.getTitle().equals(title)) {
				result = i;
			}
		}
		return result;
	}

	/**
	 * Search by risk
	 * 
	 * @param risk
	 * @return insurance
	 */
	public Insurance search(int risk) {
		Insurance result = null;
		for (Insurance i : insurances) {
			if (i.getRisk() == risk) {
				result = i;
			}
		}
		return result;
	}

	/**
	 * Search by price
	 * 
	 * @param price
	 * @return insurance
	 */
	public Insurance search(double price) {
		Insurance result = null;
		for (Insurance i : insurances) {
			if (i.getPrice() == price) {
				result = i;
			}
		}
		return result;
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
		for (Insurance i : insurances) {
			if (i.getTitle().equals(title) && i.getPrice() == price) {
				result = i;
			}
		}
		return result;
	}

	/**
	 * @return the insurances
	 */
	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void addInsurance(Insurance insurance) {
		insurances.add(insurance);
	}

}
