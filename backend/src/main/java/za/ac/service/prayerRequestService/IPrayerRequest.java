package za.ac.service.prayerRequestService;

import za.ac.domain.PrayerRequest;
import za.ac.service.IService;
import java.util.*;

public interface IPrayerRequest extends IService <PrayerRequest, String>{
    List<PrayerRequest> getAll();
    List<PrayerRequest> getPublicPrayerRequest();
    List<PrayerRequest> getPrayerRequestByMember(String memberId);
}
