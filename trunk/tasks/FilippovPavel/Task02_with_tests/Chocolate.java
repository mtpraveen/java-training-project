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

	Chocolate(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount, double cFill, int cCocoa){ 
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
}

