package library.persist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import library.api.model.Book;
import library.service.dao.BookDAO;

public class BookDAOJSONTest {
	
	private BookDAO dao;
	
	@Before
	public void setUp(){
		dao = new BookDAOJSON("resources/books.json");
	}

	@Test
	public void testCreateBook() {
		Book book = new Book("abc123", Book.Author.AUDI, "red", 5, 100);
		dao.createBook(book);
	}

	@Test
	public void testReadBooks() {
		fail("Not yet implemented");
	}

}
