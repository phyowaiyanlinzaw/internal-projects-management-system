package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TasksGroup;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {
    @Mock
    private TaskStatus status;
    @Mock
    private TasksGroup tasksGroup;
    @Mock
    private User user;
    @Mock
    private Project project;
    @Test
    public void testTasksConstruction() {
        // Arrange
        long taskId = 1;
        status = TaskStatus.TODO;
        String description = "Test Task";
        String title = "Task Title";
        long planStartTime = System.currentTimeMillis();
        long planEndTime = System.currentTimeMillis() + 3600000; // One hour later
        Double planHours = 3.5;
        long actualStartTime = System.currentTimeMillis();
        long actualEndTime = System.currentTimeMillis() + 7200000; // Two hours later
        Double actualHours = 2.5;
        boolean due = true;
        project = new Project();
        tasksGroup = TasksGroup.A;
        User user = new User();

        // Act
        Tasks tasks = new Tasks();
        tasks.setId(taskId);
        tasks.setStatus(status);
        tasks.setDescription(description);
        tasks.setTitle(title);
        tasks.setPlan_start_time(planStartTime);
        tasks.setPlan_end_time(planEndTime);
        tasks.setPlan_hours(planHours);
        tasks.setActual_start_time(actualStartTime);
        tasks.setActual_end_time(actualEndTime);
        tasks.setActual_hours(actualHours);
        tasks.setDue(due);
        tasks.setProject(project);
        tasks.setTasksGroup(tasksGroup);
        tasks.setUser(user);
        tasks.setDeleted(false);

        // Assert
        assertEquals(taskId, tasks.getId());
        assertEquals(status, tasks.getStatus());
        assertEquals(description, tasks.getDescription());
        assertEquals(title, tasks.getTitle());
        assertEquals(planStartTime, tasks.getPlan_start_time());
        assertEquals(planEndTime, tasks.getPlan_end_time());
        assertEquals(planHours, tasks.getPlan_hours());
        assertEquals(actualStartTime, tasks.getActual_start_time());
        assertEquals(actualEndTime, tasks.getActual_end_time());
        assertEquals(actualHours, tasks.getActual_hours());
        assertTrue(tasks.isDue());
        assertEquals(project, tasks.getProject());
        assertEquals(tasksGroup, tasks.getTasksGroup());
        assertEquals(user, tasks.getUser());
        assertFalse(tasks.isDeleted());
    }
}

