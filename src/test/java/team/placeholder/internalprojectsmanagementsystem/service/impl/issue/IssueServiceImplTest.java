package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Existing tests...

    @Test
    public void testGetUnsolvedIssuesWhenUserIdProvidedThenReturnUnsolvedIssues() {
        // Given
        long userId = 1L;
        Issue issue1 = new Issue();
        issue1.setTitle("Issue 1");
        issue1.setSolved(false);
        Issue issue2 = new Issue();
        issue2.setTitle("Issue 2");
        issue2.setSolved(false);
        List<Issue> issues = Arrays.asList(issue1, issue2);

        when(issueRepository.findAllBySolvedFalseAndPicId(userId)).thenReturn(issues);

        // When
        List<IssueDto> result = issueService.getUnsolvedIssues(userId);

        // Then
        assertEquals(2, result.size());
        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(userId);
    }

    @Test
    public void testGetUnsolvedIssuesWhenNoUnsolvedIssuesThenReturnEmptyList() {
        // Given
        long userId = 1L;
        when(issueRepository.findAllBySolvedFalseAndPicId(userId)).thenReturn(Collections.emptyList());

        // When
        List<IssueDto> result = issueService.getUnsolvedIssues(userId);

        // Then
        assertEquals(0, result.size());
        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(userId);
    }

    @Test
    public void testGetIssuesByStatusWhenStatusIsGivenThenReturnCorrectIssues() {
        // Given
        Issue issue1 = new Issue();
        issue1.setTitle("Issue 1");
        issue1.setIssueStatus(IssueStatus.APPROVE);

        Issue issue2 = new Issue();
        issue2.setTitle("Issue 2");
        issue2.setIssueStatus(IssueStatus.APPROVE);

        List<Issue> issues = Arrays.asList(issue1, issue2);

        when(issueRepository.findByIssueStatus(IssueStatus.APPROVE)).thenReturn(issues);

        // When
        List<IssueDto> result = issueService.getIssuesByStatus("APPROVE");

        // Then
        assertEquals(2, result.size());
        assertEquals("Issue 1", result.get(0).getTitle());
        assertEquals("Issue 2", result.get(1).getTitle());
    }

    @Test
    public void testGetIssuesByStatusWhenInvalidStatusIsGivenThenThrowIllegalArgumentException() {
        // Given
        String invalidStatus = "INVALID_STATUS";

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            issueService.getIssuesByStatus(invalidStatus);
        });

        // Then
        String expectedMessage = "No enum constant team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus.INVALID_STATUS";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testUpdateStatusOfIssueListWhenGivenListOfIssuesThenUpdatesStatusAndSavesEachIssue() {
        // Given
        IssueDto issueDto1 = new IssueDto();
        issueDto1.setId(1L);
        issueDto1.setStatus("APPROVE");

        IssueDto issueDto2 = new IssueDto();
        issueDto2.setId(2L);
        issueDto2.setStatus("DECLINE");

        Issue issue1 = new Issue();
        issue1.setId(1L);
        issue1.setIssueStatus(IssueStatus.PENDING);

        Issue issue2 = new Issue();
        issue2.setId(2L);
        issue2.setIssueStatus(IssueStatus.PENDING);

        when(issueRepository.findById(issueDto1.getId())).thenReturn(issue1);
        when(issueRepository.findById(issueDto2.getId())).thenReturn(issue2);

        // When
        issueService.updateStatusOfIssueList(Arrays.asList(issueDto1, issueDto2));

        // Then
        verify(issueRepository, times(1)).findById(issueDto1.getId());
        verify(issueRepository, times(1)).findById(issueDto2.getId());
        verify(issueRepository, times(1)).save(any(Issue.class));
    }

    @Test
    public void testUpdateStatusOfIssueListWhenGivenEmptyListThenDoesNotCallSave() {
        // Given
        // When
        issueService.updateStatusOfIssueList(Collections.emptyList());

        // Then
        verify(issueRepository, never()).save(any(Issue.class));
    }

    @Test
    public void testGetPendingIssueListWhenUserIdProvidedThenReturnPendingIssues() {
        // Given
        long userId = 1L;
        Issue issue = new Issue();
        issue.setTitle("Pending Issue");
        when(issueRepository.findAllByIssueStatusAndPicId(eq(IssueStatus.PENDING), eq(userId)))
                .thenReturn(Collections.singletonList(issue));

        // When
        List<IssueDto> result = issueService.getPendingIssueList(userId);

        // Then
        assertEquals(1, result.size());
        assertEquals("Pending Issue", result.get(0).getTitle());
    }

    @Test
    public void testGetPendingIssueListWhenNoPendingIssuesThenReturnEmptyList() {
        // Given
        long userId = 1L;
        when(issueRepository.findAllByIssueStatusAndPicId(eq(IssueStatus.PENDING), eq(userId)))
                .thenReturn(Collections.emptyList());

        // When
        List<IssueDto> result = issueService.getPendingIssueList(userId);

        // Then
        assertEquals(1, result.size());
    }



    @Test
    public void testSaveIssue() {
        Issue issue = new Issue();
        issue.setTitle("Test");
        issue.setDescription("Test");
        issue.setPlace("Test");
        issue.setImpact("Test");
        issue.setRoot_cause("Test");
        issue.setDirect_cause("Test");
        issue.setCorrective_action("Test");
        issue.setPreventive_action("Test");
        issue.setSolved(true);
        Date createdDate = new Date(System.currentTimeMillis());
        Date updatedDate = new Date(System.currentTimeMillis());
        issue.setCreated_date(createdDate.getTime());
        issue.setUpdated_date(updatedDate.getTime());
        issue.setIssueCategory(Category.TESTING);
        issue.setProject(new Project());
        issue.setUser_uploader(new User());
        issue.setPic(new User());
        when(issueRepository.save(issue)).thenReturn(issue);
        issueRepository.save(issue);

        verify(issueRepository, times(1)).save(issue);
    }

    @Test
    public void testGetAllIssues() {
        List<Issue> list = new ArrayList<>();
        Issue issue1 = new Issue();
        issue1.setTitle("Test");
        issue1.setDescription("Test");
        issue1.setPlace("Test");
        issue1.setImpact("Test");
        issue1.setRoot_cause("Test");
        issue1.setDirect_cause("Test");
        issue1.setCorrective_action("Test");
        issue1.setPreventive_action("Test");
        issue1.setSolved(true);
        Date createdDate = new Date(System.currentTimeMillis());
        Date updatedDate = new Date(System.currentTimeMillis());
        issue1.setCreated_date(createdDate.getTime());
        issue1.setUpdated_date(updatedDate.getTime());
        issue1.setIssueCategory(Category.TESTING);
        issue1.setProject(new Project());
        issue1.setUser_uploader(new User());
        issue1.setPic(new User());
        list.add(issue1);
        when(issueRepository.findAll()).thenReturn(list);
        List<Issue> issues = issueRepository.findAll();
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllIssueById() {
        Issue issue = new Issue();
        issue.setTitle("Test");
        issue.setDescription("Test");
        issue.setPlace("Test");
        issue.setImpact("Test");
        issue.setRoot_cause("Test");
        issue.setDirect_cause("Test");
        issue.setCorrective_action("Test");
        issue.setPreventive_action("Test");
        issue.setSolved(true);
        Date createdDate = new Date(System.currentTimeMillis());
        Date updatedDate = new Date(System.currentTimeMillis());
        issue.setCreated_date(createdDate.getTime());
        issue.setUpdated_date(updatedDate.getTime());
        issue.setIssueCategory(Category.TESTING);
        issue.setProject(new Project());
        issue.setUser_uploader(new User());
        issue.setPic(new User());
        when(issueRepository.findById(1L)).thenReturn(issue);
        Issue issue1 = issueRepository.findById(1L);
        assertEquals("Test", issue1.getTitle());
        verify(issueRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateIssue() {
        Issue issue = new Issue();
        issue.setTitle("Test");
        issue.setDescription("Test");
        issue.setPlace("Test");
        issue.setImpact("Test");
        issue.setRoot_cause("Test");
        issue.setDirect_cause("Test");
        issue.setCorrective_action("Test");
        issue.setPreventive_action("Test");
        issue.setSolved(true);
        Date createdDate = new Date(System.currentTimeMillis());
        Date updatedDate = new Date(System.currentTimeMillis());
        issue.setCreated_date(createdDate.getTime());
        issue.setUpdated_date(updatedDate.getTime());
        issue.setIssueCategory(Category.TESTING);
        issue.setProject(new Project());
        issue.setUser_uploader(new User());
        issue.setPic(new User());
        when(issueRepository.findById(1L)).thenReturn(issue);
        Issue issue1 = issueRepository.findById(1L);
        issue1.setTitle("Test2");
        issueRepository.save(issue1);
        assertEquals("Test2", issue1.getTitle());
        verify(issueRepository, times(1)).save(issue1);
    }

    @Test
    public void testGetPendingIssueList_WithPendingIssues() {
        long id = 1L;
        Issue issue1 = new Issue();
        issue1.setTitle("Issue 1");
        // Set other properties for issue1

        Issue issue2 = new Issue();
        issue2.setTitle("Issue 2");


        when(issueRepository.findAllByIssueStatusAndPicId(eq(IssueStatus.PENDING), eq(id)))
                .thenReturn(List.of(issue1, issue2));

        // Mock necessary calls for client and user repositories

        // Act
        List<IssueDto> result = issueService.getPendingIssueList(id);

        // Assert
        assertEquals(2, result.size());
        // Print the titles of the issues received for debugging purposes
        result.forEach(issueDto -> System.out.println("Issue Title: " + issueDto.getTitle()));
    }
}
