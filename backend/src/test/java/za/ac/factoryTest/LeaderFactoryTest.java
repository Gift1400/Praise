package za.ac.factoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.domain.ContactDetails;
import za.ac.domain.Leader;
import za.ac.factory.ContactDetailsFactory;
import za.ac.factory.LeaderFactory;

import static org.junit.jupiter.api.Assertions.*;


class LeaderFactoryTest {

    Leader leader;
    ContactDetails leaderContact;


    @BeforeEach
    void setUp() {
        leaderContact = ContactDetailsFactory.createContactDetails("LC001",
                "pastormhlengi@gmail.com",
                "0845447239",
                "0845447239","235 Adderley St");
        leader = LeaderFactory.createLeader("L001","Mhlengi", "Pastor","Prayer is powerfull",leaderContact);
    }

    @Test
    void createLeader() {
        assertNotNull(leaderContact);
        assertNotNull(leader);
        assertEquals("L001", leader.getLeaderId());
        assertEquals("Mhlengi", leader.getName());
        assertEquals("Pastor", leader.getRole());
        assertEquals("Prayer is powerfull", leader.getBio());
        assertEquals("LC001", leaderContact.getContactDetailsId());
        assertEquals("pastormhlengi@gmail.com", leaderContact.getEmail());
        assertEquals("0845447239", leaderContact.getPhone());
        assertEquals("0845447239", leaderContact.getAltPhone());
        assertEquals("235 Adderley St", leaderContact.getAddress());
    }
}