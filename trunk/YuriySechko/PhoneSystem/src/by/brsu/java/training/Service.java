package by.brsu.java.training;

import static by.brsu.java.training.ParsingConstants.DIGITS;
import static by.brsu.java.training.ParsingConstants.SPACES;
import static by.brsu.java.training.ParsingConstants.WORDS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class for modeling service in phone system
 * 
 * @author yura
 * 
 */
public class Service implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1880981944741792086L;
	private static int nextId = 1;
	
	private int id;
	private String name;
	private long cost;

	private static int getNextId() {
		return nextId++;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 */
	public Service() {
		super();
	}

	/**
	 * @param id
	 * @param cost
	 * @param name
	 */
	public Service(String name, long cost) {
		super();
		this.id = getNextId();
		this.cost = cost;
		this.name = name;
	}

	/**
	 * @return the cost
	 */
	public long getCost() {
		return cost;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Service [id=" + id + ", cost=" + cost + ", " + "name=" + name
				+ "]";
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

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(long cost) {
		this.cost = cost;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cost ^ (cost >>> 32));
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Service other = (Service) obj;
		if (cost != other.cost)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	private boolean parseName(String str) {
		Pattern pattern = Pattern.compile(SPACES + "name" + SPACES
				+ "=" + WORDS);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find())
			return false;
		String nameStr = matcher.group().replaceAll("name", "")
				.replaceAll("[=]", "").toString().trim();
		name = nameStr;
		return true;
	}

	public boolean parseService(String str) {
		return parseId(str) && parseCost(str) && parseName(str);
	}
}
