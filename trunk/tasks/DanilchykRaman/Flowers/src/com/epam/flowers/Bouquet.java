package com.epam.flowers;

import java.util.Random;

import com.epam.flowers.Flower.typeOfFlower;

public class Bouquet {
	private Flower[] bouquet;
	private int numberOfFlowers;
	
	public Bouquet(int numberOfFlowers) {
		this.numberOfFlowers = numberOfFlowers;
		bouquet = new Flower[numberOfFlowers];
	}
	
	public int getPrice() {
		int price = 0;
		for (int i = 0; i < numberOfFlowers; i++) {
			price += bouquet[i].getPrice();
		}
		
		return price;		
	}
	/** Gather random composition of flowers */
	public void gatherComposition () {
		Random rand = new Random();
		
		for (int i = 0; i < numberOfFlowers; i++) {
			int f = rand.nextInt(3);
			switch (f) {
			case 0:
				bouquet[i] = new Flower(typeOfFlower.ROSE);
				break;
			case 1:
				bouquet[i] = new Flower(typeOfFlower.TULIP);
				break;
			case 2:
				bouquet[i] = new Flower(typeOfFlower.LILIE);
				break;
			default:
				return;
			}
		}
	}
	
	public void printComposition() {
		System.out.println("Composition includes:");
		
		for (int i = 0; i < numberOfFlowers; i++) {
			switch (bouquet[i].getType()) {
			case ROSE:
				System.out.println("Rose: " + bouquet[i].getPrice());
				break;
			case TULIP:
				System.out.println("Tulip: " + bouquet[i].getPrice());
				break;
			case LILIE:
				System.out.println("Lilie: " + bouquet[i].getPrice());
				break;
			}
		}
	}
}
