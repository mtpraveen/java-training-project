package com.epam.incomes;

public class IncomeFromWork extends TaxableIncome{
	private double salary;
	
	public IncomeFromWork(double salary, double tax) {
		super(calculateProfit(salary), tax);
		this.salary = salary;
		this.tax = tax;  
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void raiseSalary(double increase) {
		setSalary(getSalary() + increase);
	}
	
	private static double calculateProfit(double salary) {
		return salary * 12;
	}
}
