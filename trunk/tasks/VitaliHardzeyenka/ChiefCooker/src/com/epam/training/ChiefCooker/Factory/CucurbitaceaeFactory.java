package com.epam.training.ChiefCooker.Factory;

import com.epam.training.ChiefCooker.Cucurbitaceae.Squash;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;

public class CucurbitaceaeFactory {
	private enum Vegetables { SQUASH }
	
	public static AbstractVegetable createCucurbitaceae(String vegetableName, double weight, double calorie) {
		Vegetables vegetable = Vegetables.valueOf(vegetableName.toUpperCase());
		
		switch (vegetable) {
			case SQUASH: 
				return new Squash(weight, calorie);
			default: 
				throw new EnumConstantNotPresentException(Vegetables.class, vegetable.name());
		}			
	}
}
