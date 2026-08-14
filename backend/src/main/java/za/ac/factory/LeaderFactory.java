package za.ac.factory;

import za.ac.domain.ContactDetails;
import za.ac.domain.Leader;
import za.ac.util.Helper;

public class LeaderFactory {
    public Leader createLeader(String leaderId, String name, String role, String bio, ContactDetails contact){

        if(Helper.isNullOrEmpty(leaderId) ||
        Helper.isNullOrEmpty(name) ||
        Helper.isNullOrEmpty(role) ||
        Helper.isNullOrEmpty(bio)){
            return null;
        }

        return new Leader.Builder()
                .setLeaderId(leaderId)
                .setName(name)
                .setRole(role)
                .setBio(bio)
                .setContactDetails(contact)
                .build();
    }

}
