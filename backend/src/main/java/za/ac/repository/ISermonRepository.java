package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Sermon;

import java.util.List;

public interface ISermonRepository extends JpaRepository<Sermon, String> {
    List<Sermon> getSermonsByLeader(String leaderId);
}
