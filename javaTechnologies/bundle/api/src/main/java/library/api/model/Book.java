package library.api.model;

public class Book {

	public static enum Author {
		Shakespeare, Moliere
	}

	private String ISBNNo;
	private Author author;
	private String color;
	private int numberOfPages;
	private int rating;

	public Book() {
		super();
	}

	public Book(String ISBNNo, Author author, String color, int numberOfPages, int rating) {
		super();
		this.ISBNNo = ISBNNo;
		this.author = author;
		this.color = color;
		this.numberOfPages = numberOfPages;
		this.rating = rating;
	}

	public String getISBNNo() {
		return ISBNNo;
	}

	public void setISBNNo(String ISBNNo) {
		this.ISBNNo = ISBNNo;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
