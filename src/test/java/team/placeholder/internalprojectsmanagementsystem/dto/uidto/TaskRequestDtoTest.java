package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRequestDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a TaskRequestDto
        TaskRequestDto taskRequestDto = new TaskRequestDto();
        taskRequestDto.setId(1L);
        taskRequestDto.setTitle("Task A");
        taskRequestDto.setDescription("Description for Task A");
        taskRequestDto.setProjectId(2L);
        taskRequestDto.setActualHours(10.5);
        taskRequestDto.setUserId(3L);
        taskRequestDto.setTasksGroup("Group A");
        taskRequestDto.setPlanStartTime(1637000000000L); // Assuming a timestamp in milliseconds
        taskRequestDto.setPlanEndTime(1637100000000L); // Assuming a timestamp in milliseconds
        taskRequestDto.setPlanHours(15.5);
        taskRequestDto.setStatus("In Progress");

        // Verify that the getters return the expected values
        assertEquals(1L, taskRequestDto.getId());
        assertEquals("Task A", taskRequestDto.getTitle());
        assertEquals("Description for Task A", taskRequestDto.getDescription());
        assertEquals(2L, taskRequestDto.getProjectId());
        assertEquals(10.5, taskRequestDto.getActualHours());
        assertEquals(3L, taskRequestDto.getUserId());
        assertEquals("Group A", taskRequestDto.getTasksGroup());
        assertEquals(1637000000000L, taskRequestDto.getPlanStartTime());
        assertEquals(1637100000000L, taskRequestDto.getPlanEndTime());
        assertEquals(15.5, taskRequestDto.getPlanHours());
        assertEquals("In Progress", taskRequestDto.getStatus());

        // Modify some values using setters
        taskRequestDto.setId(2L);
        taskRequestDto.setTitle("Task B");
        taskRequestDto.setDescription("Description for Task B");
        taskRequestDto.setProjectId(4L);
        taskRequestDto.setActualHours(20.0);
        taskRequestDto.setUserId(5L);
        taskRequestDto.setTasksGroup("Group B");
        taskRequestDto.setPlanStartTime(1637200000000L); // Assuming a different timestamp in milliseconds
        taskRequestDto.setPlanEndTime(1637300000000L); // Assuming a different timestamp in milliseconds
        taskRequestDto.setPlanHours(25.0);
        taskRequestDto.setStatus("Completed");

        // Verify that the modified values are reflected
        assertEquals(2L, taskRequestDto.getId());
        assertEquals("Task B", taskRequestDto.getTitle());
        assertEquals("Description for Task B", taskRequestDto.getDescription());
        assertEquals(4L, taskRequestDto.getProjectId());
        assertEquals(20.0, taskRequestDto.getActualHours());
        assertEquals(5L, taskRequestDto.getUserId());
        assertEquals("Group B", taskRequestDto.getTasksGroup());
        assertEquals(1637200000000L, taskRequestDto.getPlanStartTime());
        assertEquals(1637300000000L, taskRequestDto.getPlanEndTime());
        assertEquals(25.0, taskRequestDto.getPlanHours());
        assertEquals("Completed", taskRequestDto.getStatus());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
