package epam.course.comparators;

import java.util.Comparator;

import epam.course.domain.Record;

public class GenreComp implements Comparator<Record> {

	@Override
	public int compare(Record r1, Record r2) {
		return r1.getGenre().toString().compareTo(r2.getGenre().toString());
	}

}
