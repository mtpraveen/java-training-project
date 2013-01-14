/**
 * 
 */
package com.travel.web.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.*;

import com.travel.pojo.User;

/**
 * @author dima
 * 
 */
public class TravelSecurity
{
	private static String md5(String s)
	{
		MessageDigest messageDigest;
		String hashtext = s;
		try
		{
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(s.getBytes(Charset.forName("UTF8")));
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
	public static String hashPassword(String source)
	{
		String tmp = "386" + md5(source) + "1E2C6188709B" + md5(source) + "D1160674";
		return md5(tmp);
	}
}
