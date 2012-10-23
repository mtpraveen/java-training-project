package Coffe_Bus;

import java.util.Collections; 

/**
 * @author Dmitrij Zatsepin
 *
 */

public class Search {
	
	public static String key1;
	public static String key2;
	
	public static void SearchCoffe() {
		int pos1;
		int pos2;
		pos1 = Collections.binarySearch(Coffe.listCoffe, key1);
		pos2 = Collections.binarySearch(Coffe.listCoffe, key2);
		if (pos1 >= 0 & pos2 >= 0) {
	          System.out.print("Result of Search: " + "\n" + Coffe.listCoffe.get(pos1) + " " + Coffe.listTypeCoffe.get(pos1) + " (" + Coffe.listTypeCoffe.get(pos1) + ")" + "\n");
	       } else 
	       { 
	          System.out.println("Error! No such element!"); 
	       }
	}
}
