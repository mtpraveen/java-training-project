/**
 * 
 */
package Coffe_Bus;

import java.io.*;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws IOException {
		
		CoffeBus coffebus = new CoffeBus();
		CoffeBus.VanCapacity = 500;
		CoffeBus.SummCoffe = 2000;
		System.out.println("Van coffe." + "\n" + "Parameters of van coffe: ");
		System.out.println(" - Van Capacity: " + CoffeBus.VanCapacity);
		System.out.println("-----------------------");
		System.out.println("Parameters of Coffe: ");
		Coffe.NameCoffe();
		Coffe.typeCoffe();
		Coffe.PriceCoffe();
		//Coffe.printCoffe();
		Coffe.volumeCoffee();
		System.out.println("-----------------------");
		System.out.print("Please, select the sort: " + "\n" + "\t1 - Sort Name of coffe" + "\n" + "\t2 - Sort Type of coffe" + "\n" + "\t3 - Sort Volume of coffe" + "\n");
		System.out.print("You <select>: ");
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
		String l1 = reader.readLine();
		int n = Integer.parseInt(l1);
		char choise;
		choise = (char) n;
		switch (choise) {
		case 1: {
					System.out.println("-----------------------");
					System.out.println("Sort Name of coffe: ");
					Sort.SortNameCoffe();
					break;
		}
		case 2: {
			System.out.println("-----------------------");
			System.out.println("Sort Type of coffe: ");
			Sort.SortTypeCoffe();
			break;
		}
		case 3: {
			System.out.println("-----------------------");
			System.out.println("Sort Volume of coffe: ");
			Sort.SortVolumeCoffee();
			break;
		}
		default: {
			System.out.println("Error! You input incorrect value!");
		}
		}
		System.out.println("-----------------------");
		System.out.print("Please input 1-st key to search: ");
		LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(System.in));
		String l2 = reader2.readLine();
		Search.key1 = l2;
		System.out.print("Please input 2-nd key to search: ");
		LineNumberReader reader3 = new LineNumberReader(new InputStreamReader(System.in));
		String l3 = reader3.readLine();
		Search.key2 = l3;
		Search.SearchCoffe();
	}
}
