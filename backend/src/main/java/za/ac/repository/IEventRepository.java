package za.ac.repository;

import za.ac.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;

public interface IEventRepository extends JpaRepository<Event, String> {
    List<Event> getEventByChurchSite(String churchSiteId);
    List<Event> findByDateGreaterThan(LocalDate now);
}
