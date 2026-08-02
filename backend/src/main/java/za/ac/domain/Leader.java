package za.ac.domain;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "leader")
public class Leader {
    @Id
    private String leaderId;
    private String name;
    private String role;
    private String bio;

    @Embedded
    private ContactDetails contactDetails;

    protected Leader(){
    }

    public Leader(Builder builder){
        this.leaderId = builder.leaderId;
        this.name = builder.name;
        this.role = builder.role;
        this.bio = builder.bio;
        this.contactDetails = builder.contactDetails;
    }
    public String getLeaderId(){
        return leaderId;
    }
    public String getName(){
        return name;
    }
    public String getRole(){
        return role;
    }
    public String getBio(){
        return bio;
    }
    public ContactDetails getContactDetails(){
        return contactDetails;
    }

    public String toString(){
        return "Leader{ " + "\n" +
                "Leader Id: " + leaderId + "\n" +
                "Name: " + name + "\n" +
                "Role: " + role + "\n" +
                "Bio: " + bio + "\n}" +
                "Contact Details: " + contactDetails;
    }

    public static class Builder{
        private String leaderId;
        private String name;
        private String role;
        private String bio;
        private ContactDetails contactDetails;

        public Builder copy(Leader leader){
            this.leaderId = leader.leaderId;
            this.name = leader.name;
            this.role = leader.role;
            this.bio = leader.bio;
            this.contactDetails = leader.contactDetails;
            return this;
        }

        public Builder setLeaderId(String leaderId){
            this.leaderId = leaderId;
            return this;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setRole(String role){
            this.role = role;
            return this;
        }
        public Builder setBio(String bio){
            this.bio = bio;
            return this;
        }
        public Builder setContactDetails(ContactDetails contactDetails){
            this.contactDetails = contactDetails;
            return this;
        }

        public Leader build(){
            return new Leader(this);
        }
    }
}

