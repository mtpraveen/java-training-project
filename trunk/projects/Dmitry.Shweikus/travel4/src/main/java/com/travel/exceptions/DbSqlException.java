package com.travel.exceptions;

@SuppressWarnings("serial")
public class DbSqlException extends Exception {

	public DbSqlException() {
	}

	public DbSqlException(String arg0) {
		super(arg0);
	}

	public DbSqlException(Throwable arg0) {
		super(arg0);
	}

	public DbSqlException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
