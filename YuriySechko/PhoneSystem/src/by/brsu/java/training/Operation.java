package by.brsu.java.training;

import java.util.ArrayList;
import java.util.Date;

public class Operation {
	ArrayList<Service> services;
	int userId;
	Date date;
	/**
	 * @param services
	 * @param userId
	 * @param date
	 */
	public Operation(ArrayList<Service> services, int userId, Date date) {
		super();
		this.services = services;
		this.userId = userId;
		this.date = date;
	}
	/**
	 * @return the services
	 */
	public ArrayList<Service> getServices() {
		return services;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
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
		return "Operation [services=" + services + ", userId=" + userId
				+ ", date=" + date + "]";
	}
}
