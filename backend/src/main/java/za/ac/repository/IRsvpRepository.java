package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Rsvp;

import java.util.List;

public interface IRsvpRepository extends JpaRepository<Rsvp, String> {
    @Query("SELECT r FROM Rsvp r WHERE r.event.eventId = :eventId")
    List<Rsvp> getRsvpByEvent(@Param("eventId") String eventId);

    @Query("SELECT r FROM Rsvp r WHERE r.member.memberId = :memberId")
    List<Rsvp> getRsvpByMember(@Param("memberId") String memberId);
}
