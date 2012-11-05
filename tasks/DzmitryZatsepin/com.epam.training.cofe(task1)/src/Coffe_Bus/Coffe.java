package Coffe_Bus;

import java.util.*;

/**
 * @author Dmitrij Zatsepin
 * 
 */

public class Coffe {

	public static List<String> listCoffe;
	public static List<String> listTypeCoffe;
	public static int volumeCoffe[];
	public static int listPriceCoffy[];

	public String nameCoffe;

	public static void NameCoffe() {
		listCoffe = new ArrayList<String>();
		listCoffe.add("Gigacofe");
		listCoffe.add("Lavazza");
		listCoffe.add("Tchibo");
		listCoffe.add("Zhokej");
	}

	public static void printCoffe() {
		System.out.print("Coffe: ");
		for (Object object : listCoffe) {
			System.out.print(object + ", ");
		}
	}

	public static void typeCoffe() {
		listTypeCoffe = new ArrayList<String>();
		listTypeCoffe.add("Coffee Beans");
		listTypeCoffe.add("Ground Coffee");
		listTypeCoffe.add("Instant Coffee in banks");
		listTypeCoffe.add("Instant Coffee in package");
		System.out.print("Type of coffe: ");
		for (Object object : listTypeCoffe) {
			System.out.print(object + ", ");
		}

	}

	public static void PriceCoffe() {
		listPriceCoffy = new int[listCoffe.size()];
		listPriceCoffy[0] = 10;
		listPriceCoffy[1] = 20;
		listPriceCoffy[2] = 30;
		listPriceCoffy[3] = 40;
		System.out.print("Price of coffy ($): ");
		for (int i : listPriceCoffy) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	public static void volumeCoffee() {
		volumeCoffe = new int[listCoffe.size()];
		volumeCoffe[0] = 100;
		volumeCoffe[1] = 200;
		volumeCoffe[2] = 300;
		volumeCoffe[3] = 400;
		System.out.print("Volume of coffy (gramm): ");
		for (int i : volumeCoffe) {
			System.out.print(i + ", ");
		}
		System.out.println();
		int sum = 0;
		for (int i : volumeCoffe) {
			sum += i;
		}
		System.out.println("\nThe total amount of coffee: " + sum);
	}
}