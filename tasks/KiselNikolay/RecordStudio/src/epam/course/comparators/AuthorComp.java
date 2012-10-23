package epam.course.comparators;

import java.util.Comparator;

import epam.course.domain.Record;
import epam.course.service.DiskService;

public class AuthorComp implements Comparator<Record> {

	@Override
	public int compare(Record r1, Record r2) {
		if (DiskService.typeOfSort == 0) {
			return r1.getAuthor().getSurname()
					.compareTo(r2.getAuthor().getSurname());
		} else
			return r1.getAuthor().getNationality()
					.compareTo(r2.getAuthor().getNationality());

	}
}
