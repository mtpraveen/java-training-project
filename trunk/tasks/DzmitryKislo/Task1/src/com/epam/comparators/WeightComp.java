package com.epam.comparators;

import java.util.Comparator;

import com.epam.entity.Stone;

public class WeightComp implements Comparator<Stone> {

	public int compare(Stone obj1, Stone obj2) {
		if (obj1.getWeight() < obj2.getWeight())
			return -1;
		else if (obj1.getWeight() > obj2.getWeight())
			return 1;
		else
			return 0;

	}

}
