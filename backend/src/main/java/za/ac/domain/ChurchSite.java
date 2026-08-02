package za.ac.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "churchSite")
public class ChurchSite {
    @Id
    private String siteId;
    private String churchName;

    @Embedded
    private ContactDetails contactDetails;

    protected ChurchSite(){}
    public ChurchSite(Builder builder){
        this.siteId = builder.siteId;
        this.churchName = builder.churchName;
        this.contactDetails = builder.contactDetails;
    }

    public String getSiteId(){ return siteId;}
    public String getChurchName(){ return churchName;}
    public ContactDetails getContactDetails() {return contactDetails;}

    public String toString(){
        return "Church Site {" + "\n" +
                "Site Id: " + siteId + "\n" +
                "Church Name: " + churchName + "\n" +
                "Contact Details: " + contactDetails + "}";
    }

    public static class Builder{
        private String siteId;
        private String churchName;
        private ContactDetails contactDetails;

        public Builder copy(ChurchSite churchSite){
            this.siteId = churchSite.siteId;
            this.churchName = churchSite.churchName;
            this.contactDetails = churchSite.contactDetails;
            return this;
        }

        public Builder setSiteId(String siteId){
            this.siteId = siteId;
            return this;
        }
        public Builder setChurchName(String churchName){
            this.churchName = churchName;
            return this;
        }
        public Builder setContactDetails(ContactDetails contactDetails){
            this.contactDetails = contactDetails;
            return this;
        }

        public ChurchSite build(){
            return new ChurchSite(this);
        }
    }
}
