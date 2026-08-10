package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Attendance;

public interface IAttendanceRepository extends JpaRepository<Attendance, String> {
}
