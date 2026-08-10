package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.controller.RsvpController;
import za.ac.domain.Rsvp;

public interface IRsvpRepository extends JpaRepository<Rsvp, String> {
}
