package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import java.util.*;

public interface IBibleVerseRepository extends JpaRepository<BibleVerse, String> {
    List<BibleVerse> getVerseBySituation(LifeSituation situation);
}
