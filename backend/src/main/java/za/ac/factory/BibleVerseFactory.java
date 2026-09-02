package za.ac.factory;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import za.ac.util.Helper;

public class BibleVerseFactory {
    public static BibleVerse createBibleVerse(String bibleVerseId, String text, String book, int chapter, int verseNumber, LifeSituation situation) {

        if (Helper.isNullOrEmpty(bibleVerseId) ||
                Helper.isNullOrEmpty(text) ||
                Helper.isNullOrEmpty(book)) {
            return null;
        }

        return new BibleVerse.Builder()
                .setVerseId(bibleVerseId)
                .setText(text)
                .setBook(book)
                .setChapter(chapter)
                .setVerseNumber(verseNumber)
                .setLifeSituation(situation)
                .build();
    }
    }
