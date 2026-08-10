package za.ac.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "rsvp")
public class Rsvp {
    @Id
    private String rsvpId;
    private String status;

    protected Rsvp(){}

    public Rsvp(Builder builder){
        this.rsvpId = builder.rsvpId;
        this.status = builder.status;
    }

    public String getRsvpId(){ return rsvpId;}
    public String getStatus(){ return status;}

    public String toString(){
        return "RSVP{ " + "\n" +
                "RSVP Id: " + rsvpId + "\n" +
                "Status: " + status + "}";
    }

    public static class Builder{
        private String rsvpId;
        private String status;

        public Builder copy(Rsvp rsvp){
            this.rsvpId = rsvp.rsvpId;
            this.status = rsvp.status;
            return this;
        }

        public Builder setRsvpId(String rsvpId){
            this.rsvpId = rsvpId;
            return this;
        }
        public Builder setStatus(String status){
            this.status = status;
            return this;
        }

        public Rsvp build(){
            return new Rsvp(this);
        }
    }
}
