package com.epam.flowers;

import java.util.HashMap;
import java.util.Map;

public class Rose extends Flower {
	
	private typeOfRose type;
	public static Map<typeOfRose, Integer> priceList = new HashMap<typeOfRose, Integer>();
	
	static {
		priceList.put(typeOfRose.BLACK, 25000);
		priceList.put(typeOfRose.WHITE, 23000);
		priceList.put(typeOfRose.RED, 20000);
	}
	
	public Rose(String name, typeOfRose type, int price) {
		super(name, price);
		this.type = type;
		
		
	}

	public typeOfRose getType() {
		return type;
	}

	public void setType(typeOfRose type) {
		this.type = type;
	}
	
	public static int getPriceFromList(typeOfRose type) {
		return (int) priceList.get(type);
	}
}
