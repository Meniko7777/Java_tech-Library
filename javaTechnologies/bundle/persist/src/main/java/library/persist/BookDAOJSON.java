package library.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import library.api.model.Book;
import library.service.dao.BookDAO;
import library.service.dao.exceptions.BookNotFoundException;

public class BookDAOJSON implements BookDAO {

	private Logger LOGGER = LogManager.getLogger(BookDAOJSON.class);
	
	private File database;
	
	public BookDAOJSON(String databasePath) {
		this.database = new File(databasePath);
		LOGGER.debug(String.format("Book Databse : %s", database.getAbsolutePath()));
	}

	public void createBook(Book book) {
		Collection<Book> allBooks = readBooks();
		allBooks.add(book);
		Book[] extendedDatabase = allBooks.toArray(new Book[allBooks.size()]);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(database, extendedDatabase);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		LOGGER.info(String.format("Book (%s) has been added!", book.getISBNNo()));
		

	}

	public Collection<Book> readBooks() {
		ObjectMapper mapper = new ObjectMapper();
		Book[] books = new Book[] {};
		try {
			books = mapper.readValue(database, Book[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		Collection<Book> result = new ArrayList<Book>(Arrays.asList(books));
		return result;
	}

	public Book readBookByISBNNo(String ISBNNo) throws BookNotFoundException {
		for(Book book : readBooks()){
			if(ISBNNo.equals(book.getISBNNo())){
				return book;
			}
		}
		throw new BookNotFoundException(String.format("There is no book with ISBN number: %s", ISBNNo));
	}

}
