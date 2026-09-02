package za.ac.factoryTest;

import org.junit.jupiter.api.*;
import za.ac.domain.BibleVerse;
import za.ac.domain.Enums.LifeSituation;
import za.ac.factory.BibleVerseFactory;
import static org.junit.jupiter.api.Assertions.*;

class BibleVerseFactoryTest {

    BibleVerse verse;
    LifeSituation situation;

    @BeforeEach
    void setUp() {
        situation = LifeSituation.CONFUSION;
        verse = BibleVerseFactory.createBibleVerse("J25",
                "Jesus Christ is our lord and savior",
                "James", 2, 2, situation);
    }

    @Test
    void a_createBibleVerse(){
        assertNotNull(verse);
        assertNotNull(situation);
        assertEquals("J25", verse.getVerseId());
        assertEquals("Jesus Christ is our lord and savior", verse.getText());
        assertEquals("James", verse.getBook());
        assertEquals(2, verse.getChapter());
        assertEquals(2, verse.getVerseNumber());


    }


}