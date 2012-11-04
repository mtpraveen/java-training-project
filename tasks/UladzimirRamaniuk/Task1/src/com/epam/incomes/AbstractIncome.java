package com.epam.incomes;

public abstract class AbstractIncome {
	protected double profit;
	protected double income;
	
	public AbstractIncome(double profit) {
		this.profit = profit;
	}

	abstract double getIncome();

	public double getProfit() {
		return profit;
	}
	
	public void setProfit(double profit) {
		this.profit = profit;
	}
}
