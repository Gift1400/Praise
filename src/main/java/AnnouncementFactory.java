package za.ac.cput.factory;

import za.ac.cput.domain.Announcement;
import za.ac.cput.domain.AnnouncementType;

import java.time.LocalDate;
import java.util.UUID;

public class AnnouncementFactory {

    public static Announcement createAnnouncement(
            String title,
            String message,
            String churchSiteId,
            AnnouncementType type) {

        if (title == null || title.isEmpty())
            return null;

        if (message == null || message.isEmpty())
            return null;

        if (type == null)
            return null;

        String id = UUID.randomUUID().toString();

        return new Announcement.Builder()
                .setAnnouncementId(id)
                .setTitle(title)
                .setMessage(message)
                .setDate(LocalDate.now())
                .setChurchSiteId(churchSiteId)
                .setType(type)
                .build();
    }
}
