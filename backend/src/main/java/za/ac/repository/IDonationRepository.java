package za.ac.repository;

import org.springframework.data.jpa.repository.Query;
import za.ac.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface IDonationRepository extends JpaRepository<Donation, Integer> {
    List<Donation> getDonationsByMember(String memberId);

    @Query("SELECT SUM(d.amount) FROM Donation d")
    Double sumAllDonations();
}
