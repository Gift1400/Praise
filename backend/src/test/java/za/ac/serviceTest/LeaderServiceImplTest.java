package za.ac.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.domain.Leader;
import za.ac.repository.ILeaderRepository;
import za.ac.service.leaderService.LeaderServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaderServiceImplTest {

    @Mock
    private ILeaderRepository repository;

    @InjectMocks
    private LeaderServiceImpl service;

    private Leader leader;

    @BeforeEach
    void setUp() {
        leader = new Leader.Builder()
                .setLeaderId("LDR001")
                .setName("Pastor David")
                .setRole("Senior Pastor")
                .setBio("Serving the community for over 15 years")
                .build();
    }

    @Test
    void testCreate() {
        when(repository.save(leader)).thenReturn(leader);

        Leader created = service.create(leader);

        assertNotNull(created);
        assertEquals("LDR001", created.getLeaderId());
        assertEquals("Pastor David", created.getName());
        verify(repository, times(1)).save(leader);
    }

    @Test
    void testRead_Found() {
        when(repository.findById("LDR001")).thenReturn(Optional.of(leader));

        Leader found = service.read("LDR001");

        assertNotNull(found);
        assertEquals("LDR001", found.getLeaderId());
        verify(repository, times(1)).findById("LDR001");
    }

    @Test
    void testRead_NotFound() {
        when(repository.findById("LDR999")).thenReturn(Optional.empty());

        Leader found = service.read("LDR999");

        assertNull(found);
        verify(repository, times(1)).findById("LDR999");
    }

    @Test
    void testUpdate() {
        when(repository.save(leader)).thenReturn(leader);

        Leader updated = service.update(leader);

        assertNotNull(updated);
        assertEquals("LDR001", updated.getLeaderId());
        verify(repository, times(1)).save(leader);
    }

    @Test
    void testDelete_WhenExists() {
        when(repository.existsById("LDR001")).thenReturn(true);

        boolean deleted = service.delete("LDR001");

        assertTrue(deleted);
        verify(repository, times(1)).existsById("LDR001");
        verify(repository, times(1)).deleteById("LDR001");
    }

    @Test
    void testDelete_WhenNotExists() {
        when(repository.existsById("LDR999")).thenReturn(false);

        boolean deleted = service.delete("LDR999");

        assertFalse(deleted);
        verify(repository, times(1)).existsById("LDR999");
        verify(repository, never()).deleteById(anyString());
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(leader));

        List<Leader> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(leader, result.get(0));
        verify(repository, times(1)).findAll();
    }
}
