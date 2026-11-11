package za.ac.factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.domain.Sermon;
import za.ac.factory.SermonFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SermonFactoryTest {

    @Test
    void testCreateSermonValid() {
        LocalDate date = LocalDate.of(2026, 7, 20);
        Sermon sermon = SermonFactory.createSermon(
                "S001",
                "Walking in Faith",
                "Living every day with complete trust in God",
                date
        );

        assertNotNull(sermon);
        assertEquals("S001", sermon.getSermonId());
        assertEquals("Walking in Faith", sermon.getTitle());
        assertEquals("Living every day with complete trust in God", sermon.getDescription());
        assertEquals(date, sermon.getDate());
    }

    @Test
    void testCreateSermonNullTitle() {
        LocalDate date = LocalDate.of(2026, 7, 20);
        Sermon sermon = SermonFactory.createSermon(
                "S001",
                null,
                "Living every day with complete trust in God",
                date
        );
        assertNull(sermon);
    }

    @Test
    void testCreateSermonEmptyTitle() {
        LocalDate date = LocalDate.of(2026, 7, 20);
        Sermon sermon = SermonFactory.createSermon(
                "S001",
                "",
                "Living every day with complete trust in God",
                date
        );
        assertNull(sermon);
    }

    @Test
    void testCreateSermonNullId() {
        LocalDate date = LocalDate.of(2026, 7, 20);
        Sermon sermon = SermonFactory.createSermon(
                null,
                "Walking in Faith",
                "Living every day with complete trust in God",
                date
        );
        assertNull(sermon);
    }

    @Test
    void testCreateSermonEmptyDescription() {
        LocalDate date = LocalDate.of(2026, 7, 20);
        Sermon sermon = SermonFactory.createSermon(
                "S001",
                "Walking in Faith",
                "",
                date
        );
        assertNull(sermon);
    }
}