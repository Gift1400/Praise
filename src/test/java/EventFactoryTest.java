import domain.Event;
import factory.Eventfactory;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EventFactoryTest {

    private final LocalDate eventDate = LocalDate.of(2026, 4, 10);

    @Test
    public void testCreateEvent_ShouldReturnNotNull() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertNotNull(event);
    }

    @Test
    public void testCreateEvent_ShouldHaveCorrectEventId() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertEquals("E001", event.getEventId());
    }

    @Test
    public void testCreateEvent_ShouldHaveCorrectTitle() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertEquals("Easter Celebration", event.getTitle());
    }

    @Test
    public void testCreateEvent_ShouldHaveCorrectDescription() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertEquals("Annual Easter gathering for all members", event.getDescription());
    }

    @Test
    public void testCreateEvent_ShouldHaveCorrectDate() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertEquals(eventDate, event.getDate());
    }

    @Test
    public void testCreateEvent_ShouldHaveCorrectLocation() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertEquals("Main Hall", event.getLocation());
    }

    @Test
    public void testCreateEvent_ShouldHaveCorrectExpectedAttendees() {
        Event event = Eventfactory.createEvent(
                "E001", "Easter Celebration",
                "Annual Easter gathering for all members",
                eventDate, "10:00", "Main Hall", 300);
        assertEquals(300, event.getExpectedAttendees());
    }
}