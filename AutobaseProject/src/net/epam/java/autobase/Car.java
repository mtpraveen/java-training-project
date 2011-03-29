package net.epam.java.autobase;
public class Car {
	private int id;
    private String number;
	private String state;
    private String brand;
    
    /**
	 * @param number
	 * @param brand
	 */
	public Car(int id, String number, String brand) {
		this.number = number;
		this.brand = brand;
		this.id = id;
	}

    public void setState(String state){
    	this.state = state;
    }

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "car " + id + "(" + brand + ", ¹ " + number + ")";
	}    

}
