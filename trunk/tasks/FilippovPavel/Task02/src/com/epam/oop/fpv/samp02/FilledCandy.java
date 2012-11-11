/**
 * 
 */
package com.epam.oop.fpv.samp02;

/**
 * @author Pavel
 *
 */

/*
* Class FilledCandy - class describes a filled candies
* extends the class Candy
* Added property: fillVol - filling the candies
*/
public class FilledCandy extends Candy{
private double fillVol;
		
	public FilledCandy(String fName, double fWght, double fCalory, double fSugar, int fShLife, int fCount, double fFill){ 
		super(fName, fWght, fCalory, fSugar, fShLife, fCount);
		fillVol = fFill;
	}

	public double getFillVol() {
		return fillVol;
	}

	public void setFillVol(double fillVol) {
		this.fillVol = fillVol;
	}

	@Override
	public String toString() {
		return "FilledCandy " + getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(fillVol);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		FilledCandy other = (FilledCandy) obj;
		if (Double.doubleToLongBits(fillVol) != Double
				.doubleToLongBits(other.fillVol))
			return false;
		return true;
	}
	
}
		
