package za.ac.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface IEventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e FROM Event e WHERE e.churchSite.churchSiteId = :churchSiteId")
    List<Event> getEventByChurchSite(@Param("churchSiteId") String churchSiteId);
    List<Event> findByDateGreaterThan(LocalDate now);
}
