package com.epam.flowers;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
	private List<Flower> bouquet;
	private int numberOfFlowers;
	
	public Bouquet() {
		bouquet = new ArrayList<Flower>();
		numberOfFlowers = 0;
	}
	
	public int getPrice() {
		int price = 0;
		
		for (int i = 0; i < numberOfFlowers; i++) {
			price += bouquet.get(i).getPrice();
		}
		
		return price;		
	}
	
	public void addFlower(Flower flower) {
		bouquet.add(flower);
		numberOfFlowers++;
	}
	
	public int getNumberOfFlowers() {
		return numberOfFlowers;
	}
	
	public void print() {
		System.out.println("Bouquet consists of: ");
		
		for (int i = 0; i < bouquet.size(); i++) {
			System.out.println(bouquet.get(i).getName() + " - " + bouquet.get(i).getPrice());
		}
	}
}
