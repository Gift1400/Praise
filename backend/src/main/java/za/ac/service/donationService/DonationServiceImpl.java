package za.ac.service.donationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.Donation;
import za.ac.repository.IDonationRepository;
import java.util.*;

@Service
public class DonationServiceImpl implements IDonationService {

    public final IDonationRepository repository;

    @Autowired
    public DonationServiceImpl(IDonationRepository donationRepository) {
        this.repository = donationRepository;
    }

    @Override
    public Donation create(Donation donation) {
        return repository.save(donation);
    }

    @Override
    public Donation read(Integer donationId) {
        return repository.findById(donationId).orElse(null);
    }

    @Override
    public Donation update(Donation donation) {
        return repository.save(donation);
    }

    @Override
    public boolean delete(Integer donationId) {
        if(repository.existsById(donationId)){
            repository.deleteById(donationId);
            return true;
        };
        return false;
    }

    @Override
    public List<Donation> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Donation> getDonationsByMember(String memberId) {
        if(memberId == null){
            return Collections.emptyList();
        }
        return repository.getDonationsByMember(memberId);
    }

    @Override
    public double getTotalDonations() {
        Double total = repository.sumAllDonations();
        return total != null ? total : 0.0;
    }
}
