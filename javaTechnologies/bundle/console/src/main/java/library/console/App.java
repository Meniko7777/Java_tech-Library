package library.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;

import library.api.model.Announcement;
import library.api.model.Book;
import library.api.model.Price;
import library.api.model.Book.Author;
import library.api.service.AnnouncementManagementService;
import library.api.service.BookManagementService;
import library.api.service.exceptions.UnknownBookException;
import library.persist.AnnouncementDAOJSON;
import library.persist.BookDAOJSON;
import library.service.dao.AnnouncementDAO;
import library.service.dao.BookDAO;
import library.service.impl.AnnouncementManagementServiceImpl;
import library.service.impl.BookManagementServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	private static BookManagementService bookManager;
	private static AnnouncementManagementService announcementManager;

	static {
		BookDAO bookDAO = new BookDAOJSON("resources/books.json");
		AnnouncementDAO announcementDAO = new AnnouncementDAOJSON("resources/announcements.json");
		bookManager = new BookManagementServiceImpl(bookDAO);
		announcementManager = new AnnouncementManagementServiceImpl(announcementDAO, bookDAO);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		while (run) {

			String line = br.readLine();
			if ("exit".equals(line)) {
				break;
			}
			if ("list books".equals(line)) {
				listBooks();
			}
			if ("insert book".equals(line)) {
				addBook();
			}
			if ("list announcements".equals(line)) {
				printAnnouncements(announcementManager.listAnnouncements());
			}
			if ("list open announcements".equals(line)) {
				printAnnouncements(announcementManager.listOpenAnnouncements());
			}
			if ("add announcement".equals(line)) {
				addAnnouncement();
			}
		}

	}

	private static void listBooks() {
		final int tableWidth = 30;
		printHorisontalLine(tableWidth);
		System.out.println("| ISBNNo | Author | Color | # Pages | Rating |");
		printHorisontalLine(tableWidth);
		for (Book book : bookManager.listBooks()) {
			System.out.println(String.format("| %1$7s | %2$8s | %3$5s | %4$7d | %5$11d |", book.getISBNNo(),
					book.getAuthor(), book.getColor(), book.getNumberOfPages(), book.getRating()));
			printHorisontalLine(tableWidth);
		}
	}

	private static void printHorisontalLine(int length) {
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private static void addBook() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ISBN No.: ");
		String ISBNNo = br.readLine();
		System.out.println("Author: ");
		Author author = Author.valueOf(br.readLine());
		System.out.println("Color");
		String color = br.readLine();
		System.out.println("Number of Pages: ");
		int pages = Integer.parseInt(br.readLine());
		System.out.println("Rating: ");
		int rating = Integer.parseInt(br.readLine());
		Book book = new Book(ISBNNo, author, color, pages, rating);
		bookManager.acquireBook(book);

	}

	private static void printAnnouncements(Collection<Announcement> announcements) {
		final int tableWidth = 80;
		printHorisontalLine(tableWidth);
		System.out.println(
				"| 						Book							 | 	Price			 | Start Date | Expire Date | open  |");
		System.out.println(
				"| ISBNNo | Author | Color | # Pages | Rating | Amount | Currency |			  |		        |       |");
		printHorisontalLine(tableWidth);
		for (Announcement announcement : announcements) {
			Book book = announcement.getBook();
			Price price = announcement.getPrice();
			System.out.println(String.format(
					"| %1$7s | %2$8s | %3$5s | %4$7d | %5$11d | %6$5.2f | %7$8s | %8$10s | %9$5s | %10$5s |",
					book.getISBNNo(), book.getAuthor(), book.getColor(), book.getNumberOfPages(), book.getRating(),
					price.getAmount(), price.getCurrency().toString(), announcement.getStartDate().toString(),
					announcement.getRentalDate().toString(), announcement.openAnnouncement()));
		}
	}

	private static void addAnnouncement() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ISBN No.: ");
		String ISBNNo = br.readLine();
		System.out.println("Rental Date (YYYY-MM-dd): ");
		String expireDateStr = br.readLine();
		System.out.println("Price (amount currency e.g. 100 HUF): ");
		String priceStr = br.readLine();

		try {
			Book book = bookManager.getBookByISBNNo(ISBNNo);
			DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			Date expireDate = df.parse(expireDateStr);
			Price price = new Price(Double.parseDouble(priceStr.split(" ")[0]),
					Currency.getInstance(priceStr.split(" ")[1]));

			announcementManager.announce(ISBNNo, expireDate, price);
		} catch (UnknownBookException e) {
			// TODO
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
