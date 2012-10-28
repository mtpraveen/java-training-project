package com.epam.training.ChiefCooker.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.epam.training.ChiefCooker.Cucurbitaceae.Squash;
import com.epam.training.ChiefCooker.Oil.KindsOfOil;
import com.epam.training.ChiefCooker.Oil.Oil;
import com.epam.training.ChiefCooker.Searching.SearchingVegetables;
import com.epam.training.ChiefCooker.Solanaceae.Pepper;
import com.epam.training.ChiefCooker.Solanaceae.RipenessOfSolanaceae;
import com.epam.training.ChiefCooker.Solanaceae.Tomato;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;
import com.epam.training.ChiefCooker.Vegetable.Salad;

public class SearchingVegetablesTest {

	@Test
	public void testSearchVegetablesInSalad() {
		AbstractVegetable pepper = new Pepper(100, 56, RipenessOfSolanaceae.BAD, false);
		AbstractVegetable tomato = new Tomato(200, 78, RipenessOfSolanaceae.GOOD, true);
		AbstractVegetable squash = new Squash(350, 120);
		
		Oil sunflowerOil = new Oil(KindsOfOil.SUNFLOWER);
		
		Salad firstSalad = new Salad(sunflowerOil, pepper, tomato, squash);
		
		ArrayList<AbstractVegetable> foundVegetables = new ArrayList<>();
		
//		foundVegetables = SearchingVegetables.searchVegetablesInSalad(firstSalad, "weight > 80, calorie < 100");
//		print(foundVegetables, "weight > 80, calorie < 100");
		
		foundVegetables = SearchingVegetables.searchVegetablesInSalad(firstSalad, "weight > 100, calorie < 130");
		print(foundVegetables, "weight > 100, calorie < 130");
		
		
//		foundVegetables = SearchingVegetables.searchVegetablesInSalad(firstSalad, "weight > 150");
//		print(foundVegetables, "weight > 300");
//		
//		foundVegetables = SearchingVegetables.searchVegetablesInSalad(firstSalad, "calorie = 78");
//		print(foundVegetables, "calorie = 78");
		
		
	}
	
	private void print(ArrayList<AbstractVegetable> vegetables, String condition) {
		for (AbstractVegetable vegetable : vegetables) {
			System.out.println(vegetable.getClass().getName());
		}
	}

}
