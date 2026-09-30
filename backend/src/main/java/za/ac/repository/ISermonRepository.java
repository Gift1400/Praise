package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Sermon;

import java.util.List;

public interface ISermonRepository extends JpaRepository<Sermon, String> {
    @Query("SELECT s FROM Sermon s WHERE s.leader.leaderId = :leaderId")
    List<Sermon> getSermonsByLeader(@Param("leaderId") String leaderId);
}
