package Coffe_Bus;

import java.util.Collections;

/**
 * @author Dmitrij Zatsepin
 *
 */

public class Sort {

	public static final int constValue = 500;
	
	public static void SortNameCoffe() {
		Collections.sort(Coffe.listCoffe);
		for (String val : Coffe.listCoffe) {
			System.out.println(val);
		}	
	}

	public static void SortTypeCoffe() {
		Collections.sort(Coffe.listTypeCoffe);
		for (String val : Coffe.listTypeCoffe) {
			System.out.println(val);
		}
	}

	public static void SortVolumeCoffee() {
		Collections.sort(Coffe.volumeCoffe);
		for (int val : Coffe.volumeCoffe) {
			System.out.println(val);
		}
	}
}
