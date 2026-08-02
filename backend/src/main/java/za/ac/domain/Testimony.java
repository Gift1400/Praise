package za.ac.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "testimony")
public class Testimony {
    @Id
    private String testimonyId;
    private String content;
    private LocalDate date;

    protected Testimony(){}

    public Testimony(Builder builder){
        this.testimonyId = builder.testimonyId;
        this.content = builder.content;
        this.date = builder.date;
    }

    public String getTestimonyId(){ return testimonyId;}
    public String getContent(){ return content;}
    public LocalDate getDate(){ return date;}

    public String toString(){
        return "Testimony{ " + "\n" +
                "Testimony Id: " + testimonyId + "\n" +
                "Content: " + content + "\n" +
                "Date: " + date + "}";
    }

    public static class Builder{
        private String testimonyId;
        private String content;
        private LocalDate date;

        public Builder copy(Testimony testimony){
            this.testimonyId = testimony.testimonyId;
            this.content = testimony.content;
            this.date = testimony.date;
            return this;
        }

        public Builder setTestimonyId(String testimonyId){
            this.testimonyId = testimonyId;
            return this;
        }
        public Builder setContent(String content){
            this.content = content;
            return this;
        }
        public Builder setDate(LocalDate date){
            this.date = date;
            return this;
        }

        public Testimony build(){
            return new Testimony(this);
        }
    }
}
