package za.ac.factory;

import za.ac.domain.ContactDetails;
import za.ac.util.Helper;

public class ContactDetailsFactory {
    public static ContactDetails createContactDetails(String contactDetailsId, String email, String phone, String altPhone, String address){

        if(Helper.isNullOrEmpty(contactDetailsId) ||
        Helper.isNullOrEmpty(email) ||
        Helper.isNullOrEmpty(address)){
            return null;
        }

        if(!Helper.isValidNumber(phone) ||
        !Helper.isValidNumber(altPhone)){
            return null;
        }

        return new ContactDetails.Builder()
                .setContactDetailsId(contactDetailsId)
                .setEmail(email)
                .setPhone(phone)
                .setAltPhone(altPhone)
                .setAddress(address)
                .build();
    }

}
