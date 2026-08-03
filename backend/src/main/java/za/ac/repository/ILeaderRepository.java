package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Leader;

public interface ILeaderRepository extends JpaRepository<Leader, String> {
}
