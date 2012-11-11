package com.finance.derivative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.finance.model.Insurance;

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
	public double getPrice() {
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
	public List<Insurance> sortInsurancesByRiskReduction() {
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
	public List<Insurance> searchByTitle(String title) {
		List<Insurance> result = new ArrayList<Insurance>();
		for (Insurance i : insurances) {
			if (i.getTitle().equals(title)) {
				result.add(i);
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
	public List<Insurance> searchByRisk(int risk) {
		List<Insurance> result = new ArrayList<Insurance>();
		for (Insurance i : insurances) {
			if (i.getRisk() == risk) {
				result.add(i);
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
	public List<Insurance> searchByPrice(double price) {
		List<Insurance> result = new ArrayList<Insurance>();
		for (Insurance i : insurances) {
			if (i.getPrice() == price) {
				result.add(i);
			}
		}
		return result;
	}
	
	public List<Insurance> searchByTitleAndPrice(String title , int risk) {
		List<Insurance> result = new ArrayList<Insurance>();
		for (Insurance i : insurances) {
			if (i.getTitle().equals(title) && i.getRisk() == risk) {
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * @return the insurances
	 * @throws CloneNotSupportedException
	 */
	public List<Insurance> getInsurances() {
		return insurances;
	}

	/**
	 * Add new insurance
	 * 
	 * @param insurance
	 */
	public void addInsurance(Insurance insurance) {
		insurances.add(insurance);
	}

	/**
	 * Remove insurance
	 * 
	 * @param insurance
	 */
	public void removeInsurance(Insurance insurance) {
		insurances.remove(insurance);
	}
}
