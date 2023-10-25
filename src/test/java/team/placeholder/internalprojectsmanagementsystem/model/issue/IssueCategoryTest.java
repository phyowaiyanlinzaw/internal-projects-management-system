//package team.placeholder.internalprojectsmanagementsystem.model.issue;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//public class IssueCategoryTest {
//
//    private IssueCategory issueCategory;
//    private List<Issue> issues;
//
//    @BeforeEach
//    public void setUp() {
//        issueCategory = new IssueCategory();
//        issueCategory.setId(1L);
//        issueCategory.setName("TestCategory");
//
//        issues = new ArrayList<>();
//        Issue issue1 = mock(Issue.class);
//        issue1.setId(1L);
//        Issue issue2 = mock(Issue.class);
//        issue2.setId(2L);
//
//        issues.add(issue1);
//        issues.add(issue2);
//
//        issueCategory.setIssues(issues);
//    }
//
//    @Test
//    public void testGetterAndSetterMethods() {
//        // Test getter and setter methods
//        assertEquals(1L, issueCategory.getId());
//        assertEquals("TestCategory", issueCategory.getName());
//        assertSame(issues, issueCategory.getIssues());
//    }
//
//    @Test
//    public void testEqualsAndHashCode() {
//        // Create two IssueCategory objects with the same id
//        IssueCategory anotherIssueCategory = new IssueCategory();
//        anotherIssueCategory.setId(1L);
//
//        // Test equals method
//        assertTrue(issueCategory.equals(anotherIssueCategory));
//        assertTrue(anotherIssueCategory.equals(issueCategory));
//
//        // Test hashCode method
//        assertEquals(issueCategory.hashCode(), anotherIssueCategory.hashCode());
//    }
//
//    @Test
//    public void testNotEquals() {
//        // Create two IssueCategory objects with different ids
//        IssueCategory anotherIssueCategory = new IssueCategory();
//        anotherIssueCategory.setId(2L);
//
//        // Test equals method
//        assertFalse(issueCategory.equals(anotherIssueCategory));
//        assertFalse(anotherIssueCategory.equals(issueCategory));
//
//        // As a rule, it's a good practice for different objects to have different hash codes.
//        assertNotEquals(issueCategory.hashCode(), anotherIssueCategory.hashCode());
//    }
//
//    @Test
//    public void testIssuesRelationship() {
//        // Test the relationship with related issues
//        assertSame(issues, issueCategory.getIssues());
//    }
//}
//
