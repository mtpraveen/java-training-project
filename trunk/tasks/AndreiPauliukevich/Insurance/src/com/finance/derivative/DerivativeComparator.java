package com.finance.derivative;

import java.util.Comparator;

import com.finance.model.Insurance;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class DerivativeComparator implements Comparator<Insurance> {

	@Override
	public int compare(Insurance arg0, Insurance arg1) {
		if (arg0.getRisk() < arg1.getRisk()) {
			return 1;
		} else if (arg0.getRisk() > arg1.getRisk()) {
			return -1;
		} else
			return 0;
	}
}
