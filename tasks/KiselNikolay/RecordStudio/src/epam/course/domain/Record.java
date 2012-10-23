package epam.course.domain;

public class Record {

	/**
	 * @param args
	 */
	private String name;
	private int length;
	private Genre genre;
	private Author author;

	public Record(String name, int length, Genre genre, Author author) {
		super();
		this.name = name;
		this.length = length;
		this.genre = genre;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
