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
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private IssueServiceImpl issueService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

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
    public void testGetPendingIssueList() {
        // Create a mock list of pending issues
        List<Issue> pendingIssues = new ArrayList<>();
        Issue issue1 = new Issue();
        issue1.setId(1);
        issue1.setTitle("Issue 1");
        issue1.setResponsible_type(ResponsibleType.CLIENT);
        issue1.setResponsible_party(1);
        issue1.setPic(new User());
        issue1.setUser_uploader(new User());
        issue1.setProject(new Project());
        pendingIssues.add(issue1);

        // Mock the behavior of the issueRepository
        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, 1)).thenReturn(pendingIssues);

        // Create a mock ClientDto
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1);
        clientDto.setName("Client 1");

        // Mock the behavior of the clientRepository
        when(clientRepository.findById(1)).thenReturn(new Client());

        // Create a mock UserDto
        UserDto userDto = new UserDto();
        userDto.setId(2);
        userDto.setName("User 1");

        // Mock the behavior of the userRepository
        when(userRepository.findById(2)).thenReturn(new User());

        // Create a mock ProjectDto
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1);
        projectDto.setName("Project 1");

        // Mock the behavior of the modelMapper
        when(modelMapper.map(eq(issue1), eq(IssueDto.class))).thenReturn(new IssueDto());
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
        when(modelMapper.map(eq(issue1.getProject()), eq(ProjectDto.class))).thenReturn(projectDto);

        // Call the method to be tested
        List<IssueDto> result = issueService.getPendingIssueList(1);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("Issue 1", result.get(0).getTitle());
        assertEquals(clientDto, result.get(0).getResponsible_party());
        assertEquals(projectDto, result.get(0).getProjectDto());

        // Verify that the appropriate methods were called
        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, 1);
        verify(clientRepository, times(1)).findById(1);
        verify(userRepository, times(1)).findById(2);
        verify(modelMapper, times(1)).map(eq(issue1), eq(IssueDto.class));
        verify(modelMapper, times(1)).map(any(User.class), eq(UserDto.class));
        verify(modelMapper, times(1)).map(eq(issue1.getProject()), eq(ProjectDto.class));
    }




}
