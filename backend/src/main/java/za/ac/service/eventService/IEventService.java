package za.ac.service.eventService;

import za.ac.domain.Event;
import za.ac.service.IService;
import java.util.*;

public interface IEventService extends IService<Event, String> {
    List<Event> getAll();
    List<Event> getUpcomingEvents();
    List<Event> getEventByChurchSite(String churchSiteId);
}
