package samples.xml.model;

public class device {
	private String name;
	private String origin;
	private String price;
	private String critical;
	private Type type=new Type();
	
	public String getCritical() {
		return critical;
	}
	public void setCritical(String faculty) {
		this.critical = critical;
	}
	
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
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	
	
	public class Type {
		private String group;
		private String ports;
		private String energy;
		
		public String getGroup() {
			return group;
		}
		public void setGroup(String group) {
			this.group = group;
		}
		public String getPorts() {
			return ports;
		}
		public void setPorts(String ports) {
			this.ports = ports;
		}
		public String getEnergy() {
			return energy;
		}
		public void setEnergy(String energy) {
			this.energy = energy;
		}
		
		
	}



	@Override
	public String toString() {
		return "device [critical="+critical+ ", name=" + name + ", origin=" + origin + ", price="
				+ price + ", type=" + type + "group= " +type.getGroup()+
				"energy= " + type.getEnergy()+ "energy= " + type.getPorts()+ "]";
	}
	

}
