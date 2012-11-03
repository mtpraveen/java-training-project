package com.epam.factory;

import java.util.Random;

import com.epam.entity.PreciousStone;
import com.epam.entity.SemiPreciousStone;
import com.epam.entity.Stone;

public class FactoryStones {

	public static Stone getStoneFromFactory(double weight, double price, int num) {
		switch (new Random().nextInt(num)) {
		case 0:
			return new PreciousStone("preciousStone", weight, price);
		case 1:
			return new SemiPreciousStone("semiPreciousStone", weight, price);
		default:
			throw new IllegalArgumentException();
		}
	}
}
