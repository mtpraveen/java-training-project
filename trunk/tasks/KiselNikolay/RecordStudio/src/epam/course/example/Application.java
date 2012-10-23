package epam.course.example;

import epam.course.domain.Author;
import epam.course.domain.Disk;
import epam.course.domain.Genre;
import epam.course.domain.Record;
import epam.course.service.DiskService;

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
		DiskService.printDisk(d);
		System.out.println("Sort records by Author: ");
		DiskService.sortByAuthor(d);
		DiskService.printDisk(d);
		System.out.println("Sort records by Genre: ");
		DiskService.sortByGenre(d);
		DiskService.printDisk(d);
		System.out.println("Sort records by Name: ");
		DiskService.sortByName(d);
		DiskService.printDisk(d);
		System.out.println("Sort records by Length: ");
		DiskService.sortByLength(d);
		DiskService.printDisk(d);
		System.out.println("Sort by author's nationality: ");
		DiskService.sortByAuthor(d, 1);
		DiskService.printDisk(d);
		System.out.println("Find by genres: ");
		DiskService.printRecords(DiskService.findRecordByGenre(d, "ROCK",
				"POP", "BLUES"));
		System.out.println("Find by Author: ");
		DiskService.printRecords(DiskService.findRecordByAuthor(d, "Ivanov",
				"Abramov"));
		System.out.println("Find by name: ");
		DiskService.printRecords(DiskService.findRecordByName(d, "September",
				"Alt"));
		System.out.println("Find by Author and Genre: ");
		DiskService.printRecords(DiskService.findRecordByAuthorAndGenre(d,
				"Abramov", "ROCK"));

	}
}
