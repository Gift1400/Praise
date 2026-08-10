package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.PrayerRequest;

public interface IPrayerRequestRepository extends JpaRepository<PrayerRequest, String> {
}
