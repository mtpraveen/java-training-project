/**
 * 
 */
package bsu.JavaTrening.Homework;

/**
 * @author alex
 *
 */
public class mainclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Car bibi=new Car("Lada",false);
		//System.out.print(bibi.getSpeed());
		bibi.setSpeed(120);
		System.out.println(bibi.toString());
		//System.out.println(bibi.getDistanse());
		//System.out.println(bibi.getSpeed());
		bibi.staticGo(180);
		//bibi.setSpeed(0);
		bibi.stop();
		//System.out.println(bibi.getDistanse());
		//System.out.println(bibi.getSpeed());
	}

}
