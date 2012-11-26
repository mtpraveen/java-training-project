package com.epam.library.models;


public class Book {
	private String name;
	private String author;
	private int yearOfProduction;
	
	public Book(){
		this("","",0);
	}
	
	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}
	
/*	public static Book createBook(){
		Scanner scan = new Scanner(System.in);
		String name, author;
		int year;
		System.out.print("Enter the name: ");
		name = scan.nextLine();
		System.out.print("Enter the author: ");
		author = scan.nextLine();
		System.out.print("Enter the year of production: ");
		year = scan.nextInt();
		return new Book(name,author,year);
	}
*/
	
	public Book(String name,String author,int yearOfProduction){
		this.name = name;
		this.author = author;
		this.yearOfProduction = yearOfProduction;
	}
	
}
