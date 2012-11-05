/**
 * 
 */
package Coffe_Bus;

import java.io.*;

/**
 * @author Dmitrij Zatsepin
 * 
 */

public class Application {

	/**
	 * @param args
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		CoffeBus coffebus = new CoffeBus();
		System.out.println("Van coffe." + "\n" + "Parameters of van coffe: ");
		System.out.println(" - Van Capacity (gr): " + CoffeBus.VAN_CAPASITY);
		System.out
				.println(" - Maximum Summ of Coffe that we can set to Van of Coffe ($): "
						+ CoffeBus.MAX_SUMM_COFFE);
		System.out.println("-----------------------");
		System.out.println("Parameters of Coffe: ");
		Coffe.NameCoffe();
		Coffe.printCoffe();
		System.out.println();
		Coffe.typeCoffe();
		System.out.println();
		Coffe.PriceCoffe();
		Coffe.volumeCoffee();
		System.out.println("-----------------------");
		
		System.out.println("-----------------------");
		
		System.out.println("Choise: ");
		System.out.println("\t1 - Sort;\n\t2 - Seach");
		System.out.print("You <select>: ");
		LineNumberReader readerChoise = new LineNumberReader(
				new InputStreamReader(System.in));
		String line = readerChoise.readLine();
		int temporary1 = Integer.parseInt(line);
		char choise1;
		choise1 = (char) temporary1;
		switch (choise1) {
		case 1: {
			System.out.println("-----------------------");
			System.out.print("Please, select the sort: " + "\n"
					+ "\t1 - Sort Name of coffe" + "\n");
			System.out.print("You <select>: ");
			LineNumberReader reader = new LineNumberReader(
					new InputStreamReader(System.in));
			String l1 = reader.readLine();
			int n = Integer.parseInt(l1);
			char choise2;
			choise2 = (char) n;
			switch (choise2) {
			case 1: {
				System.out.println("-----------------------");
				System.out
						.print("Please enter a name of coffee that you would load to the Van of Coffe: ");
				LineNumberReader reader2 = new LineNumberReader(
						new InputStreamReader(System.in));
				String l2 = reader2.readLine();
				Sort.key1 = l2;
				Sort.SortNameCoffe();
				break;
			}
			default: {
				System.out.println("Error! You input incorrect value!");
			}
			}
			break;
		}
		case 2: {
			System.out.println("-----------------------");
			System.out.print("Please input 1-st key to search: ");
			LineNumberReader reader2 = new LineNumberReader(
			new InputStreamReader(System.in));
			String l2 = reader2.readLine();
			Search.key2 = l2;
			
			System.out.print("Please input 2-nd key to search: ");
			LineNumberReader reader3 = new LineNumberReader(new
			InputStreamReader(System.in)); String l3 = reader3.readLine();
			Search.key3 = l3;
			Search.SearchCoffe();
			break;
		}
		default: {
			System.out.println("Error! You input incorrect value!");
		}
		}
	}
}