package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TasksDtoTest {
    @Mock
    private TasksDto tasksDto;

    @Test
    public void testTasksDto() {
        // Create a TasksDto object with some mock data
        tasksDto = new TasksDto();
        tasksDto.setId(1L);
        tasksDto.setTitle("Sample Task");
        tasksDto.setDescription("This is a test task.");
        tasksDto.setStatus("In Progress");
        tasksDto.setStart_time(new Date(System.currentTimeMillis()));
        tasksDto.setEnd_time(new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000)); // 2 days later
        tasksDto.setExcepted_mm(new Time(System.currentTimeMillis()));
        tasksDto.setActual_mm(new Time(System.currentTimeMillis()));

        // Test the getter methods
        assertEquals(1L, tasksDto.getId());
        assertEquals("Sample Task", tasksDto.getTitle());
        assertEquals("This is a test task.", tasksDto.getDescription());
        assertEquals("In Progress", tasksDto.getStatus());
        assertNotNull(tasksDto.getStart_time());
        assertNotNull(tasksDto.getEnd_time());
        assertNotNull(tasksDto.getExcepted_mm());
        assertNotNull(tasksDto.getActual_mm());
    }
}

