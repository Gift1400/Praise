import Factory.DonationFactory;
import domain.Donation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DonationFactoryTest {

    @Test
    void testCreateValidDonation() {
        Donation donation = DonationFactory.createDonation(
                1,
                500.0,
                LocalDate.of(2024, 5, 10),
                "Cash",
                "John Doe",
                "Charity Org"
        );

        assertNotNull(donation);
        assertEquals(1, donation.getDonation_id());
        assertEquals(500.0, donation.getAmount());
        assertEquals(LocalDate.of(2024, 5, 10), donation.getDonation_date());
        assertEquals("Cash", donation.getDonation_type());
        assertEquals("John Doe", donation.getDonor_name());
        assertEquals("Charity Org", donation.getRecipient_organization());
    }

    @Test
    void testCreateDonationWithInvalidId() {
        Donation donation = DonationFactory.createDonation(
                0, // invalid ID
                500.0,
                LocalDate.now(),
                "Cash",
                "Jane Doe",
                "Charity Org"
        );

        assertNull(donation, "Donation should be null when ID is invalid");
    }

    @Test
    void testCreateDonationWithNegativeAmount() {
        Donation donation = DonationFactory.createDonation(
                1,
                -100.0, // invalid amount
                LocalDate.now(),
                "Cash",
                "Jane Doe",
                "Charity Org"
        );

        assertNull(donation, "Donation should be null when amount is negative");
    }

    @Test
    void testCreateDonationWithMissingFields() {
        Donation donation = DonationFactory.createDonation(
                1,
                200.0,
                LocalDate.now(),
                "", // empty donation type
                "Jane Doe",
                "Charity Org"
        );

        assertNull(donation, "Donation should be null when donation type is empty");
    }
}