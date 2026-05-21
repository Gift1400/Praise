package domain;

import java.time.LocalDate;

public class Event {

    private final String eventId;
    private final String title;
    private final String description;
    private final LocalDate date;
    private final String time;
    private final String location;
    private final int expectedAttendees;

    private Event(Builder builder) {
        this.eventId           = builder.eventId;
        this.title             = builder.title;
        this.description       = builder.description;
        this.date              = builder.date;
        this.time              = builder.time;
        this.location          = builder.location;
        this.expectedAttendees = builder.expectedAttendees;
    }

    public String    getEventId()           { return eventId; }
    public String    getTitle()             { return title; }
    public String    getDescription()       { return description; }
    public LocalDate getDate()              { return date; }
    public String    getTime()              { return time; }
    public String    getLocation()          { return location; }
    public int       getExpectedAttendees() { return expectedAttendees; }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='"           + eventId           + '\'' +
                ", title='"           + title             + '\'' +
                ", description='"     + description       + '\'' +
                ", date="             + date              +
                ", time='"            + time              + '\'' +
                ", location='"        + location          + '\'' +
                ", expectedAttendees=" + expectedAttendees +
                '}';
    }

    public static class Builder {

        private String    eventId;
        private String    title;
        private String    description;
        private LocalDate date;
        private String    time;
        private String    location;
        private int       expectedAttendees;

        public Builder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder expectedAttendees(int expectedAttendees) {
            this.expectedAttendees = expectedAttendees;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
