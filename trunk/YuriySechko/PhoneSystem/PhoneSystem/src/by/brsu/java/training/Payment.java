package by.brsu.java.training;

import java.util.Date;

/**
 * Class for modeling paper check
 * @author yura
 *
 */
public class Payment {
    private static int nextId = 1;
    
	private static int getNextId(){
        return nextId++;
    }
	
    private int id;
    private long cost;
	private Date date;

	/**
	 * @param id
	 * @param cost
	 * @param date
	 * @param userId
	 */
	public Payment(long cost, Date date) {
		super();
		this.id = getNextId();
		this.cost = cost;
		this.date = date;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the cost
	 */
	public long getCost() {
		return cost;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Payment [id=" + id + ", cost=" + cost + ", date=" + date + "]";
	}
	
	
}
