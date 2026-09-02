package za.ac.factory;

import za.ac.domain.Rsvp;
import za.ac.util.Helper;

public class RsvpFactory {
    public static Rsvp createRsvp(String rsvpId, String status){

        if(Helper.isNullOrEmpty(rsvpId) ||
        Helper.isNullOrEmpty(status)){
            return null;
        }

        return new Rsvp.Builder()
                .setRsvpId(rsvpId)
                .setStatus(status)
                .build();
    }
}
