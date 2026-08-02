package za.ac.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prayerRequest")
public class PrayerRequest {
    @Id
    private String requestId;
    private String content;
    private boolean isPrivate;
    private LocalDate date;

    protected PrayerRequest(){}

    public PrayerRequest(Builder builder){
        this.requestId = builder.requestId;
        this.content = builder.content;
        this.isPrivate = builder.isPrivate;
        this.date = builder.date;
    }

    public String getRequestId(){ return requestId;}
    public String getContent(){ return content; }
    public Boolean getIsPrivate(){ return isPrivate; }
    public LocalDate getDate(){ return date;}

    public String toString(){
        return "Prayer Request{ " + "\n" +
                "Request Id: " + requestId + "\n" +
                "Content: " + content + "\n" +
                "isPrivate: " + isPrivate + "\n" +
                "Date: " + date + "}";
    }

    public static class Builder{
        private String requestId;
        private String content;
        private boolean isPrivate;
        private LocalDate date;

        public Builder copy(PrayerRequest prayerRequest){
            this.requestId = prayerRequest.requestId;
            this.content = prayerRequest.content;
            this.isPrivate = prayerRequest.isPrivate;
            this.date = prayerRequest.date;
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

        public PrayerRequest build(){
            return new PrayerRequest(this);
        }
    }

}
