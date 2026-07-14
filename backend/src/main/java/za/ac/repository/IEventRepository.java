package za.ac.repository;

import za.ac.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, String> {
}
