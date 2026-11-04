package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.domain.Leader;

import java.util.List;

@Repository
public interface ILeaderRepository extends JpaRepository<Leader, String> {
    List<Leader> getAll();
}
