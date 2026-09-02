package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.PrayerRequest;

import java.util.List;

public interface IPrayerRequestRepository extends JpaRepository<PrayerRequest, String> {
    List<PrayerRequest> findByIsPrivateFalse();
    List<PrayerRequest> getPrayerRequestByMember(String memberId);
}
