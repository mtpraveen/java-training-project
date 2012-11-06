package com.epam.flowers;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
	private List<Flower> flowers;

	public Bouquet() {
		flowers = new ArrayList<Flower>();
	}

	public int getPrice() {
		int price = 0;

		for (int i = 0; i < flowers.size(); i++) {
			price += flowers.get(i).getPrice();
		}

		return price;
	}

	public void addFlower(Flower flower) {
		flowers.add(flower);
	}

	public int getNumberOfFlowers() {
		return flowers.size();
	}

	public String toString() {
		StringBuilder string = new StringBuilder("Bouquet consists of:\n");

		for (int i = 0; i < flowers.size(); i++) {
			string = string.append(flowers.get(i).getName()).append(" - ")
					.append(Integer.toString(flowers.get(i).getPrice()))
					.append("\n");
		}

		return string.toString();
	}
}
