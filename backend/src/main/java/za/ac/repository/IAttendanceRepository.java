package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Attendance;

import java.util.*;

public interface IAttendanceRepository extends JpaRepository<Attendance, String> {
    @Query("SELECT a FROM Attendance a WHERE a.member.memberId = :memberId")
    List<Attendance> getByMemberId(@Param("memberId") String memberId);
}
