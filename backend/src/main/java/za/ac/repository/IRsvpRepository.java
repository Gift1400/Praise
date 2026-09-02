package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.controller.RsvpController;
import za.ac.domain.Rsvp;

import java.util.List;

public interface IRsvpRepository extends JpaRepository<Rsvp, String> {
    List<Rsvp> getRsvpByEvent(String eventId);
    List<Rsvp> getRsvpByMember(String memberId);
}
