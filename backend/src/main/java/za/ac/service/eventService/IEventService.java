package za.ac.service.eventService;

import za.ac.domain.Event;
import za.ac.service.IService;
import java.util.*;

public interface IEventService extends IService<Event, String> {
    Set<Event> getAll();
    Set<Event> getUpcomingEvents();
    Set<Event> getEventByChurchSite(String churchSiteId);
}
