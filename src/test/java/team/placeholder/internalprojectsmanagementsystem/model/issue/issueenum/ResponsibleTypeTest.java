package team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsibleTypeTest {
    @Test
    public void testEnumValuesNotNull() {
        // Test that all values of the enum are not null
        for (ResponsibleType type : ResponsibleType.values()) {
            assertNotNull(type);
        }
    }

    @Test
    public void testEnumToString() {
        // Test the toString method of the enum
        assertEquals("CLIENT", ResponsibleType.CLIENT.toString());
        assertEquals("EMPLOYEE", ResponsibleType.EMPLOYEE.toString());
    }

}