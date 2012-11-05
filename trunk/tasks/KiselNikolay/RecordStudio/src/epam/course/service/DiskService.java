package epam.course.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import epam.course.comparators.AuthorComp;
import epam.course.comparators.GenreComp;
import epam.course.domain.Disk;
import epam.course.domain.Genre;
import epam.course.domain.Record;

public class DiskService {

	/**
	 * @param args
	 */

	/**
	 * define type of sort by Author. If 0 - by surname; 1 - by nationality;
	 */
	public static int typeOfSort = 0;

	public static int getTotalLength(Disk disk) {
		int totalLength = 0;
		for (Record r : disk.getRecords()) {
			totalLength += r.getLength();
		}
		return totalLength;
	}

	/**
	 * sort by Surname
	 */
	public static void sortByAuthor(Disk disk) {
		typeOfSort = 0;
		List<Record> records = disk.getRecords();
		Collections.sort(records, new AuthorComp());
	}

	/**
	 * sort by nationality
	 */
	public static void sortByAuthor(Disk disk, int type) {
		if (type == 1)
			typeOfSort = type;
		else
			typeOfSort = 0;
		List<Record> records = disk.getRecords();
		Collections.sort(records, new AuthorComp());
	}

	public static void sortByGenre(Disk disk) {
		List<Record> records = disk.getRecords();
		Collections.sort(records, new GenreComp());
	}

	public static void sortByName(Disk disk) {
		List<Record> records = disk.getRecords();
		Collections.sort(records, new Comparator<Record>() {

			@Override
			public int compare(Record r1, Record r2) {
				return r1.getName().compareTo(r2.getName());
			}

		});
	}

	public static void sortByLength(Disk disk) {
		List<Record> records = disk.getRecords();
		Collections.sort(records, new Comparator<Record>() {

			@Override
			public int compare(Record r1, Record r2) {
				if (r1.getLength() < r2.getLength())
					return 1;
				else if (r1.getLength() == r2.getLength())
					return 0;
				else
					return -1;
			}

		});
	}

	public static List<Record> findRecordByGenre(Disk disk, String... genres) {
		List<Record> records = disk.getRecords();
		List<Record> newRecords = new ArrayList<Record>();
		for (Record record : records) {
			for (String genre : genres) {
				Genre g = Genre.valueOf(genre.toUpperCase());
				if (record.getGenre().equals(g)) {
					newRecords.add(record);
					break;
				}
			}
		}
		return newRecords;
	}

	public static List<Record> findRecordByAuthor(Disk disk, String... surnames) {
		List<Record> records = disk.getRecords();
		List<Record> newRecords = new ArrayList<Record>();
		for (Record record : records) {
			for (String surname : surnames) {
				if (record.getAuthor().getSurname().equals(surname)) {
					newRecords.add(record);
					break;
				}
			}
		}
		return newRecords;
	}

	public static List<Record> findRecordByName(Disk disk, String... names) {
		List<Record> records = disk.getRecords();
		List<Record> newRecords = new ArrayList<Record>();
		for (Record record : records) {
			for (String name : names) {
				if (record.getName().equals(name)) {
					newRecords.add(record);
					break;
				}
			}
		}
		return newRecords;
	}

	public static List<Record> findRecordByAuthorAndGenre(Disk disk,
			String author, String genre) {
		List<Record> records = disk.getRecords();
		List<Record> newRecords = new ArrayList<Record>();
		for (Record record : records) {
			if (record.getAuthor().getSurname().equals(author)
					&& record.getGenre().toString().equals(genre)) {
				newRecords.add(record);

			}

		}
		return newRecords;
	}

	public static void printRecords(List<Record> records) {
		for (Record record : records) {
			int minutes = record.getLength() / 60;
			int seconds = record.getLength() % 60;
			System.out
					.printf("Name: \"%s\", Author: \"%s\", nat: \"%s\", Genre: \"%s\",  Minutes: %d:%d \n",
							record.getName(), record.getAuthor().getSurname(),
							record.getAuthor().getNationality(),
							record.getGenre(), minutes, seconds);
		}
	}

	public static void printDisk(Disk disk) {
		int totalMinutes = getTotalLength(disk) / 60;
		int totalSec = getTotalLength(disk) % 60;
		System.out.printf("Disk's name: \"%s\" Total length: %d:%d \n",
				disk.getName(), totalMinutes, totalSec);
		printRecords(disk.getRecords());

	}

}
