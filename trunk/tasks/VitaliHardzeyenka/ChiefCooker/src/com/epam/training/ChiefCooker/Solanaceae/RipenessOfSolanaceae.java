package com.epam.training.ChiefCooker.Solanaceae;

public enum RipenessOfSolanaceae {
	BAD, NORMAL, GOOD;
	
	/**
	 * Compare all values from the current enumeration with 
	 * string as enumeration value, return the result of the comparation.
	 * @param kindOfOil
	 * @return
	 */
	public static boolean contains(String ripenessOfSolanaceae) {
		boolean isContains = false;
		
		for (RipenessOfSolanaceae element : RipenessOfSolanaceae.values()) {
			if (element == RipenessOfSolanaceae.valueOf(ripenessOfSolanaceae)) {
				isContains = true;
			}
		}
		return isContains;
	}
}
