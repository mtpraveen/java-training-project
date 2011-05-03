package by.brsu.java.training;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.brsu.java.training.ParsingConstants.*;
/**
 * Class for modeling user in phone system
 * 
 * @author yura
 * 
 */
public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5290720090221062953L;
	private static int nextId = 1;

	private int id;
	private UserStatus status;
	private long phoneNumber;
	private long balance;
	private ArrayList<Service> services;

	/**
	 * Creates user with specified properties
	 * 
	 * @param phoneNumber
	 *            Phone number for new user
	 * @param status
	 *            User's status
	 * @param balance
	 *            Starting balance
	 * @param totalMinutes
	 *            Starting total spent minutes
	 * @param avaibleServices
	 *            Services, witch will be available for user
	 */
	public User(long phoneNumber, UserStatus status, long balance,
			ArrayList<Service> avaibleServices) {
		super();
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.services = avaibleServices;
		this.id = User.getNextId();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (balance ^ (balance >>> 32));
		result = prime * result + id;
		result = prime * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
		result = prime * result
				+ ((services == null) ? 0 : services.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		User other = (User) obj;
		if (balance != other.balance)
			return false;
		if (id != other.id)
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * Returns user's phone number
	 */
	public long getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets new user's phone number
	 * 
	 * @param newPhoneNumber
	 *            New phone number
	 */
	public void setPhoneNumber(long newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}

	/**
	 * Returns user's id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Returns user's status
	 */
	public UserStatus getStatus() {
		return this.status;
	}

	/**
	 * Returns user's balance
	 */
	public long getBalance() {
		return this.balance;
	}

	/**
	 * Changes user's status for specified
	 * 
	 * @param newStatus
	 *            New user's status
	 */
	public void setStatus(UserStatus newStatus) {
		this.status = newStatus;
	}

	/**
	 * Sets user's balance
	 */
	public void setBalance(long newBalance) {
		this.balance = newBalance;
	}

	/**
	 * Adds specified services for user
	 */
	public void addServices(ArrayList<Service> services) {
		this.services = services;
	}

	/**
	 * Deletes all user's services
	 */
	public void deleteServices() {
		this.services = null;
	}

	/**
	 * Returns services witch available for user
	 */
	public ArrayList<Service> getServices() {
		return this.services;
	}

	/**
	 * @param services
	 *            the services to set
	 */
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	/**
	 * Returns next available id for new user
	 */
	private static int getNextId() {
		return nextId++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", status=" + status + ", phoneNumber="
				+ phoneNumber + ", balance=" + balance + ", services="
				+ services + "]";
	}

	/**
	 */
	public void setId(int id) {
		this.id = id;
	}

	private boolean parseId(String str) {
		Pattern pattern = Pattern.compile(SPACES + "id" + SPACES + "="
				+ SPACES + DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		id = Integer.parseInt(matcher.group().replaceAll("id|[=]", "")
				.trim());
		return true;
	}

	private boolean parseStatus(String str) {
		Pattern pattern = Pattern.compile(SPACES + "status" + SPACES + "="
				+ WORDS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		String statusStr = matcher.group().replaceAll("status", "")
				.replaceAll("[=]", "").trim().toString();
		if (statusStr.toUpperCase().equals("NORMAL"))
			status = UserStatus.NORMAL;
		if (statusStr.toUpperCase().equals("BANNED"))
			status = UserStatus.BANNED;
		return true;
	}

	private boolean parsePhoneNumber(String str) {
		Pattern pattern = Pattern.compile(SPACES + "phoneNumber" + SPACES + "="
				+ SPACES + DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		phoneNumber = Long.parseLong(matcher.group()
				.replaceAll("phoneNumber", "").replaceAll("[=]", "").toString()
				.trim());
		return true;
	}

	private boolean parseBalance(String str) {
		Pattern pattern = Pattern.compile(SPACES + "balance" + SPACES + "="
				+ SPACES + DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		balance = Long.parseLong(matcher.group().replaceAll("balance", "")
				.replaceAll("[=]", "").toString().trim());
		return true;
	}

	private boolean parseServices(String str) {
		Pattern pattern = Pattern.compile(SPACES + "services" + SPACES + "="
				+ ".*");
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		StringBuffer servicesStr = new StringBuffer(matcher.group()
				.replaceAll("services=", "").toString().trim());
		pattern = Pattern.compile(SPACES + "id" + SPACES + "=" + DIGITS);
		matcher = pattern.matcher(servicesStr);
		if (services != null)
			services.clear();
		else
			services = new ArrayList<Service>();
		ArrayList<Service> availableServices = PhoneSystem.getInstance()
				.getAvailableServices();
		while (matcher.find()) {
			int serviceId = Integer.parseInt(matcher.group().replaceAll(
					"id|[=]", ""));
			for (Service service : availableServices)
				if (service.getId() == serviceId)
					services.add(service);
		}
		return true;
	}

	public boolean parseUser(String str) {
		return parseId(str) && parseStatus(str) && parsePhoneNumber(str)
				&& parseBalance(str) && parseServices(str);
	}
}
