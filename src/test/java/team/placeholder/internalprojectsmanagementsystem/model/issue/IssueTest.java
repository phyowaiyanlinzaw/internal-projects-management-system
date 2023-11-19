package team.placeholder.internalprojectsmanagementsystem.model.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IssueTest {
    @Mock
    Issue issue = mock(Issue.class);
    @Mock
    Project project = mock(Project.class);
    @Mock
    User user_uploader = mock(User.class);
    @Mock
    User pic = mock(User.class);
    @BeforeEach
    public void setUp() {
        issue = new Issue();
        project = mock(Project.class);
        user_uploader = mock(User.class);
        pic = mock(User.class);
    }
    @Test
    public void testEquals() {
        Issue other = new Issue();
        other.setId(1L);

        issue.setId(1L);

        // Mocking relationships
        issue.setProject(project);
        other.setProject(project);

        issue.setUser_uploader(user_uploader);
        other.setUser_uploader(user_uploader);

        issue.setPic(pic);
        other.setPic(pic);

        assertTrue(issue.equals(other));
    }



    @Test
    public void testHashCode() {
        int hashCode1 = issue.hashCode();
        int hashCode2 = issue.hashCode();
        assertEquals(hashCode1, hashCode2);

        // Mocking relationships
        Project mockedProject = mock(Project.class);
        User mockedUploader = mock(User.class);
        User mockedPic = mock(User.class);

        issue.setProject(mockedProject);
        issue.setUser_uploader(mockedUploader);
        issue.setPic(mockedPic);

        int hashCode3 = issue.hashCode();
        assertNotEquals(hashCode1, hashCode3);
    }

@Test
public void testGettersAndSetters() {
    // Mocking behavior for getId() in Project, User, and Pic
    when(project.getId()).thenReturn(1L);
    when(user_uploader.getId()).thenReturn(1L);
    when(pic.getId()).thenReturn(1L);

    // Test getters and setters
    issue.setId(1L);
    issue.setTitle("Test Title");
    issue.setDescription("Test Description");
    issue.setPlace("Test Place");
    issue.setImpact("Test Impact");
    issue.setRoot_cause("Test Root_Cause");
    issue.setDirect_cause("Test Direct_Cause");
    issue.setCorrective_action("Test Corrective_Action");
    issue.setPreventive_action("Test Preventive_Action");

    issue.setResponsible_party(1L);
    issue.setSolved(true);
    issue.setCreated_date(0);
    issue.setUpdated_date(0);
    issue.setSolved_date(0);

    issue.setIssueStatus(IssueStatus.PENDING);

    issue.setIssueCategory(Category.BUG);

    issue.setResponsible_type(ResponsibleType.CLIENT);

    project.setId(1L);
    issue.setProject(project);

    user_uploader.setId(1L);
    issue.setUser_uploader(user_uploader);

    pic.setId(1L);
    issue.setPic(pic);

    // Assertions
    assertEquals(1L, issue.getId());
    assertEquals("Test Title", issue.getTitle());
    assertEquals("Test Description", issue.getDescription());
    assertEquals("Test Place", issue.getPlace());
    assertEquals("Test Impact", issue.getImpact());
    assertEquals("Test Root_Cause", issue.getRoot_cause());
    assertEquals("Test Direct_Cause", issue.getDirect_cause());
    assertEquals("Test Corrective_Action", issue.getCorrective_action());
    assertEquals("Test Preventive_Action", issue.getPreventive_action());

    assertEquals(1L, issue.getResponsible_party());
    assertTrue(issue.isSolved());
    assertEquals(0, issue.getCreated_date());
    assertEquals(0, issue.getUpdated_date());
    assertEquals(0, issue.getSolved_date());

    assertEquals(IssueStatus.PENDING, issue.getIssueStatus());

    assertEquals(Category.BUG, issue.getIssueCategory());

    assertEquals(ResponsibleType.CLIENT, issue.getResponsible_type());

    Project projectID = issue.getProject();
    assertEquals(1L, projectID.getId());

    User userID = issue.getUser_uploader();
    assertEquals(1L, userID.getId());

    User picID = issue.getPic();
    assertEquals(1L, picID.getId());
}

    @Test
    public void testAllArgsConstructor() {
        // Create an instance of the Issue class using the generated constructor
        Issue issue = new Issue(
                1L,
                "Test Title",
                "Test Description",
                "Test Place",
                "Test Impact",
                "Test Root Cause",
                "Test Direct Cause",
                "Test Corrective Action",
                "Test Preventive Action",
                1L,
                true,
                0L,
                0L,
                0L,
                IssueStatus.PENDING,
                Category.BUG,
                ResponsibleType.CLIENT,
                new Project(),
                new User(),
                new User()
        );

        // Verify that the fields are initialized correctly
        assertEquals(1L, issue.getId());
        assertEquals("Test Title", issue.getTitle());
        assertEquals("Test Description", issue.getDescription());
        assertEquals("Test Place", issue.getPlace());
        // Add more assertions for other fields as needed
    }


}
