package com.epam.application;

import java.util.List;

import com.epam.incomes.IncomeFromWork;
import com.epam.incomes.NetIncome;
import com.epam.incomes.TaxableIncome;
import com.epam.taxes.TaxesHandler;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IncomeFromWork mainWork = new IncomeFromWork(1000, 12);
		IncomeFromWork additionalWork = new IncomeFromWork(400, 9);
		TaxableIncome royalty = new TaxableIncome(2000, 3);
		TaxableIncome dispositionOfProperty = new TaxableIncome(4000, 2);
		TaxesHandler taxesHandler = new TaxesHandler();
		taxesHandler.addTaxe(mainWork.getTaxPayments());
		taxesHandler.addTaxe(additionalWork.getTaxPayments());
		taxesHandler.addTaxe(royalty.getTaxPayments());
		taxesHandler.addTaxe(dispositionOfProperty.getTaxPayments());
		System.out.println("Taxes of person: ");
		System.out.println(taxesHandler.getTaxes());
		System.out.println("Sorted taxes of person: ");
		taxesHandler.sortTaxes();
		System.out.println(taxesHandler.getTaxes());
	}

}
