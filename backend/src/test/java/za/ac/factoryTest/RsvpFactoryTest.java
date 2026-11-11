package za.ac.factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.domain.Rsvp;
import za.ac.factory.RsvpFactory;

import static org.junit.jupiter.api.Assertions.*;

class RsvpFactoryTest {

    @Test
    void testCreateRsvpValid() {
        Rsvp rsvp = RsvpFactory.createRsvp("RSVP001", "Confirmed");

        assertNotNull(rsvp);
        assertEquals("RSVP001", rsvp.getRsvpId());
        assertEquals("Confirmed", rsvp.getStatus());
    }

    @Test
    void testCreateRsvpEmptyStatus() {
        Rsvp rsvp = RsvpFactory.createRsvp("RSVP001", "");
        assertNull(rsvp);
    }

    @Test
    void testCreateRsvpNullStatus() {
        Rsvp rsvp = RsvpFactory.createRsvp("RSVP001", null);
        assertNull(rsvp);
    }

    @Test
    void testCreateRsvpNullId() {
        Rsvp rsvp = RsvpFactory.createRsvp(null, "Confirmed");
        assertNull(rsvp);
    }

    @Test
    void testCreateRsvpEmptyId() {
        Rsvp rsvp = RsvpFactory.createRsvp("", "Confirmed");
        assertNull(rsvp);
    }
}