package library.persist;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Currency;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import library.api.model.Announcement;
import library.api.model.Book;
import library.api.model.Price;
import library.service.dao.AnnouncementDAO;

public class AnnouncementDAOJSONTest {

	private AnnouncementDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = new AnnouncementDAOJSON("resources/announcements.json");
	}

	@Test
	public void testCreateAnnouncement() {
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + 24*60*60*1000);
		dao.createAnnouncement(new Announcement(new Book("abc123", Book.Author.BMW, "green", 3, 200), new Price(100, Currency.getInstance("HUF")), today, tomorrow, false));
	}

	@Test
	public void testReadAnnouncements() {
		Collection<Announcement> announcements = dao.readAnnouncements();
		System.out.println(announcements);
	}

}
