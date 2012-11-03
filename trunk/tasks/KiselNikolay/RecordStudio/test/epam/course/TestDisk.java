package epam.course;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import epam.course.domain.Author;
import epam.course.domain.Disk;
import epam.course.domain.Genre;
import epam.course.domain.Record;
import static epam.course.service.DiskService.*;

public class TestDisk {
	private static Author a1;
	private static Author a2;
	private static Author a3;
	private static Record r1;
	private static Record r2;
	private static Record r3;
	private static Record r4;
	private static Disk d;

	@BeforeClass
	public static void createDisk() {
		a1 = new Author("Ivan", "Ivanov", "20.11.1987", "rus");
		a2 = new Author("Petr", "Petrov", "12.07.1987", "en");
		a3 = new Author("Oleg", "Abramov", "05.11.1980", "by");
		r1 = new Record("Fog", 130, Genre.JAZZ, a1);
		r2 = new Record("Alt", 205, Genre.ROCK, a3);
		r3 = new Record("Live", 195, Genre.POP, a2);
		r4 = new Record("September", 203, Genre.BLUES, a3);
		d = new Disk("The best songs");
		d.addRecord(r1);
		d.addRecord(r2);
		d.addRecord(r3);
		d.addRecord(r4);

	}

	@Test
	public void testFindRecordByName() {
		List<Record> records = new ArrayList<Record>();
		records.add(r1);
		records.add(r4);
		assertEquals(records, findRecordByName(d, "Fog", "September"));

	}

	@Test
	public void testFindRecordByAuthor() {
		List<Record> records=new ArrayList<Record>();
		records.add(r2);
		records.add(r4);
		assertEquals(records, findRecordByAuthor(d, "Abramov"));
	}
	
	@Test
	public void testFindRecordByGenre() {
		List<Record> records=new ArrayList<Record>();
		records.add(r2);
		records.add(r3);
		assertEquals(records, findRecordByGenre(d, "ROCK", "POP"));
	}
	
	@Test
	public void testSortByAuthor() {
		List<Record> records=new ArrayList<Record>();
		records.add(r2);
		records.add(r4);
		records.add(r1);
		records.add(r3);
		sortByAuthor(d);
		assertEquals(records, d.getRecords());
	}
	
	@Test
	public void testSortByName() {
		List<Record> records=new ArrayList<Record>();
		records.add(r2);
		records.add(r1);
		records.add(r3);
		records.add(r4);
		sortByName(d);
		assertEquals(records, d.getRecords());
	}

}
