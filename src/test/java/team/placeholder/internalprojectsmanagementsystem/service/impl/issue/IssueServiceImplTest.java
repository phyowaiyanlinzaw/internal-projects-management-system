import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    @Test
    void testUpdateStatusOfIssueList() {
        // Given
        List<Issue> issueList = Arrays.asList(new Issue(), new Issue());

        // When
        issueService.updateStatusOfIssueList(issueList);

        // Then
        verify(issueRepository, times(issueList.size())).save(any(Issue.class));
    }

    @Test
    void testGetIssuesByCategory() {
        // Given
        Issue issue = new Issue();
        issue.setIssueCategory(Category.TESTING);

        // Stubbing the behavior of the repository when findByIssueCategory is called with Category.TESTING
        when(issueRepository.findByIssueCategory(Category.TESTING)).thenReturn(Arrays.asList(issue));

        // When
        List<Issue> issues = issueService.getIssuesByCategory(Category.TESTING);

        // Then
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findByIssueCategory(Category.TESTING);
    }

    @Test
    void testGetIssueByStatus() {
        // Given
        Issue issue = new Issue();
        issue.setIssueStatus(IssueStatus.OPEN);

        // Stubbing the behavior of the repository when findByIssueStatus is called with IssueStatus.OPEN
        when(issueRepository.findByIssueStatus(IssueStatus.OPEN)).thenReturn(Arrays.asList(issue));

        // When
        List<Issue> issues = issueService.getIssueByStatus(IssueStatus.OPEN);

        // Then
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findByIssueStatus(IssueStatus.OPEN);
    }

    @Test
    void testGetIssuesBySolvedStatus() {
        // Given
        Issue issue = new Issue();
        issue.setSolved(true);

        // Stubbing the behavior of the repository when findAllBySolvedFalseAndPicId is called with 1L
        when(issueRepository.findAllBySolvedFalseAndPicId(1L)).thenReturn(Arrays.asList(issue));

        // When
        List<Issue> issues = issueService.getIssuesBySolvedStatus(1L);

        // Then
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(1L);
    }

    @Test
    void testGetUnsolvedIssues() {
        // Given
        Issue issue = new Issue();
        issue.setSolved(false);

        // Stubbing the behavior of the repository when findAllBySolvedFalseAndPicId is called with 1L
        when(issueRepository.findAllBySolvedFalseAndPicId(1L)).thenReturn(Arrays.asList(issue));

        // When
        List<IssueDto> issues = issueService.getUnsolvedIssues(1L);

        // Then
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(1L);
    }
}
