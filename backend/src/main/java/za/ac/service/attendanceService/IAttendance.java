package za.ac.service.attendanceService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Attendance;
import za.ac.service.IService;

import java.util.*;

public interface IAttendance extends IService<Attendance, String> {
    List<Attendance> getAll();
    List<Attendance> getByMemberId(String memberId);

    @Query("SELECT a FROM Attendance a WHERE a.event.eventId = :eventId")
    List<Attendance> getByEventId(@Param("eventId") String eventId);
}
