package za.ac.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.domain.Donation;
import za.ac.repository.IDonationRepository;
import za.ac.service.donationService.DonationServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DonationServiceImplTest {

    @Mock
    private IDonationRepository repository;

    @InjectMocks
    private DonationServiceImpl service;

    private Donation donation;

    @BeforeEach
    void setUp() {
        donation = new Donation.Builder()
                .setDonation_id(1)
                .setAmount(500.0)
                .setDonation_date(LocalDate.of(2026, 5, 10))
                .setDonation_type("Tithe")
                .setDonor_name("John Doe")
                .setRecipient_organization("Praise Church")
                .build();
    }

    @Test
    void testCreate() {
        when(repository.save(donation)).thenReturn(donation);

        Donation created = service.create(donation);

        assertNotNull(created);
        assertEquals(1, created.getDonation_id());
        assertEquals(500.0, created.getAmount());
        verify(repository, times(1)).save(donation);
    }

    @Test
    void testRead_Found() {
        when(repository.findById(1)).thenReturn(Optional.of(donation));

        Donation found = service.read(1);

        assertNotNull(found);
        assertEquals(1, found.getDonation_id());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testRead_NotFound() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        Donation found = service.read(999);

        assertNull(found);
        verify(repository, times(1)).findById(999);
    }

    @Test
    void testUpdate() {
        when(repository.save(donation)).thenReturn(donation);

        Donation updated = service.update(donation);

        assertNotNull(updated);
        assertEquals(1, updated.getDonation_id());
        verify(repository, times(1)).save(donation);
    }

    @Test
    void testDelete_WhenExists() {
        when(repository.existsById(1)).thenReturn(true);

        boolean deleted = service.delete(1);

        assertTrue(deleted);
        verify(repository, times(1)).existsById(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void testDelete_WhenNotExists() {
        when(repository.existsById(999)).thenReturn(false);

        boolean deleted = service.delete(999);

        assertFalse(deleted);
        verify(repository, times(1)).existsById(999);
        verify(repository, never()).deleteById(anyInt());
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(donation));

        List<Donation> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(donation, result.get(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetDonationsByMember() {
        when(repository.getDonationsByMember("MEM001")).thenReturn(List.of(donation));

        List<Donation> result = service.getDonationsByMember("MEM001");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).getDonationsByMember("MEM001");
    }

    @Test
    void testGetDonationsByMember_Null() {
        List<Donation> result = service.getDonationsByMember(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, never()).getDonationsByMember(any());
    }

    @Test
    void testGetTotalDonations_WithValue() {
        when(repository.sumAllDonations()).thenReturn(1500.0);

        double total = service.getTotalDonations();

        assertEquals(1500.0, total, 0.001);
        verify(repository, times(1)).sumAllDonations();
    }

    @Test
    void testGetTotalDonations_WhenNull() {
        when(repository.sumAllDonations()).thenReturn(null);

        double total = service.getTotalDonations();

        assertEquals(0.0, total, 0.001);
        verify(repository, times(1)).sumAllDonations();
    }
}