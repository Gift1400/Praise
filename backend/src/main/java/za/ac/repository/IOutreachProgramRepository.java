package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.OutreachProgram;

public interface IOutreachProgramRepository extends JpaRepository<OutreachProgram, String> {
}
