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
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.issue.IssueServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private NotificationServiceImpl notificationService;

    @Mock
    private ProjectRepository projectRepository; // Ensure this is properly initialized

    @InjectMocks
    private IssueServiceImpl issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        // Create a sample IsuDto object
        IsuDto isuDto = new IsuDto();
        isuDto.setTitle("Test Issue");
        isuDto.setUser_uploader(1L); // Set a valid user ID

        // Create a sample Issue object
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle(isuDto.getTitle());

        // Create a sample IssueDto object
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issue.getId());
        issueDto.setTitle(issue.getTitle());

        // Mock the behavior of the userRepository.findById method to return a User object directly
        when(userRepository.findById(isuDto.getUser_uploader())).thenReturn(new User());

        // Mock the behavior of the issueRepository.save method to return the sample Issue object
        when(issueRepository.save(any(Issue.class))).thenReturn(issue);

        // Mock the behavior of the modelMapper.map method to return the sample IssueDto object
        when(modelMapper.map(issue, IssueDto.class)).thenReturn(issueDto);

        // Call the save method of the issueService
        IssueDto savedIssueDto = issueService.save(isuDto);

        // Assert the expected values
        assertEquals(issueDto.getId(), savedIssueDto.getId());
        assertEquals(issueDto.getTitle(), savedIssueDto.getTitle());

        // Verify that the userRepository.findById method was called once with the correct user ID
        verify(userRepository, times(1)).findById(isuDto.getUser_uploader());

        // Verify that the issueRepository.save method was called once with any Issue object
        verify(issueRepository, times(1)).save(any(Issue.class));

        // Verify that the modelMapper.map method was called once with the correct Issue object and IssueDto class
        verify(modelMapper, times(1)).map(issue, IssueDto.class);
    }
    @Test
    public void testGetAllIssues() {
        // Create a sample Issue
        Issue issue = new Issue();
        issue.setTitle("Test Issue");

        // Map the Issue to IssueDto
        IssueDto issueDto = new IssueDto();
        issueDto.setTitle(issue.getTitle());

        // Set up issueList and expectedIssueDtos
        List<Issue> issueList = Collections.singletonList(issue);
        List<IssueDto> expectedIssueDtos = Collections.singletonList(issueDto);

        // Mock the repository to return the issueList
        when(issueRepository.findAll()).thenReturn(issueList);

        // Mock the modelMapper to return the mapped IssueDto
        when(modelMapper.map(any(), eq(IssueDto.class))).thenReturn(issueDto);

        // Call the method being tested
        List<IssueDto> result = issueService.getAllIssues();

        // Assertions
        assertEquals(expectedIssueDtos.size(), result.size());
        assertEquals(expectedIssueDtos.get(0).getTitle(), result.get(0).getTitle()); // Or check other properties

        // Verify method invocations
        verify(issueRepository, times(1)).findAll();
        verify(modelMapper, times(issueList.size())).map(any(), eq(IssueDto.class));
    }


    @Test
    public void testGetIssueById() {
        // Create a sample Issue
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Test Issue");

        // Map the Issue to IssueDto
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issue.getId());
        issueDto.setTitle(issue.getTitle());

        // Mock the repository to return the issue
        when(issueRepository.findById(issue.getId())).thenReturn(issue); // Optional.of()));

        // Mock the modelMapper to return the mapped IssueDto
        when(modelMapper.map(any(), eq(IssueDto.class))).thenReturn(issueDto);

        // Call the method being tested
        IssueDto result = issueService.getIssueById(issue.getId());

        // Assertions
        assertEquals(issueDto.getId(), result.getId());
        assertEquals(issueDto.getTitle(), result.getTitle()); // Or check other properties

        // Verify method invocations
        verify(issueRepository, times(1)).findById(issue.getId());
        verify(modelMapper, times(1)).map(any(), eq(IssueDto.class));
    }



    @Test
    public void testGetIssuesByCategory_WithExistingIssues() {
        // Mock data
        List<Issue> mockIssues = Collections.singletonList(new Issue());
        List<IssueDto> expectedIssueDtos = Collections.singletonList(new IssueDto());

        // Mock repository response
        when(issueRepository.findByIssueCategory(any())).thenReturn(mockIssues);

        // Mock modelMapper response
        when(modelMapper.map(any(), eq(IssueDto.class))).thenReturn(new IssueDto());
        when(modelMapper.map(any(), eq(UserDto.class))).thenReturn(new UserDto());

        // Call the method
        List<IssueDto> result = issueService.getIssuesByCategory("BUG");

        // Assertions
        assertEquals(expectedIssueDtos.size(), result.size());

        // Verify interactions with mocks
        verify(issueRepository, times(1)).findByIssueCategory(any());
        verify(modelMapper, times(mockIssues.size())).map(any(), eq(IssueDto.class));
        verify(modelMapper, atLeast(0)).map(any(), eq(UserDto.class));
    }



    @Test
    public void testGetIssuesByCategory_WithInvalidCategory() {
        // Mock repository response
        when(issueRepository.findByIssueCategory(any())).thenReturn(Collections.emptyList());

        // Call the method with an invalid category
        List<IssueDto> result = issueService.getIssuesByCategory("INVALID_CATEGORY");

        // Assertions (you might have different expectations for invalid input)
        assertEquals(0, result.size());

        // Verify interactions with mocks
        verify(issueRepository, never()).findByIssueCategory(any());
        verify(modelMapper, never()).map(any(), eq(IssueDto.class));
    }




}
