//package team.placeholder.internalprojectsmanagementsystem.model.project;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import team.placeholder.internalprojectsmanagementsystem.model.user.User;
//
//import java.sql.Timestamp;
//import java.sql.Time;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//public class TasksTest {
//    @MockBean
//    private Tasks task;
//    @MockBean
//    private Project project;
//    @MockBean
//    private User user;
//    @Mock
//    private Set<TaskNotification> taskNotifications;
//
//
//
//    @BeforeEach
//    public void setUp() {
//        task = new Tasks();
//        project = Mockito.mock(Project.class);
//        user = Mockito.mock(User.class);
//        taskNotifications = new HashSet<>();
//
//        // Mock data
//        Long id = 1L;
//        String title = "Sample Task";
//        String description = "This is a sample task description";
//        String status = "In Progress";
//        Time expectedMM = Time.valueOf("08:30:00");
//        Time actualMM = Time.valueOf("06:45:00");
//
//        // Set up mock interactions
//        Mockito.when(project.getId()).thenReturn(1L);
//        Mockito.when(user.getId()).thenReturn(2L);
//
//        // Set up the task with mock data
//        task.setId(id);
//        task.setTitle(title);
//        task.setDescription(description);
//        task.setStatus(status);
//        task.setExpected_mm(expectedMM);
//        task.setActual_mm(actualMM);
//        task.setProject(project);
//        task.setUser(user);
//        task.setTaskNotifications(taskNotifications);
//    }
//
//    @Test
//    public void testId() {
//        assertEquals(1L, task.getId());
//    }
//
//    @Test
//    public void testTitle() {
//        assertEquals("Sample Task", task.getTitle());
//    }
//
//    @Test
//    public void testDescription() {
//        assertEquals("This is a sample task description", task.getDescription());
//    }
//
//    @Test
//    public void testStatus() {
//        assertEquals("In Progress", task.getStatus());
//    }
//
//    @Test
//    public void testStartTime() {
//        Timestamp startTime = new Timestamp(System.currentTimeMillis());
//        assertEquals(startTime, task.getStart_time());
//    }
//
//    @Test
//    public void testEndTime() {
//        Timestamp endTime = new Timestamp(System.currentTimeMillis());
//        assertEquals(endTime, task.getEnd_time());
//    }
//
//
//
//    @Test
//    public void testExpectedMM() {
//        Time expectedMM = Time.valueOf("08:30:00");
//        assertEquals(expectedMM, task.getExpected_mm());
//    }
//
//    @Test
//    public void testActualMM() {
//        Time actualMM = Time.valueOf("06:45:00");
//        assertEquals(actualMM, task.getActual_mm());
//    }
//
//    @Test
//    public void testProjectAssociation() {
//        assertEquals(1L, task.getProject().getId());
//    }
//
//    @Test
//    public void testUserAssociation() {
//        assertEquals(2L, task.getUser().getId());
//    }
//
//    @Test
//    public void testNotificationAssociation() {
//        assertEquals(taskNotifications, task.getTaskNotifications());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        Tasks task1 = new Tasks();
//        task1.setId(1L);
//        task1.setTitle("Task 1");
//
//        Tasks task2 = new Tasks();
//        task2.setId(1L);
//        task2.setTitle("Task 2");
//
//        assertTrue(task1.equals(task2));
//        assertTrue(task2.equals(task1));
//        assertEquals(task1.hashCode(), task2.hashCode());
//    }
//
//    @Test
//    public void testNotEquals() {
//        Tasks task1 = new Tasks();
//        task1.setId(1L);
//        task1.setTitle("Task 1");
//
//        Tasks task2 = new Tasks();
//        task2.setId(2L);
//        task2.setTitle("Task 2");
//
//        assertFalse(task1.equals(task2));
//        assertFalse(task2.equals(task1));
//    }
//}
//
