package za.ac.factoryTest;

import org.junit.jupiter.api.*;
import za.ac.domain.ContactDetails;
import za.ac.domain.Member;
import za.ac.factory.ContactDetailsFactory;
import za.ac.factory.MemberFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   // ← makes @Order work
class MemberFactoryTest {

    private ContactDetails memberContact;

    @BeforeEach
    void setUp() {
        memberContact = ContactDetailsFactory.createContactDetails(
                "8933J",
                "jacob20@gmail.com",
                "0693721131",
                "0812243967",
                "30452"
        );
        assertNotNull(memberContact, "ContactDetails fixture must be built");
    }

    @Test
    @Order(1)
    void a_createMember_succeeds() {
        Long expectedId = 5L;

        Member member = MemberFactory.createMember(
                expectedId, "Jacob_Malik", memberContact, null
        );

        assertNotNull(member, "Factory should create a member for valid input");
        assertEquals(expectedId, member.getMemberId());
        assertEquals("Jacob_Malik", member.getUserName());
        assertSame(memberContact, member.getContactDetails());   // ← relationship check

        // ContactDetails invariants
        assertEquals("jacob20@gmail.com", memberContact.getEmail());
        assertEquals("0693721131",      memberContact.getPhone());
        assertEquals("0812243967",      memberContact.getAltPhone());
        assertEquals("30452",           memberContact.getAddress());
    }

    @Test
    @Order(2)
    void b_memberIdNull_returnsNull() {
        Member member = MemberFactory.createMember(
                null, "Alice", memberContact, null
        );
        assertNull(member, "Factory should reject a null memberId");
    }

    @Test
    @Order(3)
    void c_userNameNull_returnsNull() {
        Member member = MemberFactory.createMember(
                7L, null, memberContact, null
        );
        assertNull(member, "Factory should reject a null userName");
    }

    @Test
    @Order(4)
    void d_userNameBlank_returnsNull() {
        Member member = MemberFactory.createMember(
                8L, "   ", memberContact, null
        );
        assertNull(member, "Factory should reject a blank userName");
    }

    @Test
    @Order(5)
    void e_contactDetailsNull_returnsNull() {
        Member member = MemberFactory.createMember(
                9L, "Alice", null, null
        );
        assertNull(member, "Factory should reject null ContactDetails");
    }
}