package com.epam.flowers;

public class Rose extends Flower {
	
	private RoseTypes type;
	
	public Rose(String name, RoseTypes type, int price) {
		super(name, price);
		this.type = type;
	}

	public RoseTypes getType() {
		return type;
	}

	public void setType(RoseTypes type) {
		this.type = type;
	}
}
