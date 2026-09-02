package za.ac.service.prayerRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.domain.PrayerRequest;
import za.ac.repository.IPrayerRequestRepository;

import java.util.*;

@Service
public class PrayerRequestServiceImpl implements IPrayerRequest{
    private final IPrayerRequestRepository repository;

    @Autowired
    public PrayerRequestServiceImpl(IPrayerRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public PrayerRequest create(PrayerRequest prayerRequest) {
        return repository.save(prayerRequest);
    }

    @Override
    public PrayerRequest read(String prayerRequestId) {
        return repository.findById(prayerRequestId).orElse(null);
    }

    @Override
    public PrayerRequest update(PrayerRequest prayerRequest) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<PrayerRequest> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PrayerRequest> getPublicPrayerRequest() {
        return repository.findByIsPrivateFalse();
    }

    @Override
    public List<PrayerRequest> getPrayerRequestByMember(String memberId) {
        if(memberId == null){
            return Collections.emptyList();
        }
        return repository.getPrayerRequestByMember(memberId);
    }

}
