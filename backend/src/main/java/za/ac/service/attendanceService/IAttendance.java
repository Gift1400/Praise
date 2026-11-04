package za.ac.service.attendanceService;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Attendance;
import za.ac.service.IService;

import java.util.*;

public interface IAttendance extends IService<Attendance, String> {
    List<Attendance> getAll();
    List<Attendance> getByMemberId(String memberId);

}
