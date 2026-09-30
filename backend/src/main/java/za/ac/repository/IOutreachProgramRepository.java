package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.OutreachProgram;

import java.util.List;

public interface IOutreachProgramRepository extends JpaRepository<OutreachProgram, String> {
    @Query("SELECT o FROM OutreachProgram o JOIN o.members m WHERE m.memberId = :memberId")
    List<OutreachProgram> getProgramsByMember(@Param("memberId") String memberId);

    @Query("SELECT o FROM OutreachProgram o WHERE o.leader.leaderId = :leaderId")
    List<OutreachProgram> getProgramsByLeader(@Param("leaderId") String leaderId);
}
