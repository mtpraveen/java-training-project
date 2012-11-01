package com.epam.flowers;

import java.util.HashMap;
import java.util.Map;

public class Lily extends Flower {

	private typeOfLily type;
	public static Map<typeOfLily, Integer> priceList = new HashMap<typeOfLily, Integer>();
	
	static {
		priceList.put(typeOfLily.CASSANDRA, 35000);
		priceList.put(typeOfLily.GOLDEN, 33000);
		priceList.put(typeOfLily.PINK, 29000);
	}
	
	public Lily(String name, typeOfLily type, int price) {
		super(name, price);
		this.type = type;
	}

	public typeOfLily getType() {
		return type;
	}

	public void setType(typeOfLily type) {
		this.type = type;
	}
	
	public static int getPriceFromList(typeOfLily type) {
		return (int) priceList.get(type);
	}
}
