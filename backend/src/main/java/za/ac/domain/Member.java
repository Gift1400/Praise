package za.ac.domain;


import jakarta.persistence.*;
import java.util.*;
import za.ac.domain.ContactDetails;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "memberId")
    private String memberId;
    private String userName;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "contactDetailsId")
    private ContactDetails contactDetails;

    @OneToMany(mappedBy = "member")
    private List<Donation> donations;

    protected Member(){}

    public Member(Builder builder){
        this.memberId = builder.memberId;
        this.userName = builder.userName;
        this.contactDetails = builder.contactDetails;
        this.donations = builder.donations;
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
    public List<Donation> getDonations(){ return donations; }


    public String toString(){
        return "Member ID: " + memberId + "\n" +
                "Username: " + userName + "\n" +
                "Contact Details{ " + contactDetails + "\n" +
                "Donation: " + donations +"}" ;
    }

    public static class Builder{
        private String memberId;
        private String userName;
        private ContactDetails contactDetails;
        private List<Donation> donations;

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
        public Builder setDonation(List<Donation> donations){
            this.donations = donations;
            return this;
        }

        public Builder copy(Member member){
            this.memberId = member.memberId;
            this.userName = member.userName;
            this.contactDetails = member.contactDetails;
            this.donations = member.donations;
            return this;
        }

        public Member build(){
            return new Member(this);
        }
    }
}
