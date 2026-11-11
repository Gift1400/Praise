package za.ac.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.domain.Event;
import za.ac.repository.IEventRepository;
import za.ac.service.eventService.EventServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private IEventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event.Builder()
                .setEventId("EV001")
                .setTitle("Sunday Worship")
                .setDescription("Weekly Sunday Service")
                .setDate(LocalDate.of(2026, 11, 1))
                .setTime("09:00 AM")
                .setLocation("Main Sanctuary")
                .setExpectedAttendees(250)
                .build();
    }

    @Test
    void testCreate() {
        when(eventRepository.save(event)).thenReturn(event);

        Event created = eventService.create(event);

        assertNotNull(created);
        assertEquals("EV001", created.getEventId());
        assertEquals("Sunday Worship", created.getTitle());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testRead_Found() {
        when(eventRepository.findById("EV001")).thenReturn(Optional.of(event));

        Event found = eventService.read("EV001");

        assertNotNull(found);
        assertEquals("EV001", found.getEventId());
        verify(eventRepository, times(1)).findById("EV001");
    }

    @Test
    void testRead_NotFound() {
        when(eventRepository.findById("EV999")).thenReturn(Optional.empty());

        Event found = eventService.read("EV999");

        assertNull(found);
        verify(eventRepository, times(1)).findById("EV999");
    }

    @Test
    void testUpdate() {
        when(eventRepository.save(event)).thenReturn(event);

        Event updated = eventService.update(event);

        assertNotNull(updated);
        assertEquals("EV001", updated.getEventId());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testDelete_WhenExists() {
        when(eventRepository.existsById("EV001")).thenReturn(true);

        boolean deleted = eventService.delete("EV001");

        assertTrue(deleted);
        verify(eventRepository, times(1)).existsById("EV001");
        verify(eventRepository, times(1)).deleteById("EV001");
    }

    @Test
    void testDelete_WhenNotExists() {
        when(eventRepository.existsById("EV999")).thenReturn(false);

        boolean deleted = eventService.delete("EV999");

        assertFalse(deleted);
        verify(eventRepository, times(1)).existsById("EV999");
        verify(eventRepository, never()).deleteById(anyString());
    }

    @Test
    void testGetAll() {
        when(eventRepository.findAll()).thenReturn(List.of(event));

        List<Event> result = eventService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(event, result.get(0));
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testGetUpcomingEvents() {
        when(eventRepository.findByDateGreaterThan(any(LocalDate.class))).thenReturn(List.of(event));

        List<Event> result = eventService.getUpcomingEvents();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(eventRepository, times(1)).findByDateGreaterThan(any(LocalDate.class));
    }

    @Test
    void testGetEventByChurchSite() {
        when(eventRepository.getEventByChurchSite("SITE001")).thenReturn(List.of(event));

        List<Event> result = eventService.getEventByChurchSite("SITE001");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(eventRepository, times(1)).getEventByChurchSite("SITE001");
    }

    @Test
    void testGetEventByChurchSite_Null() {
        List<Event> result = eventService.getEventByChurchSite(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(eventRepository, never()).getEventByChurchSite(any());
    }
}