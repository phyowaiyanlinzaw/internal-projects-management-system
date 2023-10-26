//package team.placeholder.internalprojectsmanagementsystem.model.issue;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//import team.placeholder.internalprojectsmanagementsystem.model.user.User;
//
//import java.util.Objects;
//import java.sql.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//public class IssueTest {
//    @MockBean
//    private Issue issue;
//    @MockBean
//    private IssueCategory issueCategory;
//    @MockBean
//    private Project project;
//    @MockBean
//    private User userUploader;
//    @MockBean
//    private User userPic;
//
//    @BeforeEach
//    public void setUp() {
//        issue = new Issue();
//        issue.setId(1L);
//        issue.setTitle("Test Issue");
//        issue.setDescription("This is a test issue.");
//        issue.setPlace("Test place");
//        issue.setImpact("Test impact");
//        issue.setRoot_cause("Test root cause");
//        issue.setDirect_cause("Test direct cause");
//        issue.setCorrective_action("Test corrective action");
//        issue.setPreventive_action("Test preventive action");
//        issue.setClint_or_user(42);
//        issue.setSolved(false);
//        issue.setCreated_date(Date.valueOf("2023-01-01"));
//        issue.setUpdated_date(Date.valueOf("2023-01-02"));
//
//        issueCategory = mock(IssueCategory.class);
//        project = mock(Project.class);
//        userUploader = mock(User.class);
//        userPic = mock(User.class);
//
//        issue.setIssueCategory(issueCategory);
//        issue.setProject(project);
//        issue.setUser_uploader(userUploader);
//        issue.setUser_pic(userPic);
//    }
//
//    @Test
//    public void testGetterAndSetterMethods() {
//        // Test getter and setter methods
//        assertEquals(1L, issue.getId());
//        assertEquals("Test Issue", issue.getTitle());
//        assertEquals("This is a test issue.", issue.getDescription());
//        assertEquals("Test place", issue.getPlace());
//        assertEquals("Test impact", issue.getImpact());
//        assertEquals("Test root cause", issue.getRoot_cause());
//        assertEquals("Test direct cause", issue.getDirect_cause());
//        assertEquals("Test corrective action", issue.getCorrective_action());
//        assertEquals("Test preventive action", issue.getPreventive_action());
//        assertEquals(42, issue.getClint_or_user());
//        assertFalse(issue.isSolved());
//        assertEquals(Date.valueOf("2023-01-01"), issue.getCreated_date());
//        assertEquals(Date.valueOf("2023-01-02"), issue.getUpdated_date());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        // Create two Issue objects with the same id
//        Issue anotherIssue = new Issue();
//        anotherIssue.setId(1L);
//
//        // Test equals method
//        assertTrue(issue.equals(anotherIssue));
//        assertTrue(anotherIssue.equals(issue));
//
//        // Test hashCode method
//        assertEquals(issue.hashCode(), anotherIssue.hashCode());
//    }
//
//    @Test
//    public void testNotEquals() {
//        // Create two Issue objects with different ids
//        Issue anotherIssue = new Issue();
//        anotherIssue.setId(2L);
//
//        // Test equals method
//        assertFalse(issue.equals(anotherIssue));
//        assertFalse(anotherIssue.equals(issue));
//
//        // As a rule, it's a good practice for different objects to have different hash codes.
//        assertNotEquals(issue.hashCode(), anotherIssue.hashCode());
//    }
//
//    @Test
//    public void testRelationshipsWithEntities() {
//        // Test relationships with related entities
//        assertSame(issueCategory, issue.getIssueCategory());
//        assertSame(project, issue.getProject());
//        assertSame(userUploader, issue.getUser_uploader());
//        assertSame(userPic, issue.getUser_pic());
//    }
//}
