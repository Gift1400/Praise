package za.ac.factoryTest;

import za.ac.factory.AnnouncementFactory;
import org.junit.jupiter.api.Test;
import za.ac.domain.Announcement;
import za.ac.domain.Enums.AnnouncementType;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementFactoryTest {

    @Test
    void createAnnouncement() {
        Announcement announcement = AnnouncementFactory.createAnnouncement(
                "Youth Event",
                "Friday at 6PM",
                "SITE001",
                AnnouncementType.EVENTS
        );

        assertNotNull(announcement);
        assertEquals(AnnouncementType.EVENTS, announcement.getType());
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
