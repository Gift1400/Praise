package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.OutreachProgram;

import java.util.List;

public interface IOutreachProgramRepository extends JpaRepository<OutreachProgram, String> {
    List<OutreachProgram> getProgramsByMember(String memberId);
    List<OutreachProgram> getProgramsByLeader(String leaderId);
}
