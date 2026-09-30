package za.ac.factory;

import za.ac.domain.ContactDetails;
import za.ac.domain.Donation;
import za.ac.domain.Member;
import za.ac.util.Helper;

import java.util.List;

public class MemberFactory {
    public static Member createMember(String memberId, String userName,
                                      ContactDetails contact, List<Donation> donations){

        if(Helper.isNullOrEmpty(memberId)
                || Helper.isNullOrEmpty(userName)){
            return null;
        }

        return new Member.Builder()
                .setMemberId(memberId)
                .setUserName(userName)
                .setContactDetails(contact)
                .setDonation(donations)
                .build();
    }
}
