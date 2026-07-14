package ac.za.factoryTest;

import ac.za.factory.AnnouncementFactory;
import org.junit.jupiter.api.Test;
import ac.za.domain.Announcement;
import ac.za.domain.Enums.AnnouncementType;

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
