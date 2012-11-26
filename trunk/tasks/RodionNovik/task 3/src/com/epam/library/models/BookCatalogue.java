package com.epam.library.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class BookCatalogue {
	private HashMap<Book, Integer> catalogue = new HashMap<Book, Integer>();
	private int totalNumberOfBooks;

	public BookCatalogue() {
		this("G:\\Catalogue.csv");
	}

	public BookCatalogue(String path) {
		File file = new File(path);
		if (file.exists()) {
			loadFromCsvFile(file);
		} else {
			System.out.println("File not found, catalogue not initialized");
		}
		totalNumberOfBooks = catalogue.size();
	}

	public int getTotalNumberOfBooks() {
		return totalNumberOfBooks;
	}

	public void addBooksToCatalogue(Book theBook, int number) {
		if (catalogue.containsKey(theBook)) {
			number += catalogue.get(theBook);
			catalogue.put(theBook, number);
		} else {
			catalogue.put(theBook, number);
		}
	}

	public boolean isBookAvailable(Book theBook) {
		if (catalogue.get(theBook) != null && catalogue.get(theBook) > 0) {
			return true;
		} else
			return false;
	}

	public void loadFromCsvFile(File file) {
		try {
			Scanner scan = new Scanner(file);
			String row = "";
			while (scan.hasNextLine()) {
				row = scan.nextLine();
				String[] values = row.split(",");
				Integer year = Integer.parseInt(values[2]);
				Integer number = Integer.parseInt(values[3]);
				catalogue.put(new Book(values[0], values[1], year), number);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
