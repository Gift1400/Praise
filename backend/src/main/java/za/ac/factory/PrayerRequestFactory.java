package za.ac.factory;

import za.ac.domain.PrayerRequest;
import za.ac.util.Helper;

import java.time.LocalDate;

public class PrayerRequestFactory {

    public static PrayerRequest createPrayerRequest(String prayerRequestId, String content, boolean isPrivate, LocalDate date){

        if(Helper.isNullOrEmpty(prayerRequestId) ||
        Helper.isNullOrEmpty(content)){
            return null;
        }

        return new PrayerRequest.Builder()
                .setRequestId(prayerRequestId)
                .setContent(content)
                .setIsPrivate(isPrivate)
                .setLocalDate(date)
                .build();
    }

}
