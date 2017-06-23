package library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import library.api.model.Announcement;
import library.api.model.Book;
import library.api.model.Price;
import library.api.service.AnnouncementManagementService;
import library.service.dao.AnnouncementDAO;
import library.service.dao.BookDAO;
import library.service.dao.exceptions.BookNotFoundException;

public class AnnouncementManagementServiceImpl implements AnnouncementManagementService {
	
	private static Logger LOG = LogManager.getLogger(AnnouncementManagementServiceImpl.class);
	
	
	private AnnouncementDAO announcementDAO;
	private BookDAO bookDAO;
	
	

	public AnnouncementManagementServiceImpl(AnnouncementDAO announcementDAO, BookDAO bookDAO) {
		super();
		this.announcementDAO = announcementDAO;
		this.bookDAO = bookDAO;
	}

	public Collection<Announcement> listAnnouncements() {
		return announcementDAO.readAnnouncements();
	}

	public Collection<Announcement> listOpenAnnouncements() {
		Collection<Announcement> result = new ArrayList<Announcement>();
		for(Announcement announcement : announcementDAO.readAnnouncements()){
			if(announcement.openAnnouncement()){
				result.add(announcement);
			}
		}
		return result;
	}

	public void announce(String bookISBNNo, Date expire, Price price) {
		try{
		Book book = bookDAO.readBookByISBNNo(bookISBNNo);
		Announcement announcement = new Announcement(book, price, new Date(), expire, false);
		announcementDAO.createAnnouncement(announcement);
		}
		catch(BookNotFoundException ex){
			LOG.warn(ex.getMessage());
		}

	}

}
