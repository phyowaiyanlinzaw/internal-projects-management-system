package team.placeholder.internalprojectsmanagementsystem.repository.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;

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
    public void testFindByTitle() {

        String title = "Test Issue";
        Issue issue = new Issue();
        issue.setTitle(title);

        when(issueRepository.findByTitle(title)).thenReturn(issue);

        Issue result = issueRepository.findByTitle(title);

        assertEquals(title, result.getTitle());
    }

    @Test
    public void testFindById() {

        long id = 1L;
        Issue issue = new Issue();
        issue.setId(id);

        when(issueRepository.findById(id)).thenReturn(issue);

        Issue result = issueRepository.findById(id);

        assertEquals(id, result.getId());
    }
    @Test
    public void testFindAllByIssueStatusAndPicId(){
        IssueStatus issueStatus = IssueStatus.IN_PROGRESS;
        long picId = 1L;

        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());

        when(issueRepository.findAllByIssueStatusAndPicId(issueStatus, picId)).thenReturn(expectedIssues);

        List<Issue> result = issueRepository.findAllByIssueStatusAndPicId(issueStatus, picId);

        assertEquals(expectedIssues.size(), result.size());
        for (int i = 0; i < expectedIssues.size(); i++) {
            assertEquals(expectedIssues.get(i), result.get(i));
    }}
    @Test
    public void testFindByIssueStatus(){
        IssueStatus issueStatus = IssueStatus.OPEN;

        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());

        when(issueRepository.findByIssueStatus(issueStatus)).thenReturn(expectedIssues);

        List<Issue> result = issueRepository.findByIssueStatus(issueStatus);

        assertEquals(expectedIssues.size(), result.size());
        for (int i = 0; i < expectedIssues.size(); i++) {
            assertEquals(expectedIssues.get(i), result.get(i));
        }
    }

    @Test
    public void testFindByIssueCategory(){
        Category issueCategory = Category.BUG;

        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());

        when(issueRepository.findByIssueCategory(issueCategory)).thenReturn(expectedIssues);

        List<Issue> result = issueRepository.findByIssueCategory(issueCategory);

        assertEquals(expectedIssues.size(), result.size());
        for (int i = 0; i < expectedIssues.size(); i++) {
            assertEquals(expectedIssues.get(i), result.get(i));
        }
    }

    @Test
    public void testFindAllBySolvedFalseAndPicId(){
        long picId = 1L;

        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());
        expectedIssues.add(new Issue());

        when(issueRepository.findAllBySolvedFalseAndPicId(picId)).thenReturn(expectedIssues);

        List<Issue> result = issueRepository.findAllBySolvedFalseAndPicId(picId);

        assertEquals(expectedIssues.size(), result.size());
        for (int i = 0; i < expectedIssues.size(); i++) {
            assertEquals(expectedIssues.get(i), result.get(i));
        }
    }
}
