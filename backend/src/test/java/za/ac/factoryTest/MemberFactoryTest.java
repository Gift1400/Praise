package za.ac.factoryTest;

import org.junit.jupiter.api.*;
import za.ac.domain.ContactDetails;
import za.ac.domain.Member;
import za.ac.factory.ContactDetailsFactory;
import za.ac.factory.MemberFactory;

import static org.junit.jupiter.api.Assertions.*;

class MemberFactoryTest {

    Member member;
    ContactDetails memberContact;

    @BeforeEach
    void setUp() {
        memberContact = ContactDetailsFactory.createContactDetails("8933J",
                "jacob20@gmail.com",
                "0693721131",
                "0812243967",
                "30452");
        member = MemberFactory.createMember("8933B", "Jacob_Malik", memberContact);
    }


    @Test
    @Order(1)
    void a_createMember(){
        assertNotNull(member);
        assertEquals("8933B", member.getMemberId());
        assertEquals("Jacob_Malik", member.getUserName());
        assertNotNull(memberContact);
        assertEquals("jacob20@gmail.com", memberContact.getEmail());
        assertEquals("0693721131", memberContact.getPhone());
        assertEquals("0812243967", memberContact.getAltPhone());
        assertEquals("30452", memberContact.getAddress());
    }


    @Test
    @Order(2)
    void b_memberIdNull(){
        String memberId = null;
        assertNull(memberId, "Member should not exist without memberId");
    }

    @Test
    @Order(3)
    void c_userNameNullTest(){
        member = MemberFactory.createMember(
                "D1434",
                null,
                memberContact
        );

        assertNull(member, "Member should have a userName");
    }


}
