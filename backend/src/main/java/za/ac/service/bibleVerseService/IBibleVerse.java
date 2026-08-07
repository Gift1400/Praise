package za.ac.service.bibleVerseService;

import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import za.ac.service.IService;

import java.util.*;

public interface IBibleVerse extends IService <BibleVerse, String>{
    Set<BibleVerse> getAll();
    Set<BibleVerse> getVerseBySituation(LifeSituation situation);
}
