package za.ac.factory;

import za.ac.domain.Event;
import za.ac.util.Helper;

import java.time.LocalDate;

public class EventFactory {

    public static Event createEvent(String eventId,
                                    String title,
                                    String description,
                                    LocalDate date,
                                    String time,
                                    String location,
                                    int expectedAttendees) {

        if(Helper.isNullOrEmpty(eventId) ||
        Helper.isNullOrEmpty(title) ||
        Helper.isNullOrEmpty(description) ||
        Helper.isNullOrEmpty(time) ||
        Helper.isNullOrEmpty(location)){
            return null;
        }
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
