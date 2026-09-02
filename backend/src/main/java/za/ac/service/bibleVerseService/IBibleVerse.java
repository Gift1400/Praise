package za.ac.service.bibleVerseService;

import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import za.ac.service.IService;

import java.util.*;

public interface IBibleVerse extends IService <BibleVerse, String>{
    List<BibleVerse> getAll();
    List<BibleVerse> getVerseBySituation(LifeSituation situation);
}
