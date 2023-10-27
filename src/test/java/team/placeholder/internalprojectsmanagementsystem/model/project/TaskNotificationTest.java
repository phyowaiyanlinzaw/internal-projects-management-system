//package team.placeholder.internalprojectsmanagementsystem.model.project;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TaskNotificationTest {
//    @Mock
//    private Tasks tasks;
//    @Mock
//    private TaskNotification taskNotification;
//    @BeforeEach
//    public void setUp() {
//        taskNotification = new TaskNotification();
//    }
//
//    @Test
//    public void testEquals() {
//        TaskNotification taskNotification1 = new TaskNotification();
//        taskNotification1.setId(1);
//
//        TaskNotification taskNotification2 = new TaskNotification();
//        taskNotification2.setId(1);
//
//        TaskNotification taskNotification3 = new TaskNotification();
//        taskNotification3.setId(2);
//
//        // Test equality
//        assertTrue(taskNotification1.equals(taskNotification2));
//        assertFalse(taskNotification1.equals(taskNotification3));
//    }
//    @Test
//    public void testId() {
//        long id = 1L;
//        taskNotification.setId(id);
//        assertEquals(id, taskNotification.getId());
//    }
//
//    @Test
//    public void testHashCode() {
//        TaskNotification taskNotification1 = new TaskNotification();
//        taskNotification1.setId(1);
//
//        TaskNotification taskNotification2 = new TaskNotification();
//        taskNotification2.setId(1);
//
//        // The hash code should be the same for equal objects
//        assertEquals(taskNotification1.hashCode(), taskNotification2.hashCode());
//    }
//
//    @Test
//    public void testManyToManyRelationship() {
//        TaskNotification taskNotification = new TaskNotification();
//        Tasks task1 = new Tasks();
//         Tasks task2 = new Tasks();
//
//        // Add tasks to the notification
//        taskNotification.getTasks().add(task1);
//        taskNotification.getTasks().add(task2);
//
//        // Test if the tasks are associated with the notification
//        assertTrue(taskNotification.getTasks().contains(task1));
//        assertTrue(taskNotification.getTasks().contains(task2));
//    }
//
//    @Test
//    public void testDescription() {
//        TaskNotification taskNotification = new TaskNotification();
//        taskNotification.setDescription("Test Description");
//
//        // Test if the description is set correctly
//        assertEquals("Test Description", taskNotification.getDescription());
//    }
//}
//
//
