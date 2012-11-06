package com.epam.flowers;

public class Lily extends Flower {

	private LilyTypes type;
	
	public Lily(String name, LilyTypes type, int price) {
		super(name, price);
		this.type = type;
	}

	public LilyTypes getType() {
		return type;
	}

	public void setType(LilyTypes type) {
		this.type = type;
	}
}
