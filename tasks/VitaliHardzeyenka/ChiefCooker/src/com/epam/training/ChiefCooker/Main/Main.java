package com.epam.training.ChiefCooker.Main;

import com.epam.training.ChiefCooker.Builder.SaladBuilder;

public class Main {	
	public static void main(String[] args){		
		SaladBuilder saladBuilder = new SaladBuilder();
		saladBuilder.buildSalad();
	}
}
