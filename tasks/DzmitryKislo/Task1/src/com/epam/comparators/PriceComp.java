package com.epam.comparators;

import java.util.Comparator;

import com.epam.entity.Stone;

public class PriceComp implements Comparator<Stone> {

	@Override
	public int compare(Stone obj1, Stone obj2) {
		if (obj1.getPrice() < obj2.getPrice())
			return -1;
		else if (obj1.getPrice() > obj2.getPrice())
			return 1;
		else
			return 0;
	}

}
