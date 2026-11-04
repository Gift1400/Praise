package za.ac.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "rsvp")
public class Rsvp {
    @Id
    @Column(name = "rsvp_id")
    private String rsvpId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    protected Rsvp(){}

    public Rsvp(Builder builder){
        this.rsvpId = builder.rsvpId;
        this.status = builder.status;
        this.member = builder.member;
        this.event = builder.event;
    }

    public String getRsvpId(){ return rsvpId;}
    public String getStatus(){ return status;}
    public Member getMember(){ return member;}
    public Event getEvent(){ return event;}

    public String toString(){
        return "RSVP{ " + "\n" +
                "RSVP Id: " + rsvpId + "\n" +
                "Status: " + status +
                "Member: " + member + "\n" +
                "Event: " + event + "\n" +"}";
    }

    public static class Builder{
        private String rsvpId;
        private String status;
        private Member member;
        private Event event;

        public Builder copy(Rsvp rsvp){
            this.rsvpId = rsvp.rsvpId;
            this.status = rsvp.status;
            this.member = rsvp.member;
            this.event = rsvp.event;
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
        public Builder setMember(Member member){
            this.member = member;
            return this;
        }
        public Builder setEvent(Event event){
            this.event = event;
            return this;
        }

        public Rsvp build(){
            return new Rsvp(this);
        }
    }
}
