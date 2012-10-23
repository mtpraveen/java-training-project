package Coffe_Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class Coffe {

	public static List<String> listCoffe;
	public static List<String> listTypeCoffe;
	public static List<Integer> volumeCoffe;
	public static int listPriceCoffy[];

	public static void NameCoffe() {
		listCoffe = new ArrayList<String>();
		listCoffe.add("Nescafe");
		listCoffe.add("Lavazza");
		listCoffe.add("Zhokej");
		listCoffe.add("Tchibo");
		String tempName[] = new String[listCoffe.size()];
		tempName = listCoffe.toArray(tempName);
	}

	public static void printCoffe() {
		for (Object object : listCoffe) {
			System.out.println("Coffe: " + object);
		}
	}

	public static void typeCoffe() {
		listTypeCoffe = new ArrayList<String>();
		listTypeCoffe.add("Coffee Beans");
		listTypeCoffe.add("Ground Coffee");
		listTypeCoffe.add("Instant Coffee in banks");
		listTypeCoffe.add("Instant Coffee in package");
		/*
		 * for (Object object : listSortCoffe) {
		 * System.out.println("Type of coffe: " + object); }
		 */
	}

	public static void PriceCoffe() {
		listPriceCoffy = new int [listCoffe.size()];
		listPriceCoffy[0] = 10;
		listPriceCoffy[1] = 20;
		listPriceCoffy[2] = 30;
		listPriceCoffy[3] = 50;
		System.out.print("Price of coffy: ");
		for (int i: listPriceCoffy) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
	
	public static void volumeCoffee() {
		volumeCoffe = new ArrayList<Integer>();
		volumeCoffe.add(100);
		volumeCoffe.add(200);
		volumeCoffe.add(300);
		volumeCoffe.add(500);
		int sum = 0;
		Integer tempVol[] = new Integer[volumeCoffe.size()];
		tempVol = volumeCoffe.toArray(tempVol);
		for (int i : tempVol) {
			sum += i;
		}
		System.out.println("The total amount of coffee: " + sum);
	}
	
}
