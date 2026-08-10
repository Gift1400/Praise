package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Sermon;

public interface ISermonRepository extends JpaRepository<Sermon, String> {
}
