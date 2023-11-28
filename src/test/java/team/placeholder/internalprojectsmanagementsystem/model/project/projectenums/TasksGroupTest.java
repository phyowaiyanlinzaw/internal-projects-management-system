package team.placeholder.internalprojectsmanagementsystem.model.project.projectenums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksGroupTest {
    @Test
    public void testEnumOrder() {
        // Test the order of enum values
        TasksGroup[] values = TasksGroup.values();
        assertEquals(TasksGroup.A, values[0]);
        assertEquals(TasksGroup.B, values[1]);
        assertEquals(TasksGroup.C, values[2]);
    }
    @Test
    public void testEnumValues() {
        // Arrange
        TasksGroup a = TasksGroup.A;
        TasksGroup b = TasksGroup.B;
        TasksGroup c = TasksGroup.C;

        // Act & Assert
        assertEquals("A", a.name());
        assertEquals("B", b.name());
        assertEquals("C", c.name());

        // You can also use assertEquals with the enum values directly
        assertEquals(TasksGroup.A, TasksGroup.valueOf("A"));
        assertEquals(TasksGroup.B, TasksGroup.valueOf("B"));
        assertEquals(TasksGroup.C, TasksGroup.valueOf("C"));

        // Add more tests as needed
    }
}
