package za.ac.domain;


import jakarta.persistence.*;

import java.security.PrivateKey;
import za.ac.domain.ContactDetails;

@Entity
@Table(name = "members")
public class Member {
    @Id
    private String memberId;
    private String userName;

    @Embedded
    private ContactDetails contactDetails;

    protected Member(){}

    public Member(Builder builder){
        this.memberId = builder.memberId;
        this.userName = builder.userName;
        this.contactDetails = builder.contactDetails;
    }

    public String getMemberId(){
        return memberId;
    }

    public String getUserName(){
        return userName;
    }

    public ContactDetails getContactDetails(){
        return contactDetails;
    }

    public String toString(){
        return "Member ID: " + memberId + "\n" +
                "Username: " + userName + "\n" +
                "Contact Details{ " + contactDetails + "}" ;
    }

    public static class Builder{
        private String memberId;
        private String userName;
        private ContactDetails contactDetails;

        public Builder copy(Member member){
            this.memberId = member.memberId;
            this.userName = member.userName;
            this.contactDetails = member.contactDetails;
            return this;
        }

        public Builder setMemberId(String memberId){
            this.memberId = memberId;
            return this;
        }

        public Builder setUserName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder setContactDetails(ContactDetails contactDetails){
            this.contactDetails = contactDetails;
            return this;
        }

        public Member build(){
            return new Member(this);
        }
    }
}
