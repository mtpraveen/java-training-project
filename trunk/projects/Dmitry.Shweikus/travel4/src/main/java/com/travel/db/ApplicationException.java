package com.travel.db;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	public ApplicationException() {
	}

	public ApplicationException(String arg0) {
		super(arg0);
	}

	public ApplicationException(Throwable arg0) {
		super(arg0);
	}

	public ApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
