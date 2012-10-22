package com.epam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Necklace {

	public int stoneType;
	public int stoneCount;
	public List<Stone> necklace;

	public Necklace(int stoneType, int stoneCount) {
		this.stoneType = stoneType;
		this.stoneCount = stoneCount;
		necklace = new ArrayList<Stone>();
	}

	public void create() {
		double r = 0.0;
		for (int i = 0; i < stoneCount; i++) {
			r = Math.random();
			necklace.add(FactoryStones.getStoneFromFactory(i * r + 10, i * r
					* 10 + 100, stoneType));
		}
	}

	public void printNecklace() {
		for (Stone s : necklace)
			System.out.println(s);
	}

	public double totalWeight() {
		double totalW = 0.0;
		for (Stone s : necklace) {
			totalW += s.getWeight();
		}
		return totalW;
	}

	public double totalPrice() {
		double totalP = 0.0;
		for (Stone s : necklace) {
			totalP += s.getPrice();
		}
		return totalP;
	}

	public void sort() {
		Collections.sort(necklace);
	}

	public void search(double minPrice, double maxPrice) {
		for (Stone s : necklace) {
			if (s.getPrice() >= minPrice && s.getPrice() <= maxPrice)
				System.out.println(s);
		}
	}

}
