package Coffe_Bus;

import java.util.Collections;

/**
 * @author Dmitrij Zatsepin
 * 
 */

public class Sort {

	public static String key1;

	public static void SortNameCoffe() {
		Collections.sort(Coffe.listCoffe);
		int pos;
		pos = Collections.binarySearch(Coffe.listCoffe, key1);
		if (pos >= 0) {
			System.out.print("You choose: " + "\n" + Coffe.listCoffe.get(pos)
					+ ": (" + Coffe.listTypeCoffe.get(pos) + "), (Price: "
					+ Coffe.listPriceCoffy[pos] + "), (Volume: "
					+ Coffe.volumeCoffe[pos] + ")\n");
		} else {
			System.out.println("Error! No such element!");
		}
		CoffeBus.setSortingCoffeToVan(pos);
	}
}