package za.ac.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "testimony")
public class Testimony {
    @Id
    @Column(name = "testimony_id")
    private String testimonyId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
    private LocalDate date;

    protected Testimony(){}

    public Testimony(Builder builder){
        this.testimonyId = builder.testimonyId;
        this.member = builder.member;
        this.content = builder.content;
        this.date = builder.date;
    }

    public String getTestimonyId(){ return testimonyId;}
    public Member getMember(){ return member;}
    public String getContent(){ return content;}
    public LocalDate getDate(){ return date;}


    public String toString(){
        return "Testimony{ " + "\n" +
                "Testimony Id: " + testimonyId + "\n" +
                "Member: " + member + "\n" +
                "Content: " + content + "\n" +
                "Date: " + date + "}";
    }

    public static class Builder{
        private String testimonyId;
        private Member member;
        private String content;
        private LocalDate date;

        public Builder copy(Testimony testimony){
            this.testimonyId = testimony.testimonyId;
            this.member = testimony.member;
            this.content = testimony.content;
            this.date = testimony.date;
            return this;
        }

        public Builder setTestimonyId(String testimonyId){
            this.testimonyId = testimonyId;
            return this;
        }
        public Builder setMember(Member member){
            this.member = member;
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
