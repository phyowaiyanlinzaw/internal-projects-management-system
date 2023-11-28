package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TaskNotificationDtoTest {
    // Mock data
    long id = 1L;
    String description = "Sample description";
    long noti_time = System.currentTimeMillis();
    TasksDto task = mock(TasksDto.class);
    @Test
    public void testGetterSetter() {

        // Create a TaskNotificationDto instance
        TaskNotificationDto taskNotificationDto = new TaskNotificationDto();
        taskNotificationDto.setId(id);
        taskNotificationDto.setDescription(description);
        taskNotificationDto.setNoti_time(noti_time);
        taskNotificationDto.setTask(task);

        // Verify the values using getters
        assertEquals(id, taskNotificationDto.getId());
        assertEquals(description, taskNotificationDto.getDescription());
        assertEquals(noti_time, taskNotificationDto.getNoti_time());
        assertEquals(task, taskNotificationDto.getTask());
    }

    @Test
    public void testEqualsAndHashCode() {

        // Create two instances with the same values and constant noti_time
        TaskNotificationDto taskNotificationDto1 = new TaskNotificationDto();
        taskNotificationDto1.setId(id);
        taskNotificationDto1.setDescription(description);
        taskNotificationDto1.setNoti_time(noti_time);
        taskNotificationDto1.setTask(task);

        TaskNotificationDto taskNotificationDto2 = new TaskNotificationDto();
        taskNotificationDto2.setId(id);
        taskNotificationDto2.setDescription(description);
        taskNotificationDto2.setNoti_time(noti_time);
        taskNotificationDto2.setTask(task);

        assertEquals(taskNotificationDto1, taskNotificationDto2);
        assertEquals(taskNotificationDto1.hashCode(), taskNotificationDto2.hashCode());
    }
    @Test
    public void testToString() {
        // Create an instance with known values
        TaskNotificationDto taskNotificationDto = new TaskNotificationDto();
        taskNotificationDto.setId(id);
        taskNotificationDto.setDescription(description);
        taskNotificationDto.setNoti_time(noti_time);
        taskNotificationDto.setTask(task);

        // Check the toString representation
        assertEquals("TaskNotificationDto{id=1, description='Sample description', noti_time=" +
                        taskNotificationDto.getNoti_time() + ", task=" + taskNotificationDto.getTask() + "}",
                taskNotificationDto.toString());
    }

}

