package factory;

import domain.Member;
import util.Helper;

public class MemberFactory {
    public static Member createMember(String memberId, String userName, String email,
                                      String contactNumber){

        if(Helper.isNullOrEmpty(memberId)
                || Helper.isNullOrEmpty(userName)
                || Helper.isNullOrEmpty(email)
                || Helper.isNullOrEmpty(contactNumber)){
            return null;
        }

        if(!Helper.isValidEmail(email)){
            return null;
        }


        return new Member.Builder()
                .setMemberId(memberId)
                .setUserName(userName)
                .setEmail(email)
                .setContactNumber(contactNumber)
                .build();
    }
}
