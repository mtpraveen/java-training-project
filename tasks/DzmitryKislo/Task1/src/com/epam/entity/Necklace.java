package com.epam.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.comparators.PriceComp;
import com.epam.comparators.WeightComp;
import com.epam.factory.FactoryStones;

public class Necklace {

	private int stoneType;
	private int stoneCount;
	private List<Stone> necklace;

	/**
	 * Constructor
	 * 
	 * @param stoneType
	 * @param stoneCount
	 */
	public Necklace(int stoneType, int stoneCount) {
		this.stoneType = stoneType;
		this.stoneCount = stoneCount;
		necklace = new ArrayList<Stone>();
		create();
	}

	
	/**
	 * Constructor
	 */
	public Necklace() {
		this.stoneType = 0;
		this.stoneCount = 0;
		necklace = new ArrayList<Stone>();
	}

	private void create() {
		double r = 0.0;
		for (int i = 0; i < stoneCount; i++) {
			r = Math.random();
			necklace.add(FactoryStones.getStoneFromFactory(i * r + 10, i * r
					* 10 + 100, stoneType));
		}
	}

	public void addStone(Stone stone) {
		necklace.add(stone);
		stoneCount = necklace.size();
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

	public void sortByWeight() {
		Collections.sort(necklace, new WeightComp());
	}

	public void sortByPrice() {
		Collections.sort(necklace, new PriceComp());
	}

	public List<Stone> searchByPrice(double minPrice, double maxPrice) {
		List<Stone> list = new ArrayList<Stone>();
		for (Stone s : necklace) {
			if (s.getPrice() >= minPrice && s.getPrice() <= maxPrice)
				list.add(s);
		}
		return list;
	}

	public List<Stone> searcByWeight(double minWeight, double maxWeight) {
		List<Stone> list = new ArrayList<Stone>();
		for (Stone s : necklace) {
			if (s.getWeight() >= minWeight && s.getWeight() <= maxWeight)
				list.add(s);
		}
		return list;
	}

	public List<Stone> getStones() {
		return necklace;
	}

}
