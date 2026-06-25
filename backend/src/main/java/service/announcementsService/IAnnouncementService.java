package service.announcementsService;

import za.ac.cput.domain.Announcement;

import java.util.Set;

public interface IAnnouncementService {

    Announcement create(Announcement announcement);

    Announcement read(String announcementId);

    Announcement update(Announcement announcement);

    boolean delete(String announcementId);

    Set<Announcement> getAll();
}
}
