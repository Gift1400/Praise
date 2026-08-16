package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Attendance;

import java.util.*;

public interface IAttendanceRepository extends JpaRepository<Attendance, String> {
    List<Attendance> getByMemberId(String memberId);
}
