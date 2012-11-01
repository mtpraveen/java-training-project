/**
 * 
 */
package com.epam.oop.fpv.samp02;

/**
 * @author Pavel
 *
 */

/*
 * Class Cookie - class describes a cookies
 * extends the class Candy
 * Added property: countInPack  - the count of cookies in a pack
 */
public class Cookie extends Candy{
	private int countInPack;

	Cookie(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount, int cCountPack){ 
		super(cName, cWght, cCalory, cSugar, cShLife, cCount);
		countInPack = cCountPack;
	}

	public int getCountInPack() {
		return countInPack;
	}
}