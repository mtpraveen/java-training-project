package com.epam.task1.exceptions;

public class NegativeFeeException extends RuntimeException {
	private double fee;
	public NegativeFeeException(double fee) {
		this.fee = fee;
	}
	public double getFee() {
		return fee;
	}
	@Override
	public String getMessage() {
		return "Fee = " + fee + ".Fee cannot be negative";
	}
}
