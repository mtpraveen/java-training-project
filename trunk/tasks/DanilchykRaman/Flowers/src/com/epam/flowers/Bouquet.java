package com.epam.flowers;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
	private List<Flower> bouquet;
	
	public Bouquet() {
		bouquet = new ArrayList<Flower>();
	}
	
	public int getPrice() {
		int price = 0;
		
		for (int i = 0; i < bouquet.size(); i++) {
			price += bouquet.get(i).getPrice();
		}
		
		return price;		
	}
	
	public void addFlower(Flower flower) {
		bouquet.add(flower);
	}
	
	public int getNumberOfFlowers() {
		return bouquet.size();
	}
	
	public String toString() {
		String string = new String("Bouquet consists of:\n");
		
		for (int i = 0; i < bouquet.size(); i++) {
			string = string.concat(bouquet.get(i).getName()).concat(" - ").concat(Integer.toString(bouquet.get(i).getPrice()).concat("\n"));
		}
		
		return string;
	}
}
