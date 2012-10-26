package com.epam.training.ChiefCooker.Factory;

import com.epam.training.ChiefCooker.Solanaceae.*;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;

public class SolanaceaeFactory {
	private enum  Vegetables { TOMATO, PEPPER }

    public static AbstractVegetable createSolanaceae(String vegetableName, double weight, double calorie, RipenessOfSolanaceae ripenessOfSolanaceae, boolean pulp) {
    	Vegetables vegetable = Vegetables.valueOf(vegetableName.toUpperCase());

        switch (vegetable) {
            case TOMATO:
            	return new Tomato(weight, calorie, ripenessOfSolanaceae, pulp);
            case PEPPER: 
            	return new Pepper(weight, calorie, ripenessOfSolanaceae, pulp);
            default:
            	throw new EnumConstantNotPresentException(Vegetables.class, vegetable.name());
        }
    }
}
