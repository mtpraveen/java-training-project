package com.epam.flowers;

public enum TulipTypes {
	ALTAICA (30000), CLUSIANA (21000), MONTANA (26000);
	
	private int price;
	
	TulipTypes(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}