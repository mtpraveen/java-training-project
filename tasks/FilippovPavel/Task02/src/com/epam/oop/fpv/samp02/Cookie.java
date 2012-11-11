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

	public Cookie(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount, int cCountPack){ 
		super(cName, cWght, cCalory, cSugar, cShLife, cCount);
		countInPack = cCountPack;
	}

	public int getCountInPack() {
		return countInPack;
	}
	
	@Override
	public String toString() {
		return "Cookie " + getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + countInPack;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cookie other = (Cookie) obj;
		if (countInPack != other.countInPack)
			return false;
		return true;
	}

	public void setCountInPack(int countInPack) {
		this.countInPack = countInPack;
	}

}