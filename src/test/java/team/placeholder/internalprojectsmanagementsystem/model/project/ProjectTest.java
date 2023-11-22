package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.DevelopmentPhase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ProjectTest {
    @Mock
    Project project = mock(Project.class);
    @Mock
    private Review review;
    @Mock
    private Tasks task;
    @Mock
    private SystemOutLine systemOutLine;
    @Mock
    private Deliverable deliverable;
    @Mock
    private Amount amount;
    @Mock
    private Client client;
    @Mock
    private Department department;
    @Mock
    private User user;


    @Test
    public void testProjectFields() {
        // Create a Project instance
        Project project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setBackground("Test Background");
        project.setDuration(10);
        project.setStart_date(System.currentTimeMillis());
        project.setEnd_date(System.currentTimeMillis() + 1000000);
        project.setCurrent_phase(DevelopmentPhase.PLANNING);
        project.setObjective("Test Objective");

        // Verify the fields
        assertEquals(1L, project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals("Test Background", project.getBackground());
        assertEquals(10, project.getDuration());
        // You might want to use a tolerance for comparing timestamps due to potential minor differences
        assertEquals(System.currentTimeMillis(), project.getStart_date(), 1000);
        assertEquals(System.currentTimeMillis() + 1000000, project.getEnd_date(), 1000);
        assertEquals(DevelopmentPhase.PLANNING, project.getCurrent_phase());
        assertEquals("Test Objective", project.getObjective());
    }


    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        long projectId = 1L;
        Project project1 = new Project();
        project1.setId(projectId);

        Project project2 = new Project();
        project2.setId(projectId);

        Project project3 = new Project();
        project3.setId(2L); // Different ID
        Review review = mock(Review.class);
        List<Tasks> tasks = new ArrayList<>();  // You can create and mock Tasks if needed
        SystemOutLine systemOutLine = mock(SystemOutLine.class);
        List<Deliverable> deliverables = new ArrayList<>();  // You can create and mock Deliverables if needed
        Amount amount = mock(Amount.class);

        // Create two Project instances with the same values
        Project project5 = new Project();
        project1.setReviews(review);
        project1.setTasks(tasks);
        project1.setSystemOutLine(systemOutLine);
        project1.setDeliverables(deliverables);
        project1.setClosed(true);
        project1.setAmount(amount);

        Project project6 = new Project();
        project2.setReviews(review);
        project2.setTasks(tasks);
        project2.setSystemOutLine(systemOutLine);
        project2.setDeliverables(deliverables);
        project2.setClosed(true);
        project2.setAmount(amount);

        // Test equals and hashCode methods
        assertTrue(project5.equals(project6) && project6.equals(project5));
        assertEquals(project1.hashCode(), project2.hashCode());

        // Act & Assert
        assertEquals(project1, project2);
        assertEquals(project1.hashCode(), project2.hashCode());

        assertNotEquals(project1, project3);
        assertNotEquals(project1.hashCode(), project3.hashCode());
    }

    @Test
    public void testRelationships() {
        // Arrange
        Architecture architecture = new Architecture();
        client = new Client();
        department = new Department();
        User projectManager = new User();
        User user1 = new User();
        User user2 = new User();

        project = new Project();
        project.getArchitectures().add(architecture);
        project.setClient(client);
        project.setDepartment(department);
        project.setProjectManager(projectManager);
        project.getUsers().add(user1);
        project.getUsers().add(user2);

        // Act & Assert
        assertEquals(1, project.getArchitectures().size());
        assertEquals(client, project.getClient());
        assertEquals(department, project.getDepartment());
        assertEquals(projectManager, project.getProjectManager());
        assertEquals(1, project.getUsers().size());
    }
    @Test
    void test() {
        // Mock dependencies

    }
    @Test
    void testRelationship() {
        // Mock dependencies
        Review review = mock(Review.class);
        List<Tasks> tasks = new ArrayList<>();
        SystemOutLine systemOutLine = mock(SystemOutLine.class);
        List<Deliverable> deliverables = new ArrayList<>();
        Amount amount = mock(Amount.class);

        // Create a Project instance and set relationships
        Project project = new Project();
        project.setReviews(review);
        project.setTasks(tasks);
        project.setSystemOutLine(systemOutLine);
        project.setDeliverables(deliverables);
        project.setClosed(true);
        project.setAmount(amount);

        // Test relationships
        assertEquals(review, project.getReviews());
        assertEquals(tasks, project.getTasks());
        assertEquals(systemOutLine, project.getSystemOutLine());
        assertEquals(deliverables, project.getDeliverables());
        assertTrue(project.isClosed());
        assertEquals(amount, project.getAmount());
    }
}



