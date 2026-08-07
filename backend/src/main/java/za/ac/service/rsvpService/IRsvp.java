package za.ac.service.rsvpService;

import za.ac.domain.Rsvp;
import za.ac.service.IService;
import java.util.*;

public interface IRsvp extends IService <Rsvp, String>{
    Set<Rsvp> getAll();
    Set<Rsvp> getRsvpByEvent(String eventId);
    Set<Rsvp> getRsvpByMember(String memberId);
}
