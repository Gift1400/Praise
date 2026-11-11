package za.ac.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prayer_request")
public class PrayerRequest {
    @Id
    @Column(name = "prayer_request_id")
    private String requestId;
    private String content;
    private boolean isPrivate;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    protected PrayerRequest(){}

    public PrayerRequest(Builder builder){
        this.requestId = builder.requestId;
        this.content = builder.content;
        this.isPrivate = builder.isPrivate;
        this.date = builder.date;
        this.member = builder.member;
    }

    public String getRequestId(){ return requestId;}
    public String getContent(){ return content; }
    public Boolean getIsPrivate(){ return isPrivate; }
    public LocalDate getDate(){ return date;}
    public Member getMember(){ return member;}

    public String toString(){
        return "Prayer Request{ " + "\n" +
                "Request Id: " + requestId + "\n" +
                "Content: " + content + "\n" +
                "isPrivate: " + isPrivate + "\n" +
                "Date: " + date + "\n" +
                "Member: " + member + "}";
    }

    public static class Builder{
        private String requestId;
        private String content;
        private boolean isPrivate;
        private LocalDate date;
        private Member member;

        public Builder copy(PrayerRequest prayerRequest){
            this.requestId = prayerRequest.requestId;
            this.content = prayerRequest.content;
            this.isPrivate = prayerRequest.isPrivate;
            this.date = prayerRequest.date;
            this.member = prayerRequest.member;
            return this;
        }

        public Builder setRequestId(String requestId){
            this.requestId = requestId;
            return this;
        }
        public Builder setContent(String content){
            this.content = content;
            return this;
        }
        public Builder setIsPrivate(Boolean isPrivate){
            this.isPrivate = isPrivate;
            return this;
        }
        public Builder setLocalDate(LocalDate date){
            this.date = date;
            return this;
        }
        public Builder setMember(Member member){
            this.member = member;
            return this;
        }

        public PrayerRequest build(){
            return new PrayerRequest(this);
        }
    }

}
