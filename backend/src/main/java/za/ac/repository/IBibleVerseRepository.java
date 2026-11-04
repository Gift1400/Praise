package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import java.util.*;

@Repository
public interface IBibleVerseRepository extends JpaRepository<BibleVerse, String> {
    List<BibleVerse> getVerseByLiseSituation(LifeSituation situation);
}
