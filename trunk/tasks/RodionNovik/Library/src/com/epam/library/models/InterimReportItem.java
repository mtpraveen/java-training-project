package com.epam.library.models;

public class InterimReportItem {
	private String usersName;
	private Book book;

	public InterimReportItem(String usersName, Book book) {
		this.usersName = usersName;
		this.book = book;
	}

	public String toString() {
		return (usersName + "," + book.toString());
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(getClass() == obj.getClass()))
			return false;
		else {
			InterimReportItem tmp = (InterimReportItem) obj;
			if (tmp.usersName.equals(this.usersName) && tmp.book.equals(this.book))
				return true;
			else
				return false;
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usersName == null) ? 0 : usersName.hashCode())
				+ ((book == null) ? 0 : book.hashCode());
		return result;
	}
}
