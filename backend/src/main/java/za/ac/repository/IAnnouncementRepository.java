package za.ac.repository;

import za.ac.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Enums.AnnouncementType;

import java.util.List;
import java.util.Set;

public interface IAnnouncementRepository extends JpaRepository<Announcement, String> {
    List<Announcement> getByType(AnnouncementType announcementType);
    List<Announcement> getByChurchSite(String churchSiteId);
}
