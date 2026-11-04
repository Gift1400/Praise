package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.domain.Testimony;

import java.util.List;

@Repository
public interface ITestimonyRepository extends JpaRepository<Testimony, String> {
    List<Testimony> getAll();
}
