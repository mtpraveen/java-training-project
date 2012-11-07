package com.epam.training.ChiefCooker.test;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

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

	enum VarietyStatus { NO, BAD, GOOD }
	
	@Test
	public void testSearchVegetablesInSalad() {
		ArrayList<AbstractVegetable> setOfVegetables = new ArrayList<>();
		ArrayList<AbstractVegetable> foundSetOfVegetables = new ArrayList<>();
		
		setOfVegetables.add(new Pepper(100, 56, RipenessOfSolanaceae.BAD, false));		
		setOfVegetables.add(new Tomato(200, 78, RipenessOfSolanaceae.GOOD, true));
		setOfVegetables.add(new Squash(350, 120));
		
		foundSetOfVegetables.addAll(SearchingVegetables.searchVegetablesInSalad(setOfVegetables, "weight > 100, calorie < 130"));
		assertEquals(getVarietyStatus(foundSetOfVegetables.size()), VarietyStatus.GOOD);
		foundSetOfVegetables.clear();

		foundSetOfVegetables.addAll(SearchingVegetables.searchVegetablesInSalad(setOfVegetables, "weight > 200"));
		assertEquals(getVarietyStatus(foundSetOfVegetables.size()), VarietyStatus.BAD);
		foundSetOfVegetables.clear();
		
		foundSetOfVegetables.addAll(SearchingVegetables.searchVegetablesInSalad(setOfVegetables, "calorie = 78"));
		assertEquals(getVarietyStatus(foundSetOfVegetables.size()), VarietyStatus.BAD);
		foundSetOfVegetables.clear();
		
		foundSetOfVegetables.addAll(SearchingVegetables.searchVegetablesInSalad(setOfVegetables, "weight > 300, calorie < 50"));
		assertEquals(getVarietyStatus(foundSetOfVegetables.size()), VarietyStatus.NO);
		foundSetOfVegetables.clear();		
		
	}
	
	/**
	 * Get status of variety for vegetables that subject to it number in the future salad.
	 * @param numberOfVegetables - number of found vegetables with specified parameters.
	 */
	private VarietyStatus getVarietyStatus(int numberOfVegetables) {
		if (numberOfVegetables == 0) {
			return VarietyStatus.NO;
		} else if (numberOfVegetables == 1) {
			return VarietyStatus.BAD;
		} else if (numberOfVegetables > 1) {
			return VarietyStatus.GOOD;
		} else {
			return null;
		}
	}
}
