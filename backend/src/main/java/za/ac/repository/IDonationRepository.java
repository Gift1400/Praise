package za.ac.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.ac.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface IDonationRepository extends JpaRepository<Donation, Integer> {
    @Query("SELECT d FROM Donation d WHERE d.member.memberId = :memberId")
    List<Donation> getDonationsByMember(@Param("memberId") String memberId);

    @Query("SELECT SUM(d.amount) FROM Donation d")
    Double sumAllDonations();
}
