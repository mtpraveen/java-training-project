package Coffe_Bus;

/**
 * @author Dmitrij Zatsepin
 * 
 */

public class CoffeBus {

	public static final int VAN_CAPASITY = 50000;
	public static final int MAX_SUMM_COFFE = 20000;

	public static void setSortingCoffeToVan(int pos) {
		int summVol = 0;
		int summCoffe = 0;
		int count;
		while (MAX_SUMM_COFFE >= summCoffe & VAN_CAPASITY >= summVol) {
			summVol += Coffe.volumeCoffe[pos];
			summCoffe += Coffe.listPriceCoffy[pos];
		}
		count = (VAN_CAPASITY / Coffe.volumeCoffe[pos]);
		System.out.println("Amount of loaded coffe: " + count
				+ "\nVolume of loaded coffe to Van (gr): "
				+ (summVol - Coffe.volumeCoffe[pos])
				+ "\nSumm of the loaded coffe(" + Coffe.listCoffe.get(pos)
				+ ") ($): " + (summCoffe - Coffe.listPriceCoffy[pos]));
	}
}