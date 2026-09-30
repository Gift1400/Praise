package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.PrayerRequest;

import java.util.List;

public interface IPrayerRequestRepository extends JpaRepository<PrayerRequest, String> {
    List<PrayerRequest> findByIsPrivateFalse();

    @Query("SELECT p FROM PrayerRequest p WHERE p.member.memberId = :memberId")
    List<PrayerRequest> getPrayerRequestByMember(@Param("memberId") String memberId);
}
