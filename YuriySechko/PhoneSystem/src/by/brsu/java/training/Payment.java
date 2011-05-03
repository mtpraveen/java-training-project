package by.brsu.java.training;

import static by.brsu.java.training.ParsingConstants.DIGITS;
import static by.brsu.java.training.ParsingConstants.SPACES;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class for modeling paper check
 * 
 * @author yura
 * 
 */
public class Payment implements java.io.Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 811876563002100380L;
	private static int nextId = 1;

	private static int getNextId() {
		return nextId++;
	}

	private int id;
	private long cost;
	private Date date;
	private int userId;

	/**
	 * 
	 */
	public Payment() {
		super();
	}

	/**
	 * @param id
	 * @param cost
	 * @param date
	 * @param userId
	 */
	public Payment(long cost, Date date, int userId) {
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
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cost ^ (cost >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
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
		Payment other = (Payment) obj;
		if (cost != other.cost)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
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
		return "Payment [id=" + id + ", cost=" + cost + ", date=" + date
				+ ", userId=" + userId + "]";
	}

	private boolean parseId(String str) {
		Pattern pattern = Pattern.compile(SPACES + "id" + SPACES + "="
				+ SPACES + DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		id = Integer.parseInt(matcher.group().replaceAll("id", "")
				.replaceAll("[=]", "").trim());
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
					.replaceAll("[=]", "").toString().trim();

			SimpleDateFormat format = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzzz yyyy", Locale.US);
			date = format.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(long cost) {
		this.cost = cost;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	
	private boolean parseCost(String str) {
		Pattern pattern = Pattern.compile(SPACES + "cost" + SPACES
				+ "=" + SPACES + DIGITS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		cost = Long.parseLong(matcher.group().replaceAll("cost", "")
				.replaceAll("[=]", "").trim().toString());
		return true;
	}

	public boolean parsePayment(String str) {
		return parseId(str) && parseCost(str) && parseDate(str) && parseUserId(str);
	}
}
