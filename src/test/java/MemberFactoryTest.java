
import domain.Member;
import factory.MemberFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberFactoryTest {

    Member member;

    @BeforeEach
    void setUp() {
        member = MemberFactory.createMember("8933B", "Jacob_Malik",
                "jacobmalik@gmail.com", "+27 73 458 1902");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createMember(){
        assertNotNull(member);
        assertEquals("8933B", member.getMemberId());
        assertEquals("Jacob_Malik", member.getUserName());
        assertEquals("jacobmalik@gmail.com", member.getEmail());
        assertEquals("+27 73 458 1902", member.getContactNumber());
    }


    @Test
    void memberIdNull(){
        member = MemberFactory.createMember(
                null, "Sipho Mabhena",
                "siphomabhena@gmail.com",
                "+27 69 457 9832");

        assertNull(member, "Member should not exist without memberId");
    }

    @Test
    void userNameNullTest(){
        member = MemberFactory.createMember(
                "D1434",
                null,
                "thembisilesithole@gmail.com",
                "+27 84 561 9900"
        );

        assertNull(member, "Member should have a userName");
    }

    @Test
    void emailNullTest(){
        member = MemberFactory.createMember(
                "D3482",
                "Mark Peterson",
                null,
                "+27 72 221 5432"
        );

        assertNull(member);
    }

}
