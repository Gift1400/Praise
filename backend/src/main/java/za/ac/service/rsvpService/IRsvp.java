package za.ac.service.rsvpService;

import za.ac.domain.Rsvp;
import za.ac.service.IService;
import java.util.*;

public interface IRsvp extends IService <Rsvp, String>{
    List<Rsvp> getAll();
    List<Rsvp> getRsvpByEvent(String eventId);
    List<Rsvp> getRsvpByMember(String memberId);
}
