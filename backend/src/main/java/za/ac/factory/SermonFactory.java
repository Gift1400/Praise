package za.ac.factory;

import za.ac.domain.Sermon;
import za.ac.util.Helper;

import java.time.LocalDate;

public class SermonFactory {

    public static Sermon createSermon(String sermonId, String title, String description, LocalDate date){

        if(Helper.isNullOrEmpty(sermonId) ||
        Helper.isNullOrEmpty(title) ||
        Helper.isNullOrEmpty(description)){
            return null;
        }

        return new Sermon.Builder()
                .setSermonId(sermonId)
                .setTitle(title)
                .setDescription(description)
                .setLocalDate(date)
                .build();
    }


    private String sermonId;
    private String title;
    private String description;
    private LocalDate date;
}
