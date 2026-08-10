package za.ac.service.prayerRequestService;

import za.ac.domain.PrayerRequest;
import za.ac.service.IService;
import java.util.*;

public interface IPrayerRequest extends IService <PrayerRequest, String>{
    Set<PrayerRequest> getAll();
    Set<PrayerRequest> getPublicPrayerRequest();
    Set<PrayerRequest> getPrayerRequestByMember(String memberId);
}
