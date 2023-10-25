package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {

    @Test
    public void testEnumValues() {
        for (TaskStatus value : TaskStatus.values()) {
            assertNotNull(value);
        }
    }

    @Test
    public void testEnumOrder() {
        // Test the order of enum values
        TaskStatus[] values = TaskStatus.values();
        assertEquals(TaskStatus.STARTED, values[0]);
        assertEquals(TaskStatus.IN_PROGRESS, values[1]);
        assertEquals(TaskStatus.FINISHED, values[2]);
    }

    @Test
    public void testGetPhaseName() {
        // Arrange
        TaskStatus enumInstance = TaskStatus.FINISHED; // Replace with your actual enum value

        // Act
        String phaseName = enumInstance.getStatus_Name();

        // Assert
        assertEquals("FINISHED", phaseName); // Verify that the method returns the correct name
    }

}