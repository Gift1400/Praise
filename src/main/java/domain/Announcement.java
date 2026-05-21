package domain;

import java.time.LocalDate;

public class Announcement {

    private String announcementId;
    private String title;
    private String message;
    private LocalDate date;
    private String churchSiteId;
    private AnnouncementType type;

    private Announcement(Builder builder) {
        this.announcementId = builder.announcementId;
        this.title = builder.title;
        this.message = builder.message;
        this.date = builder.date;
        this.churchSiteId = builder.churchSiteId;
        this.type = builder.type;
    }

    // Getters
    public String getAnnouncementId() {
        return announcementId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getChurchSiteId() {
        return churchSiteId;
    }

    public AnnouncementType getType() {
        return type;
    }

    // Builder
    public static class Builder {
        private String announcementId;
        private String title;
        private String message;
        private LocalDate date;
        private String churchSiteId;
        private AnnouncementType type;

        public Builder setAnnouncementId(String announcementId) {
            this.announcementId = announcementId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setChurchSiteId(String churchSiteId) {
            this.churchSiteId = churchSiteId;
            return this;
        }

        public Builder setType(AnnouncementType type) {
            this.type = type;
            return this;
        }

        public Announcement build() {
            return new Announcement(this);
        }
    }
}