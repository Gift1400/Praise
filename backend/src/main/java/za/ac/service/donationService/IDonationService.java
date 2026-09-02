package za.ac.service.donationService;

import za.ac.domain.Donation;
import za.ac.service.IService;
import java.util.*;

public interface IDonationService extends IService<Donation, Integer> {
    List<Donation> getAll();
    List<Donation> getDonationsByMember(String memberId);
    double getTotalDonations();
}
