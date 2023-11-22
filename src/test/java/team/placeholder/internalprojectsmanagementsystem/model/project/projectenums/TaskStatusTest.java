package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {
    @Test
    public void testEnumOrder() {
        // Test the order of enum values
        TaskStatus[] values = TaskStatus.values();
        assertEquals(TaskStatus.TODO, values[0]);
        assertEquals(TaskStatus.IN_PROGRESS, values[1]);
        assertEquals(TaskStatus.FINISHED, values[2]);
    }

    @Test
    public void testEnumValues() {
        // Arrange
        TaskStatus todo = TaskStatus.TODO;
        TaskStatus inProgress = TaskStatus.IN_PROGRESS;
        TaskStatus finished = TaskStatus.FINISHED;

        // Act & Assert
        assertEquals("TODO", todo.name());
        assertEquals("IN_PROGRESS", inProgress.name());
        assertEquals("FINISHED", finished.name());

        // You can also use assertEquals with the enum values directly
        assertEquals(TaskStatus.TODO, TaskStatus.valueOf("TODO"));
        assertEquals(TaskStatus.IN_PROGRESS, TaskStatus.valueOf("IN_PROGRESS"));
        assertEquals(TaskStatus.FINISHED, TaskStatus.valueOf("FINISHED"));

        // Add more tests as needed
    }

}