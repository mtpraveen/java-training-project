package com.epam.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Gordeenko_XP
 * Class describe getting hash of password using 
 * Secure Hash Algorithm Version 2 (SHA-256).
 * Encrypt string password to hash for storing in data base.
 */
public class Encryption {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(Encryption.class.getName())); 

	/**
	 * Getting hash from string password for data base storing.
	 * @param value - password for encrypting (getting hash code)
	 * @return - hash code 
	 */
	public String getHashCode(String value) {
		MessageDigest messageDigest = null;
		byte[] byteData = null;
		StringBuffer hash = new StringBuffer();
		
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException exception) {
			logger.getExceptionTextFileLogger().error(exception);
		}
		messageDigest.update(value.getBytes());

		byteData = messageDigest.digest();

		hash = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			hash.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return hash.toString();
	}
	
}
