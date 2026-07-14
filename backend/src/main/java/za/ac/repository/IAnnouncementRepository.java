package za.ac.repository;

import za.ac.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementRepository extends JpaRepository<Announcement, String> {
}
