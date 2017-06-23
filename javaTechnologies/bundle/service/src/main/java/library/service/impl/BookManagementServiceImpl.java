package library.service.impl;

import java.util.Collection;

import library.api.model.Book;
import library.api.service.BookManagementService;
import library.api.service.exceptions.UnknownBookException;
import library.service.dao.BookDAO;
import library.service.dao.exceptions.BookNotFoundException;

public class BookManagementServiceImpl implements BookManagementService {

	private BookDAO bookDAO;

	public BookManagementServiceImpl(BookDAO bookDAO) {
		super();
		this.bookDAO = bookDAO;
	}

	public Collection<Book> listBooks() {
		return bookDAO.readBooks();
	}

	public void acquireBook(Book book) {
		bookDAO.createBook(book);
	}
	
	public Book getBookByISBNNo(String ISBNNo) throws UnknownBookException {
		try {
			Book result = bookDAO.readBookByISBNNo(ISBNNo);
			return result;
		} catch (BookNotFoundException e) {
			throw new UnknownBookException(ISBNNo);
		}
	}

}
