package za.ac.service.attendanceService;

import za.ac.domain.Attendance;
import za.ac.service.IService;

import java.util.Set;

public interface IAttendance extends IService<Attendance, String> {
    Set<Attendance> getAll();
    Set<Attendance> getByMemberId(String memberId);
}
