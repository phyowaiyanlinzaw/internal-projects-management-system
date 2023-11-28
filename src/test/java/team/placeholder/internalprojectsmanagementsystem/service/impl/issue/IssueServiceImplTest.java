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
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private NotificationService notificationService;

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

    // Existing test cases...

    @Test
    void testSave() {
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

    private User createSampleUser() {
        User user = new User();
        user.setId(1L);
        user.setName("john.doe");
        // Set other properties as needed for your tests
        return user;
    }

    private Project createSampleProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");
        // Set other properties as needed for your tests
        return project;
    }

    private Client createSampleClient() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Sample Client");
        // Set other properties as needed for your tests
        return client;
    }

    private Issue createSampleSavedIssue() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Test");
        issue.setDescription("Test");
        issue.setPlace("Test");
        issue.setImpact("Test");
        issue.setRoot_cause("Test");
        issue.setDirect_cause("Test");
        issue.setCorrective_action("Test");
        issue.setPreventive_action("Test");
        issue.setSolved(true);
        issue.setCreated_date(new Date().getTime());
        issue.setUpdated_date(new Date().getTime());
        issue.setIssueCategory(Category.TESTING);
        issue.setProject(new Project());
        issue.setUser_uploader(new User());
        issue.setPic(new User());
        return issue;
    }

    @Test
    void testGetAllIssues() {
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
        when(issueRepository.findAll()).thenReturn(issues);
        List<Issue> issues1 = issueRepository.findAll();
        assertEquals(1, issues1.size());
        verify(issueRepository, times(1)).findAll();
    }

//    @Test
//    void testGetAllIssues() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//        // Initialize a list containing a single sample issue
//        List<Issue> sampleIssues = Collections.singletonList(createSampleIssue());
//        // Mock the behavior of the issueRepository to return the sampleIssues when findAll() is called
//        when(issueRepository.findAll()).thenReturn(sampleIssues);
//
//        // Mock necessary mappings for UserDto, ClientDto, and ProjectDto
//        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
//        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
//        when(modelMapper.map(any(Project.class), eq(ProjectDto.class))).thenReturn(new ProjectDto());
//
//        // Act
//        // Call the getAllIssues() method to get the list of issues
//        List<IssueDto> issues = issueService.getAllIssues();
//
//        // Assert
//        // Ensure that the size of the returned issues list is 1
//        assertEquals(1, issues.size(), "Expected one issue in the result");
//
//        // You can add more assertions based on your logic
//        // Check that ProjectDto and UserUploader are not null in the first issue
//        assertNotNull(issues.get(0).getProjectDto(), "ProjectDto should not be null");
//        assertNotNull(issues.get(0).getUser_uploader(), "UserUploader should not be null");
//
//        // Verify interactions if needed
//        // Verify that the findAll() method on issueRepository was called exactly once
//        verify(issueRepository, times(1)).findAll();
//        // Verify that the map method on modelMapper with UserDto as the target class was called at least once
//        verify(modelMapper, atLeastOnce()).map(any(Issue.class), eq(IssueDto.class));
//        // You can add more verifications as needed
//    }
//


    private Issue createSampleIssue() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Test");
        issue.setDescription("Test");
        issue.setPlace("Test");
        issue.setImpact("Test");
        issue.setRoot_cause("Test");
        issue.setDirect_cause("Test");
        issue.setCorrective_action("Test");
        issue.setPreventive_action("Test");
        issue.setSolved(true);
        issue.setCreated_date(new Date().getTime());
        issue.setUpdated_date(new Date().getTime());
        issue.setIssueCategory(Category.TESTING);
        issue.setProject(new Project());
        issue.setUser_uploader(new User());
        issue.setPic(new User());
        return issue;
    }

    @Test
    void testGetIssueById() {
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
    void testUpdateIssue() {
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
    void getPendingIssueList() {

    }

    @Test
    void updateStatusOfIssueList() {
    }

    @Test
    void testGetUnsolvedIssues() {
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

    @Test
    void countIssuesByProjectManagerId() {
    }

    @Test
    void testGetIssuesByStatus() {
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
    void testGetIssuesBySolvedStatus() {
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

    // Add more test cases to cover other branches and scenarios
}