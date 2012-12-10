package epam.course.domain;

public class Customer extends Person {

	private boolean entered;

	public boolean isEntered() {
		return entered;
	}

	public void setEntered(boolean entered) {
		this.entered = entered;
	}

	private int numberOrders;
	private boolean isAdmin;

	public Customer(String name, String surname, String paspNumber, String login,
			String password, int numberOrders, boolean isAdmin) {
		super(name, surname, paspNumber, login, password);
		this.numberOrders = numberOrders;
		isAdmin = false;
	}

	public Customer(String name, String surname, String paspNumber, String login,
			String password) {
		super(name, surname, paspNumber, login, password);
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getNumberOrders() {
		return numberOrders;
	}

	public void setNumberOrders(int numberOrders) {
		this.numberOrders = numberOrders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numberOrders;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (numberOrders != other.numberOrders)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer "+super.toString()+" [entered=" + entered + ", numberOrders="
				+ numberOrders + ", isAdmin=" + isAdmin + "]";
	}
	
	

}
