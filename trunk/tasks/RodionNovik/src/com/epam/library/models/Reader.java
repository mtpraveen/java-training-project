package com.epam.library.models;

import java.util.Date;

public class Reader {
	private String fio;
	private Date dateOfBirth;
	private String address;

	public Reader(String fio, Date date, String address) {
		this.fio = fio;
		this.dateOfBirth = date;
		this.address = address;
	}

	public String toString() {
		return ("FIO:" + fio + "\nDateOfBirth:" + dateOfBirth + "\naddress:" + address);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fio == null) ? 0 : fio.hashCode())+((address == null) ? 0 : address.hashCode())+dateOfBirth.toString().hashCode();
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(getClass() == obj.getClass()))
			return false;
		else {
			Reader tmp = (Reader) obj;
			if (tmp.fio.equals(this.fio) && tmp.address.equals(this.address)
					&& tmp.dateOfBirth.equals(this.dateOfBirth))
				return true;
			else
				return false;
		}
	}
}
