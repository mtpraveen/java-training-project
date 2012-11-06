package com.epam.flowers;

public class Tulip extends Flower {

	private TulipTypes type;
	
	public Tulip(String name, TulipTypes type, int price) {
		super(name, price);
		this.type = type;
	}

	public TulipTypes getType() {
		return type;
	}

	public void setType(TulipTypes type) {
		this.type = type;
	}
}
