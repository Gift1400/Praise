package za.ac.service.attendanceService;

import za.ac.domain.Attendance;
import za.ac.service.IService;

import java.util.*;

public interface IAttendance extends IService<Attendance, String> {
    List<Attendance> getAll();
    List<Attendance> getByMemberId(String memberId);
}
