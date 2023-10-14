package team.placeholder.internalprojectsmanagementsystem.model.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.IssueTest;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.Development_phase;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    @Mock
    private Project project;
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
    private ProjectStatus projectStatus;
    @Mock
    private Issue issue;
    @Mock
    private Architecture architecture;
    @Mock
    private Department department;
    @Mock
    private Client client;
    @Mock
    private User user;
    @Mock
    private Set<User> users;

    @Mock
    private Development_phase developmentPhase;

    @BeforeEach
    public void setUp() {
        project = new Project();
        review = new Review();
        task = new Tasks();
        systemOutLine = new SystemOutLine();
        deliverable = new Deliverable();
        amount = new Amount();
        projectStatus = new ProjectStatus();
        issue = new Issue();
        architecture = new Architecture();
        department = new Department();
        client = new Client();
        user = new User();
        users = new HashSet<>();
    }



    @Test
    public void testGettersAndSetters() {
        project.setId(1L);
        project.setName("TestProject");
        project.setBackground("Background");
        project.setDuration(30);
        project.setStart_date(new Date(2023, 1, 1));
        project.setEnd_date(new Date(2023, 2, 1));
        project.setCurrent_phase(Development_phase.TESTING);
        project.setObjective("Objective");

        assertEquals(1L, project.getId());
        assertEquals("TestProject", project.getName());
        assertEquals("Background", project.getBackground());
        assertEquals(30, project.getDuration());
        assertEquals(new Date(2023, 1, 1), project.getStart_date());
        assertEquals(new Date(2023, 2, 1), project.getEnd_date());
        assertEquals(Development_phase.TESTING, project.getCurrent_phase());
        assertEquals("Objective", project.getObjective());
    }

    @Test
    public void testReviewList() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        project.setReviews(reviews);

        assertEquals(reviews, project.getReviews());
    }

    @Test
    public void testTasksList() {
        List<Tasks> tasks = new ArrayList<>();
        tasks.add(task);
        project.setTasks(tasks);

        assertEquals(tasks, project.getTasks());
    }

    @Test
    public void testSystemOutLinesList() {
        List<SystemOutLine> systemOutLines = new ArrayList<>();
        systemOutLines.add(systemOutLine);
        project.setSystemOutLines(systemOutLines);

        assertEquals(systemOutLines, project.getSystemOutLines());
    }

    @Test
    public void testDeliverableList() {
        List<Deliverable> deliverables = new ArrayList<>();
        deliverables.add(deliverable);
        project.setDeliverable(deliverables);

        assertEquals(deliverables, project.getDeliverable());
    }

    @Test
    public void testAmountList() {
        List<Amount> amounts = new ArrayList<>();
        amounts.add(amount);
        project.setAmount(amounts);

        assertEquals(amounts, project.getAmount());
    }

    @Test
    public void testProjectStatusesList() {
        List<ProjectStatus> projectStatuses = new ArrayList<>();
        projectStatuses.add(projectStatus);
        project.setProjectStatuses(projectStatuses);

        assertEquals(projectStatuses, project.getProjectStatuses());
    }

    @Test
    public void testIssuesList() {
        List<Issue> issues = new ArrayList<>();
        issues.add(issue);
        project.setIssues(issues);

        assertEquals(issues, project.getIssues());
    }

    @Test
    public void testArchitecturesList() {
        List<Architecture> architectures = new ArrayList<>();
        architectures.add(architecture);
        project.setArchitectures(architectures);

        assertEquals(architectures, project.getArchitectures());
    }

    @Test
    public void testDepartment() {
        project.setDepartment(department);

        assertEquals(department, project.getDepartment());
    }

    @Test
    public void testClient() {
        project.setClient(client);

        assertEquals(client, project.getClient());
    }

    @Test
    public void testUser() {
        project.setUser(user);

        assertEquals(user, project.getUser());
    }

    @Test
    public void testUsers() {
        users.add(user);
        project.setUsers(users);

        assertEquals(users, project.getUsers());
    }

    @Test
    public void testEqualsAndHashCode() {
        project.setId(1L);
        Project otherProject = new Project();
        otherProject.setId(1L);
        Project differentProject = new Project();
        differentProject.setId(2L);

        assertEquals(project, otherProject);
        assertNotEquals(project, differentProject);

        assertEquals(project.hashCode(), otherProject.hashCode());
        assertNotEquals(project.hashCode(), differentProject.hashCode());
    }
}



