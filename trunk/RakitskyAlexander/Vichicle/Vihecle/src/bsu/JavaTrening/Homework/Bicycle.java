/**
 * 
 */
package bsu.JavaTrening.Homework;

/**
 * @author alex
 *
 */
public class Bicycle extends Vihecle{
	String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public Bicycle() {
		super();
		setMaxspeed(40);
		setSpeedDispersal(1);
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return"The motorcycle " + name+ " whith speed "+ getSpeed();
	}
	public Bicycle(String name){
		this();
		setName(name);
	}
}
