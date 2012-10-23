package epam.course;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import epam.course.domain.Author;
import epam.course.domain.Disk;
import epam.course.domain.Genre;
import epam.course.domain.Record;
import epam.course.service.DiskService;

public class TestDisk {

	/**
	 * @param args
	 */
	@Test
	public void testFindRecordByName() {
		Author a1 = new Author("Ivan", "Ivanov", "20.11.1987", "rus");
		Author a2 = new Author("Petr", "Petrov", "12.07.1987", "en");
		Author a3 = new Author("Oleg", "Abramov", "05.11.1980", "by");
		Record r1 = new Record("Fog", 130, Genre.JAZZ, a1);
		Record r2 = new Record("Alt", 205, Genre.ROCK, a3);
		Record r3 = new Record("Live", 195, Genre.POP, a2);
		Record r4 = new Record("September", 203, Genre.BLUES, a3);
		Disk d = new Disk("The best songs");
		d.addRecord(r1);
		d.addRecord(r2);
		d.addRecord(r3);
		d.addRecord(r4);
		List<Record> records=new ArrayList<Record>();
		records.add(r1);
		records.add(r4);
		assertEquals(DiskService.findRecordByName(d, "Fog", "September"),records);
		
	}

}
