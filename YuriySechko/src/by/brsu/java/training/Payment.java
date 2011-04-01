package by.brsu.java.training;

/**
 * Class for modeling paper check
 * @author yura
 *
 */
public class Payment {
	
	/**
	 * Returns total cost in dollars
	 * @return Total cost
	 */
    public int getCost() {
		return cost;
	}

    /**
     * Sets new cost in dollars
     * @param New cost
     */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Creates object with specified total cost
	 * @param cost Total cost
	 */
	public Payment(int cost) {
		super();
		this.cost = cost;
	}

	private int cost;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Payment [cost=" + cost + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (cost != other.cost)
			return false;
		return true;
	}

	
}
