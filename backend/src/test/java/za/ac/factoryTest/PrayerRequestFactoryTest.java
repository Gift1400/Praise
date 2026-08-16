package za.ac.factoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.domain.PrayerRequest;
import za.ac.factory.PrayerRequestFactory;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PrayerRequestFactoryTest {

    PrayerRequest prayerRequest;
    LocalDate date = LocalDate.of(2026, 4, 13);

    @BeforeEach
    void setUp() {
        prayerRequest = PrayerRequestFactory.createPrayerRequest("PR001",null, true,date);
    }

    @Test
    void createPrayerRequest() {
        assertNotNull(prayerRequest);
        assertEquals("PR001", prayerRequest.getRequestId());
        assertNull(null, prayerRequest.getContent());
        assertEquals(true, prayerRequest.getIsPrivate());
        assertEquals(date, prayerRequest.getDate());
    }

    @Test
    void publicPrayerRequest(){
        
    }
}