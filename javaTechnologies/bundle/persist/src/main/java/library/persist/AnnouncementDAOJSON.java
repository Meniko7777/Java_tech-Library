package library.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import library.api.model.Announcement;
import library.api.model.Book;
import library.service.dao.AnnouncementDAO;
import library.service.dao.BookDAO;

public class AnnouncementDAOJSON implements AnnouncementDAO {

	private static Logger LOG = LogManager.getLogger(AnnouncementDAOJSON.class);

	private final File database;

	public AnnouncementDAOJSON(String database) {
		super();
		this.database = new File(database);
	}

	public void createAnnouncement(Announcement announcement) {
		Collection<Announcement> announcements = new ArrayList<Announcement>(readAnnouncements());
		announcements.add(announcement);
//		System.out.println(announcements);
		Announcement[] announcementsArray = announcements.toArray(new Announcement[announcements.size()]);
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Announcement.class, AnnouncementMixIn.class);
		try {
			mapper.writeValue(database, announcementsArray);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Collection<Announcement> readAnnouncements() {
		Collection<Announcement> result;
		Announcement[] announcements = new Announcement[] {};
		try {
			ObjectMapper mapper = new ObjectMapper();
			announcements = mapper.readValue(database, Announcement[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			LOG.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		result = new ArrayList<Announcement>(Arrays.asList(announcements));
		return result;
	}

	private abstract class AnnouncementMixIn{
		@JsonIgnore abstract boolean openAnnouncement(); 
	}
}
