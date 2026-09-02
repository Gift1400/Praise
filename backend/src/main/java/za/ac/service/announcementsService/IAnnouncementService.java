package za.ac.service.announcementsService;

import za.ac.domain.Announcement;
import za.ac.domain.Enums.AnnouncementType;
import za.ac.service.IService;
import java.util.*;

public interface IAnnouncementService extends IService<Announcement, String> {

    List<Announcement> getAll();
    List<Announcement> getByType(AnnouncementType announcementType);
    List<Announcement> getByChurchSite(String churchSiteId);
}
