package by.brsu.java.training;

import java.util.Date;

public class Balance {
    private static int nextId = 1;
    
    private static int getNextId(){
        return nextId++;
    }
    
    private int id;
	private long cost;
	private Date date;
	private int userId;

	
	/**
	 * @param id
	 * @param cost
	 * @param date
	 */
	public Balance(int cost, Date date, int userId) {
		super();
		this.id = getNextId();
		this.cost = cost;
		this.date = date;
		this.userId = userId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
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
		return "Balance [id=" + id + ", cost=" + cost + ", date=" + date
				+ ", userId=" + userId + "]";
	}
	
	
}
