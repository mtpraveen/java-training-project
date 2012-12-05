package com.epam.library.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BookCatalogue {
	private HashMap<Book, Integer> catalogue = new HashMap<Book, Integer>();
	private int totalNumberOfBooks;

	public BookCatalogue() {
		this("G:\\pack.zip");
	}

	public BookCatalogue(String path) {
		File file = new File(path);
		if (file.exists()) {
			loadFromCsvFileInZip(file);
		} else {
			System.out.println("File not found, catalogue not initialized");
		}
		totalNumberOfBooks = catalogue.size();
	}
	
	public HashMap<Book, Integer> getCatalogue() {
		return catalogue;
	}

	public void getBook(Book book){
		if(isBookAvailable(book)){
			int tmpAmount = catalogue.get(book);
			tmpAmount--;
			catalogue.put(book, new Integer(tmpAmount));
		}
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
		if (catalogue.containsKey(theBook) && catalogue.get(theBook) > 0) {
			return true;
		} else
			return false;
	}

	public void loadFromCsvFileInZip(File file) {
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
			ZipEntry zipEntry = null;
			while((zipEntry = zin.getNextEntry())!=null){
				if(zipEntry.getName().equalsIgnoreCase("catalogue.csv")){
					Scanner scan = new Scanner(zin);
					String row = "";
					while (scan.hasNextLine()) {
						row = scan.nextLine();
						String[] values = row.split(",");
						Integer year = Integer.parseInt(values[2]);
						Integer number = Integer.parseInt(values[3]);
						catalogue.put(new Book(values[0], values[1], year), number);
					}
				}
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
