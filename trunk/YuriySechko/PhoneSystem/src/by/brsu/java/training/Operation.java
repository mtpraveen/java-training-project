package by.brsu.java.training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.brsu.java.training.ParsingConstants.*;

/**
 * represents operation in phone system
 * @author yura
 *
 */
public class Operation {
	private static int nextId = 1;
	
	private static int getNextId() {
		return nextId++;
	}
	
	int id;
	ArrayList<Service> services;
	int userId;
	Date date;

	/**
	 * 
	 */
	public Operation() {
		super();
	}

	/**
	 * @param services
	 * @param userId
	 * @param date
	 */
	public Operation(ArrayList<Service> services, int userId, Date date) {
		super();
		this.id = getNextId();
		this.services = services;
		this.userId = userId;
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((services == null) ? 0 : services.hashCode());
		result = prime * result + userId;
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
		Operation other = (Operation) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
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
		return "Operation [id=" + id + ", services=" + services + ", userId="
				+ userId + ", date=" + date + "]";
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

	private boolean parseUserId(String str) {
		Pattern pattern = Pattern.compile(SPACES + "userId" + SPACES + "="
				+ SPACES + DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		userId = Integer.parseInt(matcher.group()
				.replaceAll("userId|[=]", "").trim());
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
	
	private boolean parseDate(String str) {
		Pattern pattern = Pattern.compile(SPACES + "date" + ".*"
				+ DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		try {
			String dateStr = matcher.group().replaceAll("date", "")
					.replaceAll("=", "").toString().trim();

			SimpleDateFormat format = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzzz yyyy", Locale.US);
			date = format.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean parseOperation(String str) {
		parseId(str);
		parseUserId(str);
		parseServices(str);
		parseDate(str);
		return true;
	}
	
	
}
