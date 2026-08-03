package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.Testimony;

public interface ITestimonyRepository extends JpaRepository<Testimony, String> {
}
