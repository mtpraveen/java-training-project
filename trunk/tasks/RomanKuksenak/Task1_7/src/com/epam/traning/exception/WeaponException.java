package com.epam.traning.exception;

/**
 * 
 * @author Roman
 * 
 */
public class WeaponException extends Exception {
	public WeaponException(String text) {
		super(text);
	}

	/**
	 * 
	 * @param text
	 * @param innerEx
	 */
	public WeaponException(String text, Exception innerEx) {
		super(text, innerEx);

	}
}
