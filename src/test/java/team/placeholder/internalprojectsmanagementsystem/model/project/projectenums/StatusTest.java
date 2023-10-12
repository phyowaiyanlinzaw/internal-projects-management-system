package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    public void testEnumValues() {
        for (Status value : Status.values()) {
            assertNotNull(value);
        }
    }

    @Test
    public void testEnumOrder() {
        // Test the order of enum values
        Status[] values = Status.values();
        assertEquals(Status.STARTED, values[0]);
        assertEquals(Status.IN_PROGRESS, values[1]);
        assertEquals(Status.FINISHED, values[2]);
    }

    @Test
    public void testGetPhaseName() {
        // Arrange
        Status enumInstance = Status.FINISHED; // Replace with your actual enum value

        // Act
        String phaseName = enumInstance.getStatus_Name();

        // Assert
        assertEquals("FINISHED", phaseName); // Verify that the method returns the correct name
    }

}