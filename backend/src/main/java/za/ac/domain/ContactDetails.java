package za.ac.domain;
import jakarta.persistence.*;

@Embeddable
public class ContactDetails {
    @Id
    private String contactDetailsId;
    private String email;
    private String phone;
    private String altPhone;
    private String address;

    public ContactDetails(Builder builder){
        this.contactDetailsId = builder.contactDetailsId;
        this.email = builder.email;
        this.phone = builder.phone;
        this.altPhone = builder.altPhone;
        this.address = builder.address;
    }

    protected ContactDetails() {

    }

    public String getContactDetailsId(){
        return contactDetailsId;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getAltPhone(){
        return altPhone;
    }
    public String getAddress(){
        return address;
    }

    public String toString(){
        return "ContactDetailsId:" + contactDetailsId+ "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Alt Phone: " + altPhone + "\n" +
                "Address: " + address ;
    }

    public static class Builder{
        private String contactDetailsId;
        private String email;
        private String phone;
        private String altPhone;
        private String address;

        public Builder copy(ContactDetails contactDetails){
            this.contactDetailsId = contactDetails.contactDetailsId;
            this.email = contactDetails.email;
            this.phone = contactDetails.phone;
            this.altPhone = contactDetails.altPhone;
            this.address = contactDetails.address;
            return this;
        }

        public Builder setContactDetailsId(String contactDetailsId){
            this.contactDetailsId = contactDetailsId;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder setAltPhone(String altPhone){
            this.altPhone = altPhone;
            return this;
        }
        public Builder setAddress(String address){
            this.address = address;
            return this;
        }

        public ContactDetails build(){
            return new ContactDetails(this);
        }
    }
}
