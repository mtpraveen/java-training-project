package com.epam.training.ChiefCooker.test;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import com.epam.training.ChiefCooker.Builder.SaladBuilder;
import com.epam.training.ChiefCooker.Cucurbitaceae.Squash;
import com.epam.training.ChiefCooker.Oil.KindsOfOil;
import com.epam.training.ChiefCooker.Oil.Oil;
import com.epam.training.ChiefCooker.Solanaceae.Pepper;
import com.epam.training.ChiefCooker.Solanaceae.RipenessOfSolanaceae;
import com.epam.training.ChiefCooker.Solanaceae.Tomato;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;
import com.epam.training.ChiefCooker.Vegetable.Salad;

public class SaladBuilderTest {

	@Test
	public void test() {
		SaladBuilder saladBuilder = new SaladBuilder();
		
		AbstractVegetable tomato;
		AbstractVegetable pepper;
		AbstractVegetable squash;
		
		Oil oil;
		
		Salad salad = new Salad();
		
		tomato = saladBuilder.createSolanaceae("Tomato", 300, 80, RipenessOfSolanaceae.GOOD, true);
		pepper = saladBuilder.createSolanaceae("Pepper", 100, 120, RipenessOfSolanaceae.BAD, false);
		squash = saladBuilder.createCucurbitaceae("Squash", 400, 300);
		
		assertTrue(tomato instanceof Tomato);
		assertTrue(pepper instanceof Pepper);
		assertTrue(squash instanceof Squash);
		
		salad.getVegetablesSalad().add(tomato);
		salad.getVegetablesSalad().add(pepper);
		salad.getVegetablesSalad().add(squash);
		
		assertTrue(salad.getVegetablesSalad().size() == 3);
		
		oil = saladBuilder.createOil("SUNFLOWER");
		
		assertTrue(oil instanceof Oil);
		
		salad.setOil(oil);
		
		assertTrue(salad.getOil().getKindOfOil() == KindsOfOil.SUNFLOWER);
		
		
	}

}
