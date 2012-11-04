package com.epam.incomes;

public class TaxableIncome extends AbstractIncome {
	protected double taxPayments;
	protected double tax;
	
	public TaxableIncome(double profit, double tax) {
		super(profit);
		this.tax = tax;
	}

	public double getTax() {
		return tax;
	}

	public double getTaxPayments() {
		this.taxPayments = getProfit() * (getTax() / 100);
		return taxPayments;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	
	@Override
	public double getIncome() {
		income = getProfit() - getTaxPayments();
		return income;
	}
}
