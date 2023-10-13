package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IssueRepositoryTest {

    @Mock
    private IssueRepository issueRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSelectAllIssue() {
        // Arrange
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue());
        issues.add(new Issue());

        // Mock the behavior of issueRepository.selectAllIssue
        when(issueRepository.selectAllIssue()).thenReturn(issues);

        // Act
        List<Issue> result = issueRepository.selectAllIssue();

        // Assert
        assertEquals(issues.size(), result.size());
    }



    @Test
    public void testFindByTitle() {
        // Arrange
        String title = "Test Issue";
        Issue issue = new Issue();
        issue.setTitle(title);

        // Mock the behavior of issueRepository.findByTitle
        when(issueRepository.findByTitle(title)).thenReturn(issue);

        // Act
        Issue result = issueRepository.findByTitle(title);

        // Assert
        assertEquals(title, result.getTitle());
    }

    @Test
    public void testFindById() {
        // Arrange
        long id = 1L;
        Issue issue = new Issue();
        issue.setId(id);

        // Mock the behavior of issueRepository.findById
        when(issueRepository.findById(id)).thenReturn(issue);

        // Act
        Issue result = issueRepository.findById(id);

        // Assert
        assertEquals(id, result.getId());
    }
}
