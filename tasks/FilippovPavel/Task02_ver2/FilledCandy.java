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
		
	FilledCandy(String fName, double fWght, double fCalory, double fSugar, int fShLife, int fCount, double fFill){ 
		super(fName, fWght, fCalory, fSugar, fShLife, fCount);
		fillVol = fFill;
	}

	public double getFillVol() {
		return fillVol;
	}
	
}
		
