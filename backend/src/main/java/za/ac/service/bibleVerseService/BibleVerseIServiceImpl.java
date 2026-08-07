package za.ac.service.bibleVerseService;

import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;

import java.util.Set;

public class BibleVerseIServiceImpl implements IBibleVerse{

    @Override
    public BibleVerse create(BibleVerse bibleVerse) {
        return null;
    }

    @Override
    public BibleVerse read(String s) {
        return null;
    }

    @Override
    public BibleVerse update(BibleVerse bibleVerse) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Set<BibleVerse> getAll() {
        return Set.of();
    }

    @Override
    public Set<BibleVerse> getVerseBySituation(LifeSituation situation) {
        return Set.of();
    }

}
