package com.epam.library.models;

import java.util.Date;

public class ReportItem {
	private String usersName;
	private Book book;
	private Date dateOfTaking;

	public ReportItem(String usersName, Book book, Date dateOfTaking) {
		this.usersName = usersName;
		this.book = book;
		this.dateOfTaking = dateOfTaking;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(getClass() == obj.getClass()))
			return false;
		else {
			ReportItem tmp = (ReportItem) obj;
			if (tmp.usersName.equals(this.usersName)
					&& tmp.book.equals(this.book)
					&& tmp.dateOfTaking.equals(this.dateOfTaking))
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
				+ ((book == null) ? 0 : book.hashCode())
				+ dateOfTaking.hashCode();
		return result;
	}
	
	public String toString(){
		return (usersName+"," + book.toString() + "," + dateOfTaking + ",");
	}

}
