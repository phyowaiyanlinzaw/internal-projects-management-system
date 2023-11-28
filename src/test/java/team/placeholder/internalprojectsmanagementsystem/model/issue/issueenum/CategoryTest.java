package team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void testEnumValuesNotNull() {

        for (Category category : Category.values()) {
            assertNotNull(category);
        }
    }

    @Test
    public void testEnumToString() {

        assertEquals("ERROR", Category.ERROR.toString());
        assertEquals("BUG", Category.BUG.toString());
        assertEquals("COMMUNICATION", Category.COMMUNICATION.toString());
        assertEquals("TECHNICAL", Category.TECHNICAL.toString());
        assertEquals("BUDGET", Category.BUDGET.toString());
        assertEquals("DESIGN", Category.DESIGN.toString());
        assertEquals("TESTING", Category.TESTING.toString());
        assertEquals("OTHER", Category.OTHER.toString());
    }



}