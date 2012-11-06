package com.epam.flowers;

public enum LilyTypes {
	CASSANDRA (35000), GOLDEN (33000), PINK (29000);
	
	private int price;
	
	LilyTypes(int price) {
		this.price = price;
	} 
	
	public int getPrice() {
		return price;
	}
}