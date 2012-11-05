package com.epam.taxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaxesHandler {
	private List<Double> taxes;
	
	public TaxesHandler() {
		taxes = new ArrayList<Double>();
	}
	
	public void addTaxe(double taxe) {
		taxes.add(taxe);
	}
	
	public double getTaxe(int index) {
		return taxes.get(index);
	}
	
	public List<Double> getTaxes() {
		return taxes;
	}
	
	public void sortTaxes() {
		Collections.sort(taxes);
	}
}
