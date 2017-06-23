package library.api.service;

import java.util.Date;
import java.util.Collection;

import library.api.model.Announcement;
import library.api.model.Book;
import library.api.model.Price;

public interface AnnouncementManagementService {

	Collection<Announcement> listAnnouncements();
	Collection<Announcement> listOpenAnnouncements();
	
	void announce(String bookISBNNo, Date expire, Price price);
}
