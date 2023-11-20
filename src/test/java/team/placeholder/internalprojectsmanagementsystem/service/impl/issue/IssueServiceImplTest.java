package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
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
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;


    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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
    void testGetIssuesByCategory() {
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
        when(issueRepository.findByIssueCategory(Category.TESTING)).thenReturn(Arrays.asList(issue));
        List<Issue> issues = issueRepository.findByIssueCategory(Category.TESTING);
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findByIssueCategory(Category.TESTING);

    }

    @Test
    public void testGetIssueByStatus() {
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
        when(issueRepository.findByIssueStatus(IssueStatus.OPEN)).thenReturn(Arrays.asList(issue));
        List<Issue> issues = issueRepository.findByIssueStatus(IssueStatus.OPEN);
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findByIssueStatus(IssueStatus.OPEN);
    }

    @Test
    public void testGetIssuesBySolvedStatus() {
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
        when(issueRepository.findAllBySolvedFalseAndPicId(1L)).thenReturn(Arrays.asList(issue));
        List<Issue> issues = issueRepository.findAllBySolvedFalseAndPicId(1L);
        assertEquals(1, issues.size());
        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(1L);

    }

    @Test
    public void testGetUnsolvedIssues() {
        List<Issue> issues = new ArrayList<>();
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
        issues.add(issue);
        when(issueRepository.findAllBySolvedFalseAndPicId(1L)).thenReturn(issues);
        List<Issue> issues1 = issueRepository.findAllBySolvedFalseAndPicId(1L);
        assertEquals(1, issues1.size());
        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(1L);


    }


}



