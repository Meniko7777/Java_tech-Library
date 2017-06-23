package library.api.service;

import java.util.Collection;

import library.api.model.Book;
import library.api.service.exceptions.UnknownBookException;

public interface BookManagementService {

	Collection<Book> listBooks();
	Book getBookByISBNNo(String plateNo) throws UnknownBookException;
	void acquireBook(Book book);
	
}
