package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Donation;
public interface IDonationRepository extends JpaRepository<Donation, Integer> {
    // Spring Data JPA will auto-generate CRUD methods
}