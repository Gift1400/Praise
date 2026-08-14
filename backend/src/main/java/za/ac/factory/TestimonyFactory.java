package za.ac.factory;

import za.ac.domain.Testimony;
import za.ac.util.Helper;
import java.time.LocalDate;

public class TestimonyFactory {
    public static Testimony createTestimony(String testimonyId, String content, LocalDate date){

        if(Helper.isNullOrEmpty(testimonyId) ||
                Helper.isNullOrEmpty(content)){
            return null;
        }

        return new Testimony.Builder()
                .setTestimonyId(testimonyId)
                .setContent(content)
                .setDate(date)
                .build();
    }
}
