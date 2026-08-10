package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.BibleVerse;

public interface IBibleVerseRepository extends JpaRepository<BibleVerse, String> {
}
