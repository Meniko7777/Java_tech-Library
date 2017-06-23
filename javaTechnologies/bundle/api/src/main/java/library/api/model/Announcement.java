package library.api.model;

import java.util.Date;

public class Announcement {

	private Book book;
	private Price price;
	private Date launchDate;
	private Date rentalDate;
	private boolean rented;

	public Announcement() {
		super();
	}

	public Announcement(Book book, Price price, Date launchDate, Date rentalDate, boolean isRented) {
		super();
		this.book = book;
		this.price = price;
		this.launchDate = launchDate;
		this.rentalDate = rentalDate;
		this.rented = isRented;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean isRented) {
		this.rented = isRented;
	}

	public boolean openAnnouncement() {
		return !rented && rentalDate.after(new Date());
	}

	@Override
	public String toString() {
		return "Announcement [book=" + book + ", price=" + price + ", launchDate=" + launchDate + ", rentalDate="
				+ rentalDate + ", rented=" + rented + "]";
	}

}
