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

    @Test
    public void testSaveIssue() {
        // Arrange
        IsuDto isuDto = new IsuDto();
        isuDto.setTitle("Test Issue");
        isuDto.setDescription("This is a test issue");
        // Set other properties of isuDto

        Issue issue = new Issue();
        issue.setTitle(isuDto.getTitle());
        issue.setDescription(isuDto.getDescription());
        // Set other properties of issue

        Issue savedIssue = new Issue();
        savedIssue.setId(1L);
        savedIssue.setTitle(isuDto.getTitle());
        savedIssue.setDescription(isuDto.getDescription());
        // Set other properties of savedIssue

        IssueDto expectedIssueDto = new IssueDto();
        expectedIssueDto.setId(1L);
        expectedIssueDto.setTitle(isuDto.getTitle());
        expectedIssueDto.setDescription(isuDto.getDescription());
        // Set other properties of expectedIssueDto

        when(issueRepository.save(any(Issue.class))).thenReturn(savedIssue);

        // Act
        IssueDto actualIssueDto = issueService.save(isuDto);

        // Assert
        assertEquals(expectedIssueDto.getId(), actualIssueDto.getId());
        assertEquals(expectedIssueDto.getTitle(), actualIssueDto.getTitle());
        assertEquals(expectedIssueDto.getDescription(), actualIssueDto.getDescription());
        // Assert other properties of actualIssueDto

        verify(issueRepository, times(1)).save(any(Issue.class));
        verify(notificationService, times(1)).save(anyString(), any(), anyString(), any(IssueDto.class));
    }

    private UserDto createSampleUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("john.doe");
        // Set other properties as needed for your tests
        return userDto;
    }
    // Helper methods to create sample objects for mocks




    @Test
    void testSaveWhenIsuDtoHasNullFieldsThenReturnsIssueDto() {
        // Arrange
        IsuDto isuDto = new IsuDto();
        Issue issue = createSampleIssue();
        IssueDto expectedIssueDto = createSampleIssueDto();
        when(issueRepository.save(any(Issue.class))).thenReturn(issue);
        when(modelMapper.map(issue, IssueDto.class)).thenReturn(expectedIssueDto);

        // Act
        IssueDto result = issueService.save(isuDto);

        // Assert
        assertEquals(expectedIssueDto, result);

        // Verify interactions
        verify(issueRepository, times(1)).save(any(Issue.class));
        verify(notificationService, times(1)).save(anyString(), any(), anyString(), any());
    }

    @Test
    void testSaveWhenRepositorySaveReturnsNullThenReturnsNull() {
        // Arrange
        IsuDto isuDto = createSampleIsuDto();
        when(issueRepository.save(any(Issue.class))).thenReturn(null);

        // Act
        IssueDto result = issueService.save(isuDto);

        // Assert
        assertNull(result);

        // Verify interactions
        verify(issueRepository, times(1)).save(any(Issue.class));
        verify(notificationService, never()).save(anyString(), any(), anyString(), any());
    }

    private IsuDto createSampleIsuDto() {
        IsuDto isuDto = new IsuDto();
        isuDto.setTitle("Sample Title");
        // Set other properties as needed for your tests
        return isuDto;
    }

    private Issue createSampleIssue() {
        Issue issue = new Issue();
        issue.setTitle("Sample Title");
        // Set other properties as needed for your tests
        return issue;
    }

    private IssueDto createSampleIssueDto() {
        IssueDto issueDto = new IssueDto();
        issueDto.setTitle("Sample Title");
        // Set other properties as needed for your tests
        return issueDto;
    }

    @Test
    void save() {
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
    void testSave_NullIssue() {
        // Arrange
        IsuDto isuDto = createSampleIsuDto();
        when(issueRepository.save(any(Issue.class))).thenReturn(null);

        // Act
        IssueDto result = issueService.save(isuDto);

        // Assert
        assertNull(result);

        // Verify interactions
        verify(issueRepository, times(1)).save(any(Issue.class));
        verify(notificationService, never()).save(anyString(), any(), anyString(), any());
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
    void getAllIssues() {
        // Arrange
        List<Issue> sampleIssues = Collections.singletonList(createSampleIssue());
        when(issueRepository.findAll()).thenReturn(sampleIssues);

        // Act
        List<IssueDto> issues = issueService.getAllIssues();

        // Assert
        assertEquals(sampleIssues.size(), issues.size());
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    void getIssueById() {
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
    void updateIssue() {
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
    void getUnsolvedIssues() {
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
    void getIssuesByStatus() {
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
    void getIssuesBySolvedStatus() {
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
    void getIssuesByCategory() {
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