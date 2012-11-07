/**
 * 
 */
package com.epam.oop.fpv.samp02;

/**
 * @author Pavel
 *
 */

 /*
 * Class Chocolate - class describes a chocolates
 * extends the class FilledCandy
 * Added property: cocoaPerc - percentage of cocoa
 */
public class Chocolate extends FilledCandy{
	private int cocoaPerc;

	public Chocolate(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount, double cFill, int cCocoa){ 
		super(cName, cWght, cCalory, cSugar, cShLife, cCount, cFill);
		if (cCocoa<1 && cCocoa>99){
			System.out.println("Cocoa percent must be in 1 - 99 interval");
			cocoaPerc = 1;
		}
		else{
			cocoaPerc = cCocoa;
		}
	}

	public int getCocoaPerc() {
		return cocoaPerc;
	}
	public void setCocoaPerc(int cocoaPerc) {
		this.cocoaPerc = cocoaPerc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cocoaPerc;
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
		Chocolate other = (Chocolate) obj;
		if (cocoaPerc != other.cocoaPerc)
			return false;
		return true;
	}
	
}

