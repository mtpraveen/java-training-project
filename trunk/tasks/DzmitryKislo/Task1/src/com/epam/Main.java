package com.epam;

import java.util.List;
import java.util.Scanner;

import com.epam.entity.Necklace;
import com.epam.entity.Stone;

public class Main {

	public static final int STONE_TYPE = 2;
	public static final int STONE_COUNT = 50;

	public static void main(String[] args) {

		// selection of stones
		Necklace n = new Necklace(STONE_TYPE, STONE_COUNT);

		// print necklace
		n.printNecklace();

		// Total weight and total price
		System.out.println("Total weight = " + n.totalWeight() + " (carat)");
		System.out.println("Total price = " + n.totalPrice() + " $");

		// Sorting by weight
		n.sortByWeight();

		// print necklace
		System.out.println();
		n.printNecklace();

		// search
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter minPrice: ");
		double minPrice = scan.nextDouble();
		System.out.println("Enter maxPrice: ");
		double maxPrice = scan.nextDouble();
		List<Stone> list = n.searchByPrice(minPrice, maxPrice);
		for(Stone st: list)
			System.out.println(st.getName());

	}

}
