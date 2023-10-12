package team.placeholder.internalprojectsmanagementsystem.model.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TasksTest {

    @InjectMocks
    private Tasks tasks;

    @Mock
    private Project project;

    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGettersAndSetters() {
        long id = 1l;
        String title = "Task Title";
        String description = "Task Description";
        String status = "In Progress";
        Date startTime = new Date(System.currentTimeMillis());
        Date endTime = new Date(System.currentTimeMillis() + 3600000); // 1 hour later
        Time expectedMM = new Time(System.currentTimeMillis());
        Time actualMM = new Time(System.currentTimeMillis() + 60000); // 1 minute later

        tasks.setId(id);
        tasks.setTitle(title);
        tasks.setDescription(description);
        tasks.setStatus(status);
        tasks.setStart_time(startTime);
        tasks.setEnd_time(endTime);
        tasks.setExcepted_mm(expectedMM);
        tasks.setActual_mm(actualMM);

        assertEquals(id, tasks.getId());
        assertEquals(title, tasks.getTitle());
        assertEquals(description, tasks.getDescription());
        assertEquals(status, tasks.getStatus());
        assertEquals(startTime, tasks.getStart_time());
        assertEquals(endTime, tasks.getEnd_time());
        assertEquals(expectedMM, tasks.getExcepted_mm());
        assertEquals(actualMM, tasks.getActual_mm());
    }

    @Test
    public void testId() {
        long taskId = 1L;
        tasks.setId(taskId);
        assertEquals(taskId, tasks.getId());
    }

    @Test
    public void testTasksIdEquality() {
        // Create two mock Tasks instances with the same id
        Tasks task1 = Mockito.mock(Tasks.class);
        Mockito.when(task1.getId()).thenReturn(1L);

        Tasks task2 = Mockito.mock(Tasks.class);
        Mockito.when(task2.getId()).thenReturn(1L);

        // Check if the ids are equal
        assertEquals(task1.getId(), task2.getId());
    }

    @Test
    public void testTasksIdInequality() {
        // Create two mock Tasks instances with different ids
        Tasks task1 = Mockito.mock(Tasks.class);
        Mockito.when(task1.getId()).thenReturn(1L);

        Tasks task2 = Mockito.mock(Tasks.class);
        Mockito.when(task2.getId()).thenReturn(2L);

        // Check if the ids are not equal
        assertNotEquals(task1.getId(), task2.getId());
    }


    @Test
    public void testEquals() {
        Tasks task1 = new Tasks();
        task1.setId(1L);

        Tasks task2 = new Tasks();
        task2.setId(1L);

        assertEquals(task1, task2);
    }

    @Test
    public void testNotEquals() {
        Tasks task1 = new Tasks();
        task1.setId(1L);

        Tasks task2 = new Tasks();
        task2.setId(2L);

        assertNotEquals(task1, task2);
    }

    @Test
    public void testHashCode() {
        Tasks task1 = new Tasks();
        task1.setId(1L);

        Tasks task2 = new Tasks();
        task2.setId(1L);

        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testProjectAndUser() {
        when(project.getId()).thenReturn(1L);
        when(user.getId()).thenReturn(2L);

        tasks.setProject(project);
        tasks.setUser(user);

        assertEquals(1L, tasks.getProject().getId());
        assertEquals(2L, tasks.getUser().getId());
    }

    @Test
    public void testNotifications() {
        Notification notification1 = new Notification();
        notification1.setId(1L);

        Notification notification2 = new Notification();
        notification2.setId(2L);

        Set<Notification> notifications = new HashSet<>();
        notifications.add(notification1);
        notifications.add(notification2);

        tasks.setNotifications(notifications);

        assertTrue(tasks.getNotifications().contains(notification1));
        assertTrue(tasks.getNotifications().contains(notification2));
    }



    @Test
    public void testAddAndRemoveNotification() {
        Notification notification1 = new Notification();
        notification1.setId(1L);

        Notification notification2 = new Notification();
        notification2.setId(2L);

        // Add notification1
        tasks.addNotification(notification1);

        // Verify that notification1 was added and is associated with tasks
        assertTrue(tasks.getNotifications().contains(notification1));
        assertTrue(notification1.getTasks().contains(tasks));

        // Verify that notification2 was not added and is not associated with tasks
        assertFalse(tasks.getNotifications().contains(notification2));
        assertFalse(notification2.getTasks().contains(tasks));

        // Add notification2
        tasks.addNotification(notification2);

        // Verify that notification2 was added and is associated with tasks
        assertTrue(tasks.getNotifications().contains(notification2));
        assertTrue(notification2.getTasks().contains(tasks));

        // Remove notification1
        tasks.removeNotification(notification1);

        // Verify that notification1 was removed and is not associated with tasks
        assertFalse(tasks.getNotifications().contains(notification1));
        assertFalse(notification1.getTasks().contains(tasks));

        // Verify that notification2 is still added and associated with tasks
        assertTrue(tasks.getNotifications().contains(notification2));
        assertTrue(notification2.getTasks().contains(tasks));
    }
}
