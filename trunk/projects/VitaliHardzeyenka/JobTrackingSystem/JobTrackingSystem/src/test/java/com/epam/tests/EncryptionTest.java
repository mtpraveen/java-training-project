package com.epam.tests;

import static org.junit.Assert.*;

import org.junit.Test;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;

import com.epam.logic.Encryption;

public class EncryptionTest {

	@Test
	public void test() {
		Encryption encryption = new Encryption();
		
		String firstPassword = "alk;sjdfklas12341234";
		String secondPassword = "alk;sjdfklas12341234";
		String thirdPassword = "asdA1BF34! #$%#% 3344   !!!";
		
		String firstHash = encryption.getHashCode(firstPassword);
		String secondHash = encryption.getHashCode(secondPassword);
		String thirdHash = encryption.getHashCode(thirdPassword);
		
		assertTrue(firstPassword.equals(secondPassword));
		assertFalse(firstPassword.equals(thirdPassword));
		
		assertTrue(firstHash.equals(secondHash));
		assertFalse(firstHash.equals(thirdHash));
	}

}
