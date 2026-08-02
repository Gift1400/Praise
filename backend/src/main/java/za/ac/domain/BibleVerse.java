package za.ac.domain;

import jakarta.persistence.*;
import za.ac.domain.Enums.LifeSituation;

@Entity
@Table(name = "bibleVerse")
public class BibleVerse {
    @Id
    private String verseId;
    private String text;
    private String book;
    private int chapter;
    private int verseNumber;

    @Enumerated(EnumType.STRING)
    private LifeSituation lifeSituation;

    protected BibleVerse(){}

    public BibleVerse(Builder builder){
        this.verseId = builder.verseId;
        this.text = builder.text;
        this.book = builder.book;
        this.chapter = builder.chapter;
        this.verseNumber = builder.verseNumber;
        this.lifeSituation = builder.lifeSituation;
    }

    public String getVerseId(){
        return verseId;
    }
    public String getText(){
        return text;
    }
    public String getBook(){
        return book;
    }
    public int getChapter(){
        return chapter;
    }
    public int getVerseNumber(){
        return verseNumber;
    }
    public LifeSituation getLifeSituation(){ return lifeSituation;}

    public String toString(){
        return "Bible Verse{ " + "\n" +
                "Verse ID: " + verseId + "\n" +
                "Text: " + text + "\n" +
                "Book: " + book + "\n" +
                "Chapter: " + chapter + "\n" +
                "Verse Number: " + verseNumber + "\n" +
                "Life Situation: " + lifeSituation;
    }

    public static class Builder{
        private String verseId;
        private String text;
        private String book;
        private int chapter;
        private int verseNumber;
        private LifeSituation lifeSituation;

        public Builder copy(BibleVerse bibleVerse){
            this.verseId = bibleVerse.verseId;
            this.text = bibleVerse.text;
            this.book = bibleVerse.book;
            this.chapter = bibleVerse.chapter;
            this.verseNumber = bibleVerse.verseNumber;
            this.lifeSituation = bibleVerse.lifeSituation;
            return this;
        }

        public Builder setVerseId(String verseId){
            this.verseId = verseId;
            return this;
        }
        public Builder setText(String text){
            this.text = text;
            return this;
        }
        public Builder setBook(String book){
            this.book = book;
            return this;
        }
        public Builder setChapter(int chapter){
            this.chapter = chapter;
            return this;
        }
        public Builder setVerseNumber(int verseNumber){
            this.verseNumber = verseNumber;
            return this;
        }
        public Builder setLifeSituation(LifeSituation lifeSituation){
            this.lifeSituation = lifeSituation;
            return this;
        }

        public BibleVerse build(){
            return new BibleVerse(this);
        }
    }
}
