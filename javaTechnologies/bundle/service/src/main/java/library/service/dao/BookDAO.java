package library.service.dao;

import java.util.Collection;

import library.api.model.Book;
import library.service.dao.exceptions.BookNotFoundException;

public interface BookDAO {

	void createBook(Book book);
	
	Collection<Book> readBooks();
	Book readBookByISBNNo(String ISBNNo) throws BookNotFoundException;
	
	
	
}
