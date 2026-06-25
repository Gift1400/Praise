package repository;

import domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnnouncementRepository extends JpaRepository<Announcement, String> {

    Set<Announcement> getAll();
}
