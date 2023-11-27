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
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;

import static org.junit.jupiter.api.Assertions.*;

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

//    @Test
//    void testSave() {
//        // Arrange
//        IsuDto isuDto = createSampleIsuDto();
//        Issue savedIssue = createSampleSavedIssue();
//        IssueDto expectedIssueDto = createSampleIssueDto();
//
//        // Mocking the necessary interactions
//        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
//        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
//        when(modelMapper.map(any(Issue.class), eq(IssueDto.class))).thenReturn(expectedIssueDto);
//        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
//        when(projectRepository.findById(any())).thenReturn(Optional.of(new Project()));
//        when(clientRepository.findById(any())).thenReturn(Optional.of(new Client()));
//        when(issueRepository.save(any(Issue.class))).thenReturn(savedIssue);
//
//        // Act
//        IssueDto result = issueService.save(isuDto);
//
//        // Assert
//        assertNull(result);
//        assertEquals(expectedIssueDto.getId(), result.getId());
//        assertEquals("Titles should match", expectedIssueDto.getTitle(), result.getTitle());
//        // Add similar assertions for other properties...
//
//        // Verify interactions
//        verify(issueRepository, times(1)).save(any(Issue.class));
//        verify(notificationService, times(1)).save(anyString(), any(), anyString(), any());
//    }
//
//
//
//    private IssueDto createSampleIssueDto() {
//        IssueDto issueDto = new IssueDto();
//        issueDto.setId(1L);
//        issueDto.setTitle("Test");
//        issueDto.setDescription("Test");
//        issueDto.setPlace("Test");
//        issueDto.setImpact("Test");
//        issueDto.setRoot_cause("Test");
//        issueDto.setDirect_cause("Test");
//        issueDto.setCorrective_action("Test");
//        issueDto.setPreventive_action("Test");
//        issueDto.setSolved(true);
//        issueDto.setCreated_date(new Date().getTime());
//        issueDto.setUpdated_date(new Date().getTime());
//        issueDto.setIssueCategory(String.valueOf(Category.TESTING));
//        issueDto.setProjectDto(new ProjectDto());
//        issueDto.setUser_uploader(new UserDto());
//        issueDto.setUser_pic(new UserDto());
//        return issueDto;
//    }
//
//    private Issue createSampleSavedIssue() {
//        Issue issue = new Issue();
//        issue.setId(1L);
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        issue.setCreated_date(new Date().getTime());
//        issue.setUpdated_date(new Date().getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        return issue;
//    }
//
//    private IsuDto createSampleIsuDto() {
//        IsuDto isuDto = new IsuDto();
//        isuDto.setTitle("Test");
//        isuDto.setDescription("Test");
//        isuDto.setPlace("Test");
//        isuDto.setImpact("Test");
//        isuDto.setRoot_cause("Test");
//        isuDto.setDirect_cause("Test");
//        isuDto.setCorrective_action("Test");
//        isuDto.setPreventive_action("Test");
//        isuDto.setSolved(true);
//        isuDto.setCreated_date(new Date().getTime());
//        isuDto.setUpdated_date(new Date().getTime());
//        isuDto.setIssueCategory(String.valueOf(Category.TESTING));
//        isuDto.setProject_id(new ProjectDto().getId());
//        isuDto.setUser_uploader(new UserDto().getId());
//        isuDto.setUser_pic(new UserDto().getId());
//        isuDto.setResponsible_type(String.valueOf(ResponsibleType.CLIENT));
//        isuDto.setResponsible_party(1L);
//        return isuDto;
//    }

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
}