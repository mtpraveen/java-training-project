/**
 * 
 */
package com.travel.web.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;
import java.security.NoSuchAlgorithmException;

/**
 * @author dima
 * 
 */
public class TravelSecurity
{
	public static String hashPassword(String password)
	{
		MessageDigest messageDigest;
		String hashtext = password;
		try
		{
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(password.getBytes(Charset.forName("UTF8")));
			final byte[] resultByte = messageDigest.digest();
			BigInteger bigInt = new BigInteger(1, resultByte);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32)
			{
				hashtext = "0" + hashtext;
			}
		} catch (NoSuchAlgorithmException e)
		{
			//
		}
		//System.out.println("password " + password + " -> [" + hashtext + "]");
		return hashtext;
	}
}
