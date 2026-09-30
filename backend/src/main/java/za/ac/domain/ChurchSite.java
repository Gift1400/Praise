package za.ac.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "churchSite")
public class ChurchSite {
    @Id
    @Column(name = "churchSiteId")
    private String churchSiteId;
    private String churchName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactDetailsId")
    private ContactDetails contactDetails;

    protected ChurchSite(){}
    public ChurchSite(Builder builder){
        this.churchSiteId = builder.churchSiteId;
        this.churchName = builder.churchName;
        this.contactDetails = builder.contactDetails;
    }

    public String getchurchSiteId(){ return churchSiteId;}
    public String getChurchName(){ return churchName;}
    public ContactDetails getContactDetails() {return contactDetails;}

    public String toString(){
        return "Church Site {" + "\n" +
                "Site Id: " + churchSiteId + "\n" +
                "Church Name: " + churchName + "\n" +
                "Contact Details: " + contactDetails + "}";
    }

    public static class Builder{
        private String churchSiteId;
        private String churchName;
        private ContactDetails contactDetails;

        public Builder copy(ChurchSite churchSite){
            this.churchSiteId = churchSite.churchSiteId;
            this.churchName = churchSite.churchName;
            this.contactDetails = churchSite.contactDetails;
            return this;
        }

        public Builder setChurchSiteId(String siteId){
            this.churchSiteId = siteId;
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
