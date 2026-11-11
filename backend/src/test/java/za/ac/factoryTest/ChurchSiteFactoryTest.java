package za.ac.factoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.domain.ChurchSite;
import za.ac.domain.ContactDetails;
import za.ac.factory.ChurchSiteFactory;
import za.ac.factory.ContactDetailsFactory;

import static org.junit.jupiter.api.Assertions.*;

class ChurchSiteFactoryTest {

    private ContactDetails contactDetails;

    @BeforeEach
    void setUp() {
        contactDetails = ContactDetailsFactory.createContactDetails(
                "CD001",
                "info@praisechurch.org",
                "0112345678",
                "0823456789",
                "123 Faith Street"
        );
    }

    @Test
    void testCreateChurchSiteSuccess() {
        ChurchSite churchSite = ChurchSiteFactory.createChurchSite("CS001", "Praise Cape Town", contactDetails);

        assertNotNull(churchSite);
        assertEquals("CS001", churchSite.getchurchSiteId());
        assertEquals("Praise Cape Town", churchSite.getChurchName());
        assertEquals(contactDetails, churchSite.getContactDetails());
    }

    @Test
    void testCreateChurchSiteWithNullId() {
        ChurchSite churchSite = ChurchSiteFactory.createChurchSite(null, "Praise Cape Town", contactDetails);
        assertNull(churchSite);
    }

    @Test
    void testCreateChurchSiteWithEmptyId() {
        ChurchSite churchSite = ChurchSiteFactory.createChurchSite("", "Praise Cape Town", contactDetails);
        assertNull(churchSite);
    }

    @Test
    void testCreateChurchSiteWithNullName() {
        ChurchSite churchSite = ChurchSiteFactory.createChurchSite("CS001", null, contactDetails);
        assertNull(churchSite);
    }

    @Test
    void testCreateChurchSiteWithEmptyName() {
        ChurchSite churchSite = ChurchSiteFactory.createChurchSite("CS001", "", contactDetails);
        assertNull(churchSite);
    }
}