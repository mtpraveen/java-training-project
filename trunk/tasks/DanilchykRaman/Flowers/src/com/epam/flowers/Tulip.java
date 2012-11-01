package com.epam.flowers;

import java.util.HashMap;
import java.util.Map;

public class Tulip extends Flower {

	private typeOfTulip type;
	public static Map<typeOfTulip, Integer> priceList = new HashMap<typeOfTulip, Integer>();
	
	static {
		priceList.put(typeOfTulip.ALTAICA, 30000);
		priceList.put(typeOfTulip.CLUSIANA, 21000);
		priceList.put(typeOfTulip.MONTANA, 26000);
	}
	
	public Tulip(String name, typeOfTulip type, int price) {
		super(name, price);
		this.type = type;
	}

	public typeOfTulip getType() {
		return type;
	}

	public void setType(typeOfTulip type) {
		this.type = type;
	}
	
	public static int getPriceFromList(typeOfTulip type) {
		return (int) priceList.get(type);
	}
}
