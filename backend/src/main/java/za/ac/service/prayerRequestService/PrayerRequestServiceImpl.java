package za.ac.service.prayerRequestService;

import za.ac.domain.PrayerRequest;

import java.util.Set;

public class PrayerRequestServiceImpl implements IPrayerRequest{

    @Override
    public PrayerRequest create(PrayerRequest prayerRequest) {
        return null;
    }

    @Override
    public PrayerRequest read(String s) {
        return null;
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
    public Set<PrayerRequest> getAll() {
        return Set.of();
    }

    @Override
    public Set<PrayerRequest> getPublicPrayerRequest() {
        return Set.of();
    }

    @Override
    public Set<PrayerRequest> getPrayerRequestByMember(String memberId) {
        return Set.of();
    }

}
