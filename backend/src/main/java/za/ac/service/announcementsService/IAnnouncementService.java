package za.ac.service.announcementsService;

import za.ac.domain.Announcement;
import za.ac.domain.Enums.AnnouncementType;
import za.ac.service.IService;

import java.util.Set;

public interface IAnnouncementService extends IService<Announcement, String> {

    Set<Announcement> getAll();
    Set<Announcement> getByType(AnnouncementType announcementType);
    Set<Announcement> getByChurchSite(String churchSiteId);
}
