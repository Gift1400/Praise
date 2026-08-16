package za.ac.factory;

import za.ac.domain.ChurchSite;
import za.ac.domain.ContactDetails;
import za.ac.util.Helper;

public class ChurchSiteFactory {
    public static ChurchSite createChurchSite(String churchSiteId, String churchName, ContactDetails contactDetails){

        if(Helper.isNullOrEmpty(churchSiteId) ||
        Helper.isNullOrEmpty(churchName)){
            return null;
        }

        return new ChurchSite.Builder()
                .setSiteId(churchSiteId)
                .setChurchName(churchName)
                .setContactDetails(contactDetails)
                .build();
    }
}
