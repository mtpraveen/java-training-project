package com.epam.flowers;

public enum RoseTypes {
	BLACK (25000), WHITE (23000), RED (20000);
	
	private int price;
	
	RoseTypes(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}