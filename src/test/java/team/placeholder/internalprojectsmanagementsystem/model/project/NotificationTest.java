package team.placeholder.internalprojectsmanagementsystem.model.project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {
    @Mock
    private Tasks tasks;
    @Mock
    private Notification notification;
    @BeforeEach
    public void setUp() {
        notification = new Notification();
    }

    @Test
    public void testEquals() {
        Notification notification1 = new Notification();
        notification1.setId(1);

        Notification notification2 = new Notification();
        notification2.setId(1);

        Notification notification3 = new Notification();
        notification3.setId(2);

        // Test equality
        assertTrue(notification1.equals(notification2));
        assertFalse(notification1.equals(notification3));
    }
    @Test
    public void testId() {
        long id = 1L;
        notification.setId(id);
        assertEquals(id, notification.getId());
    }

    @Test
    public void testHashCode() {
        Notification notification1 = new Notification();
        notification1.setId(1);

        Notification notification2 = new Notification();
        notification2.setId(1);

        // The hash code should be the same for equal objects
        assertEquals(notification1.hashCode(), notification2.hashCode());
    }

    @Test
    public void testManyToManyRelationship() {
        Notification notification = new Notification();
        Tasks task1 = new Tasks();
         Tasks task2 = new Tasks();

        // Add tasks to the notification
        notification.getTasks().add(task1);
        notification.getTasks().add(task2);

        // Test if the tasks are associated with the notification
        assertTrue(notification.getTasks().contains(task1));
        assertTrue(notification.getTasks().contains(task2));
    }

    @Test
    public void testDescription() {
        Notification notification = new Notification();
        notification.setDescription("Test Description");

        // Test if the description is set correctly
        assertEquals("Test Description", notification.getDescription());
    }
}


