package library.service.dao;

import java.util.Collection;

import library.api.model.Announcement;

public interface AnnouncementDAO {

	void createAnnouncement(Announcement announcement);

	Collection<Announcement> readAnnouncements();

}
