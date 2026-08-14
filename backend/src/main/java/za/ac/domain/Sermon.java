package za.ac.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Sermon")
public class Sermon {
    @Id
    private String sermonId;
    private String title;
    private String description;
    private LocalDate date;

    protected Sermon(){}

    public Sermon(Builder builder){
        this.sermonId = builder.sermonId;
        this.title = builder.title;
        this.description = builder.description;
        this.date = builder.date;
    }

    public String getSermonId(){ return sermonId;}
    public String getTitle(){ return title;}
    public String getDescription(){ return description;}
    public LocalDate getDate(){ return date;}

    public String toString(){
        return "Sermon{ " + "\n" +
                "Sermon Id: " + sermonId + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Date: " + date + "}";
    }

    public static class Builder{
        private String sermonId;
        private String title;
        private String description;
        private LocalDate date;

        public Builder copy(Sermon sermon){
            this.sermonId = sermon.sermonId;
            this.title = sermon.title;
            this.description = sermon.description;
            return this;
        }

        public Builder setSermonId(String sermonId){
            this.sermonId = sermonId;
            return this;
        }
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }
        public Builder setDescription(String description){
            this.description = description;
            return this;
        }
        public Builder setLocalDate(LocalDate date){
            this.date = date;
            return this;
        }

        public Sermon build(){
            return new Sermon(this);
        }
    }
}
