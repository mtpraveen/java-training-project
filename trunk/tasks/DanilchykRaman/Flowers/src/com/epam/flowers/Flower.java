package com.epam.flowers;

public class Flower {
	enum typeOfFlower {ROSE, TULIP, LILIE};
	
	private int price;
	private final typeOfFlower type;

	public Flower(typeOfFlower T) {
		type = T;
		
		switch (T) {
		case ROSE:
			price = 20000;
			break;
		case TULIP:
			price = 15000;
			break;
		case LILIE:
			price = 30000;
			break;
		default:
			price = 0;
			break;
		}
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public typeOfFlower getType() {
		return type;
	}
}
