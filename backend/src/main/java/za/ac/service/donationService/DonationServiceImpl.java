package za.ac.service.donationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Donation;
import za.ac.repository.IDonationRepository;

import java.util.Set;

@Service
public class DonationServiceImpl implements IDonationService {

    public final IDonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(IDonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Donation create(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public Donation read(Integer donationId) {
        return donationRepository.findById(donationId).orElse(null);
    }

    @Override
    public Donation update(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public boolean delete(Integer donationId) {
        if(donationRepository.existsById(donationId)){
            donationRepository.deleteById(donationId);
            return true;
        };
        return false;
    }

    @Override
    public Set<Donation> getAll() {
        return Set.of();
    }

    @Override
    public Set<Donation> getDonationsByMember(String memberId) {
        return Set.of();
    }

    @Override
    public double getTotalDonations() {
        return 0;
    }
}
