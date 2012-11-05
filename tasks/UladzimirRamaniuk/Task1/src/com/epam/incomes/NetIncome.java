package com.epam.incomes;

public class NetIncome extends AbstractIncome {

	public NetIncome(double profit) {
		super(profit);
	}

	@Override
	public double getIncome() {
		income = profit;
		return income;
	}
}