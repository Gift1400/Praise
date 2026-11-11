package za.ac.factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.domain.OutreachProgram;
import za.ac.factory.OutreachProgramFactory;

import static org.junit.jupiter.api.Assertions.*;

class OutreachProgramFactoryTest {

    @Test
    void testCreateOutreachProgramValid() {
        OutreachProgram program = OutreachProgramFactory.createOutreachProgram(
                "OP001",
                "Community Feeding Scheme",
                "Weekly food distribution to communities in need"
        );

        assertNotNull(program);
        assertEquals("OP001", program.getProgramId());
        assertEquals("Community Feeding Scheme", program.getProgramName());
        assertEquals("Weekly food distribution to communities in need", program.getDescription());
    }

    @Test
    void testCreateOutreachProgramEmptyName() {
        OutreachProgram program = OutreachProgramFactory.createOutreachProgram(
                "OP001",
                "",
                "Weekly food distribution to communities in need"
        );
        assertNull(program);
    }

    @Test
    void testCreateOutreachProgramNullName() {
        OutreachProgram program = OutreachProgramFactory.createOutreachProgram(
                "OP001",
                null,
                "Weekly food distribution to communities in need"
        );
        assertNull(program);
    }

    @Test
    void testCreateOutreachProgramNullId() {
        OutreachProgram program = OutreachProgramFactory.createOutreachProgram(
                null,
                "Community Feeding Scheme",
                "Weekly food distribution to communities in need"
        );
        assertNull(program);
    }

    @Test
    void testCreateOutreachProgramEmptyId() {
        OutreachProgram program = OutreachProgramFactory.createOutreachProgram(
                "",
                "Community Feeding Scheme",
                "Weekly food distribution to communities in need"
        );
        assertNull(program);
    }

    @Test
    void testCreateOutreachProgramEmptyDescription() {
        OutreachProgram program = OutreachProgramFactory.createOutreachProgram(
                "OP001",
                "Community Feeding Scheme",
                ""
        );
        assertNull(program);
    }
}