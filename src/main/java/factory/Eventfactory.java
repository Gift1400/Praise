package factory;

import domain.Event;
import java.time.LocalDate;

public class Eventfactory {

    public static Event createEvent(String eventId,
                                    String title,
                                    String description,
                                    LocalDate date,
                                    String time,
                                    String location,
                                    int expectedAttendees) {
        return new Event.Builder()
                .eventId(eventId)
                .title(title)
                .description(description)
                .date(date)
                .time(time)
                .location(location)
                .expectedAttendees(expectedAttendees)
                .build();
    }
}
