package epam.course.example;

import epam.course.domain.Author;
import epam.course.domain.Disk;
import epam.course.domain.Genre;
import epam.course.domain.Record;
//import epam.course.service.DiskService;

import static epam.course.service.DiskService.*;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		printDisk(d);
		System.out.println("Sort records by Author: ");
		sortByAuthor(d);
		printDisk(d);
		System.out.println("Sort records by Genre: ");
		sortByGenre(d);
		printDisk(d);
		System.out.println("Sort records by Name: ");
		sortByName(d);
		printDisk(d);
		System.out.println("Sort records by Length: ");
		sortByLength(d);
		printDisk(d);
		System.out.println("Sort by author's nationality: ");
		sortByAuthor(d, 1);
		printDisk(d);
		System.out.println("Find by genres: ");
		printRecords(findRecordByGenre(d, "ROCK", "POP", "BLUES"));
		System.out.println("Find by Author: ");
		printRecords(findRecordByAuthor(d, "Ivanov", "Abramov"));
		System.out.println("Find by name: ");
		printRecords(findRecordByName(d, "September", "Alt"));
		System.out.println("Find by Author and Genre: ");
		printRecords(findRecordByAuthorAndGenre(d, "Abramov", "ROCK"));

	}
}
