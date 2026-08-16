package za.ac.factoryTest;

import org.junit.jupiter.api.BeforeEach;
import za.ac.domain.Attendance;
import za.ac.domain.ContactDetails;
import za.ac.domain.Member;
import za.ac.factory.AttendanceFactory;
import za.ac.factory.ContactDetailsFactory;
import za.ac.factory.MemberFactory;
import za.ac.service.attendanceService.AttendanceServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceFactoryTest {

    Attendance attendance;
    LocalDate date;
    Member member;
    ContactDetails contact;

    @BeforeEach
    void setUp() {
        contact = ContactDetailsFactory.createContactDetails("C23T",
                "thabomakhubu@gmail.com", "0738872245", "", "3923");
        date = LocalDate.of(2026, 8, 15);
        member = MemberFactory.createMember("23T", "Thabo1200", contact);
        attendance = AttendanceFactory.createAttendance("14B", date, member);
    }
}