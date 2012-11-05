package Coffe_Bus;

import java.util.Collections;

/**
 * @author Dmitrij Zatsepin
 * 
 */

public class Search {

	public static String key2;
	public static String key3;
	
	public static void SearchCoffe() {
		int pos1;
		int pos2;
		pos1 = Collections.binarySearch(Coffe.listCoffe, key2);
		pos2 = Collections.binarySearch(Coffe.listTypeCoffe, key3);
		int temp1;
		int temp2;
		temp1 = (pos2 - pos1);
		temp2 = (pos1 - pos2);
		if (pos1 >= 0 & pos2 >= 0) {
			if (temp1 >= 0) {
				for (int i = 0; i <= temp1; i++) {
					System.out.println("Coffe: "
							+ Coffe.listCoffe.get(pos1 + i));
				}
			} else {
				for (int i = 0; i <= temp2; i++) {
					System.out.println("Coffe: "
							+ Coffe.listCoffe.get(pos1 - i));
				}
			}
		} else {
			System.out.println("Error! No such element!");
	}
	}
}