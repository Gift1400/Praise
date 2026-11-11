package za.ac.domain;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    private String eventId;
    private String title;
    private String description;
    private LocalDate date;
    private String time;
    private String location;
    private int expectedAttendees;

    @ManyToOne
    @JoinColumn(name = "church_site_id")
    private ChurchSite churchSite;

    protected Event(){}

    private Event(Builder builder) {
        this.eventId = builder.eventId;
        this.title = builder.title;
        this.description = builder.description;
        this.date = builder.date;
        this.time = builder.time;
        this.location = builder.location;
        this.expectedAttendees = builder.expectedAttendees;
        this.churchSite = builder.churchSite;
    }

    public String getEventId() { return eventId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public String getTime()  { return time; }
    public String getLocation() { return location; }
    public int getExpectedAttendees() { return expectedAttendees; }
    public ChurchSite getChurchSite(){ return churchSite;}


    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId + '\'' +
                ", title=" + title + '\'' +
                ", description=" + description + '\'' +
                ", date=" + date +
                ", time=" + time  + '\'' +
                ", location=" + location  + '\'' +
                ", expectedAttendees=" + expectedAttendees + '\'' +
                ", churchSite=" + churchSite +
                '}';
    }

    public static class Builder {

        private String eventId;
        private String title;
        private String description;
        private LocalDate date;
        private String time;
        private String location;
        private int expectedAttendees;
        private ChurchSite churchSite;

        public Builder setEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setExpectedAttendees(int expectedAttendees) {
            this.expectedAttendees = expectedAttendees;
            return this;
        }

        public Builder setChurchSite(ChurchSite churchSite){
            this.churchSite = churchSite;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
