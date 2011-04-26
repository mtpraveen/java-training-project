package xml.model;

public class Flower {
	private String name;
	private String soil;
	private String origin;
	//visual parameters
	private String stemColour;
	private String leafsColour;
	private double aveSize;
	//growing tips
	private int temperature;
	private String lighting;
	private String watering;
	//multiplying
	private String multiplying;
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
	 * @return the soil
	 */
	public String getSoil() {
		return soil;
	}
	/**
	 * @param soil the soil to set
	 */
	public void setSoil(String soil) {
		this.soil = soil;
	}
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the stemColour
	 */
	public String getStemColour() {
		return stemColour;
	}
	/**
	 * @param colour the stemColour to set
	 */
	public void setStemColour(String colour) {
		this.stemColour = colour;
	}
	/**
	 * @return the leafsColour
	 */
	public String getLeafsColour() {
		return leafsColour;
	}
	/**
	 * @param leafsColour the leafesColour to set
	 */
	public void setLeafsColour(String leafsColour) {
		this.leafsColour = leafsColour;
	}
	/**
	 * @return the aveSize
	 */
	public double getAveSize() {
		return aveSize;
	}
	/**
	 * @param aveSize the aveSize to set
	 */
	public void setAveSize(double aveSize) {
		this.aveSize = aveSize;
	}
	/**
	 * @return the temperature
	 */
	public int getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the lighting
	 */
	public String getLighting() {
		return lighting;
	}
	/**
	 * @param lighting the lighting to set
	 */
	public void setLighting(String lighting) {
		this.lighting = lighting;
	}
	/**
	 * @return the watering
	 */
	public String getWatering() {
		return watering;
	}
	/**
	 * @param watering the watering to set
	 */
	public void setWatering(String watering) {
		this.watering = watering;
	}
	/**
	 * @return the multiplying
	 */
	public String getMultiplying() {
		return multiplying;
	}
	/**
	 * @param multiplying the multiplying to set
	 */
	public void setMultiplying(String multiplying) {
		this.multiplying = multiplying;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Flower name=" + name + ", soil=" + soil + ", origin=" + origin + 
				"\n   visual parameters: " + 
				"stem colour=" + stemColour + ", leafs colour=" + leafsColour + ", average size=" + aveSize + 
				"\n   growing tips: " + 
				"temperature=" + temperature + ", lighting=" + lighting + ", watering=" + watering
				+ "\n   multiplying=" + multiplying + "\n";
	}

}
