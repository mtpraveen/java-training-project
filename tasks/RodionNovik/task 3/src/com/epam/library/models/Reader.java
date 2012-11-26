package com.epam.library.models;

import java.util.Date;
import java.util.HashMap;

public class Reader {
	private String login;
	private String password;
	private String fio;
	private Date dateOfBirth;
	private HashMap<Book, Integer> booksOnSubscription;

	public Reader(String fio, Date date, String login, String password) {
		this.fio = fio;
		this.dateOfBirth = date;
		this.login = login;
		this.password = password;
	}
}
