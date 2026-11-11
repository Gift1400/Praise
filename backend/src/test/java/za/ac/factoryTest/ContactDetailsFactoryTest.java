package za.ac.factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.domain.ContactDetails;
import za.ac.factory.ContactDetailsFactory;

import static org.junit.jupiter.api.Assertions.*;

class ContactDetailsFactoryTest {

    @Test
    void testCreateContactDetailsValid() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                "info@praisechurch.org",
                "0112345678",
                "0823456789",
                "123 Faith Street"
        );

        assertNotNull(contact);
        assertEquals("CD001", contact.getContactDetailsId());
        assertEquals("info@praisechurch.org", contact.getEmail());
        assertEquals("0112345678", contact.getPhone());
        assertEquals("0823456789", contact.getAltPhone());
        assertEquals("123 Faith Street", contact.getAddress());
    }

    @Test
    void testCreateContactDetailsInvalidPhone() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                "info@praisechurch.org",
                "invalid-phone",
                "0823456789",
                "123 Faith Street"
        );
        assertNull(contact);
    }

    @Test
    void testCreateContactDetailsInvalidAltPhone() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                "info@praisechurch.org",
                "0112345678",
                "1234",
                "123 Faith Street"
        );
        assertNull(contact);
    }

    @Test
    void testCreateContactDetailsNullPhone() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                "info@praisechurch.org",
                null,
                "0823456789",
                "123 Faith Street"
        );
        assertNull(contact);
    }

    @Test
    void testCreateContactDetailsNullEmail() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                null,
                "0112345678",
                "0823456789",
                "123 Faith Street"
        );
        assertNull(contact);
    }

    @Test
    void testCreateContactDetailsEmptyEmail() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                "",
                "0112345678",
                "0823456789",
                "123 Faith Street"
        );
        assertNull(contact);
    }

    @Test
    void testCreateContactDetailsNullId() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                null,
                "info@praisechurch.org",
                "0112345678",
                "0823456789",
                "123 Faith Street"
        );
        assertNull(contact);
    }

    @Test
    void testCreateContactDetailsNullAddress() {
        ContactDetails contact = ContactDetailsFactory.createContactDetails(
                "CD001",
                "info@praisechurch.org",
                "0112345678",
                "0823456789",
                null
        );
        assertNull(contact);
    }
}