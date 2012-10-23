package com.epam.training.ChiefCooker.Builder;

import com.epam.training.ChiefCooker.Vegetable.Salad;

public abstract class AbstractBaseBuilder {
	Salad salad = new Salad();	
	
	public void setSalad(Salad salad) {
		this.salad = salad;
	}
	
	public Salad getSalad(){
		return salad;
	}
	
	public abstract void buildSalad();
}
