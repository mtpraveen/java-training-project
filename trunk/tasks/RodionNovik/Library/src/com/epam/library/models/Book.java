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
	
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(getClass() == obj.getClass()))
			return false;
		else {
			Book tmp = (Book) obj;
			if (tmp.name.equals(this.name) && tmp.author.equals(this.author) && tmp.yearOfProduction == this.yearOfProduction)
				return true;
			else
				return false;
		}
	}
	
	public Book(String name,String author,int yearOfProduction){
		this.name = name;
		this.author = author;
		this.yearOfProduction = yearOfProduction;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode())+((author == null) ? 0 : author.hashCode())+ yearOfProduction;
		return result;
	}
	
	public String toString(){
		return (name+","+author+","+yearOfProduction);
	}
}
