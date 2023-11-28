package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TasksGroup;


import static org.junit.jupiter.api.Assertions.*;

public class TasksDtoTest {
    @Mock
    private TasksDto tasksDto;

    @Test
    void testGetterAndSetter() {

        // Arrange
        TasksDto tasksDto = new TasksDto();

        // Set values using setter methods
        tasksDto.setId(1L);
        tasksDto.setStatus(TaskStatus.IN_PROGRESS);
        tasksDto.setTitle("Task1");
        tasksDto.setDescription("Description1");
        tasksDto.setPlanStartTime(System.currentTimeMillis());
        tasksDto.setPlanEndTime(System.currentTimeMillis() + 86400000);

        tasksDto.setActualStartTime(System.currentTimeMillis());
        tasksDto.setActualEndTime(System.currentTimeMillis() + 43200000);

        tasksDto.setPlanHours(8.0);
        tasksDto.setActualHours(6.0);

        tasksDto.setDue(true);

        UserDto userDto = new UserDto();
        tasksDto.setUserDto(userDto);

        tasksDto.setTasksGroup(TasksGroup.A);

        ProjectDto projectDto = new ProjectDto();
        tasksDto.setProjectDto(projectDto);

        // Act
        Long id = tasksDto.getId();
        TaskStatus status = tasksDto.getStatus();
        String title = tasksDto.getTitle();
        String description = tasksDto.getDescription();
        long plan_start_time = tasksDto.getPlanStartTime();
        long plan_end_time = tasksDto.getPlanEndTime();
        long actual_start_time = tasksDto.getActualStartTime();
        long actual_end_time = tasksDto.getActualEndTime();
        Double plan_hours = tasksDto.getPlanHours();
        Double actual_hours = tasksDto.getActualHours();
        boolean due = tasksDto.isDue();
        UserDto retrievedUserDto = tasksDto.getUserDto();
        TasksGroup tasksGroup = tasksDto.getTasksGroup();
        ProjectDto retrievedProjectDto = tasksDto.getProjectDto();

        // Assert
        assertEquals(1L, id);
        assertEquals(TaskStatus.IN_PROGRESS, status);
        assertEquals("Task1", title);
        assertEquals("Description1", description);
        assertTrue(plan_start_time > 0);
        assertTrue(plan_end_time > plan_start_time);
        assertTrue(actual_start_time > 0);
        assertTrue(actual_end_time > actual_start_time);
        assertEquals(8.0, plan_hours, 0.01);
        assertEquals(6.0, actual_hours, 0.01);
        assertTrue(due);
        assertEquals(userDto, retrievedUserDto);
        assertEquals(TasksGroup.A, tasksGroup);
        assertEquals(projectDto, retrievedProjectDto);
    }


}

