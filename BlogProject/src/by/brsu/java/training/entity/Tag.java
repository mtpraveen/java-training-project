package by.brsu.java.training.entity;

public class Tag implements Comparable<Tag> {
	private long id;
	private String text;

	/**
	 * 
	 */
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param text
	 */
	public Tag(long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int compareTo(Tag o) {
		return this.text.compareToIgnoreCase(o.getText());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [id=" + id + ", text=" + text + "]";
	}
}
