package za.ac.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.domain.Announcement;
import za.ac.domain.Enums.AnnouncementType;
import za.ac.repository.IAnnouncementRepository;
import za.ac.service.announcementsService.AnnouncementServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceImplTest {

    @Mock
    private IAnnouncementRepository repository;

    @InjectMocks
    private AnnouncementServiceImpl service;

    private Announcement announcement;

    @BeforeEach
    void setUp() {
        announcement = new Announcement.Builder()
                .setAnnouncementId("ANN001")
                .setTitle("Youth Camp")
                .setMessage("Annual youth camp registration is open")
                .setDate(LocalDate.of(2026, 9, 1))
                .setChurchSiteId("SITE001")
                .setType(AnnouncementType.EVENTS)
                .build();
    }

    @Test
    void testCreate() {
        when(repository.save(announcement)).thenReturn(announcement);

        Announcement created = service.create(announcement);

        assertNotNull(created);
        assertEquals("ANN001", created.getAnnouncementId());
        assertEquals("Youth Camp", created.getTitle());
        verify(repository, times(1)).save(announcement);
    }

    @Test
    void testRead_Found() {
        when(repository.findById("ANN001")).thenReturn(Optional.of(announcement));

        Announcement found = service.read("ANN001");

        assertNotNull(found);
        assertEquals("ANN001", found.getAnnouncementId());
        verify(repository, times(1)).findById("ANN001");
    }

    @Test
    void testRead_NotFound() {
        when(repository.findById("ANN999")).thenReturn(Optional.empty());

        Announcement found = service.read("ANN999");

        assertNull(found);
        verify(repository, times(1)).findById("ANN999");
    }

    @Test
    void testUpdate() {
        when(repository.save(announcement)).thenReturn(announcement);

        Announcement updated = service.update(announcement);

        assertNotNull(updated);
        assertEquals("ANN001", updated.getAnnouncementId());
        verify(repository, times(1)).save(announcement);
    }

    @Test
    void testDelete_WhenExists() {
        when(repository.existsById("ANN001")).thenReturn(true);

        boolean deleted = service.delete("ANN001");

        assertTrue(deleted);
        verify(repository, times(1)).existsById("ANN001");
        verify(repository, times(1)).deleteById("ANN001");
    }

    @Test
    void testDelete_WhenNotExists() {
        when(repository.existsById("ANN999")).thenReturn(false);

        boolean deleted = service.delete("ANN999");

        assertFalse(deleted);
        verify(repository, times(1)).existsById("ANN999");
        verify(repository, never()).deleteById(anyString());
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(announcement));

        List<Announcement> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(announcement, result.get(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetByType() {
        when(repository.getByType(AnnouncementType.EVENTS)).thenReturn(List.of(announcement));

        List<Announcement> result = service.getByType(AnnouncementType.EVENTS);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).getByType(AnnouncementType.EVENTS);
    }

    @Test
    void testGetByType_Null() {
        List<Announcement> result = service.getByType(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, never()).getByType(any());
    }

    @Test
    void testGetByChurchSiteId() {
        when(repository.getByChurchSiteId("SITE001")).thenReturn(List.of(announcement));

        List<Announcement> result = service.getByChurchSiteId("SITE001");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).getByChurchSiteId("SITE001");
    }

    @Test
    void testGetByChurchSiteId_Null() {
        List<Announcement> result = service.getByChurchSiteId(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, never()).getByChurchSiteId(any());
    }
}