package aircraft;

import java.util.Comparator;

public class SortedByDistance implements Comparator<Airplane> {

	@Override
	public int compare(Airplane obj0, Airplane obj1) {
		double distance0 = obj0.distance;
		double distance1 = obj1.distance;

		if (distance0 > distance1) {
			return 1;
		} else if (distance0 < distance1) {
			return -1;
		} else {
			return 0;
		}
	}

}
