package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Announcement;
import za.ac.cput.domain.AnnouncementType;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementFactoryTest {

    @Test
    void createAnnouncement() {
        Announcement announcement = AnnouncementFactory.createAnnouncement(
                "Youth Event",
                "Friday at 6PM",
                "SITE001",
                AnnouncementType.EVENT
        );

        assertNotNull(announcement);
        assertEquals(AnnouncementType.EVENT, announcement.getType());
    }

    @Test
    void createAnnouncementFail() {
        Announcement announcement = AnnouncementFactory.createAnnouncement(
                "Title",
                "Message",
                "SITE001",
                null
        );

        assertNull(announcement);
    }
}
