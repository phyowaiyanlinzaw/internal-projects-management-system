//package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;
//
//
//import ch.qos.logback.classic.Logger;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
//import team.placeholder.internalprojectsmanagementsystem.dto.uidto.IsuDto;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.Category;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.IssueStatus;
//import team.placeholder.internalprojectsmanagementsystem.model.issue.issueenum.ResponsibleType;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
//import team.placeholder.internalprojectsmanagementsystem.model.user.User;
//import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
//import team.placeholder.internalprojectsmanagementsystem.repository.issue.IssueRepository;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
//import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
//import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
//import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class IssueServiceImplTest {
//
//    @Mock
//    private IssueRepository issueRepository;
//
//    @Mock
//    private ProjectRepository projectRepository;
//
//    @Mock
//    private NotificationService notificationService;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private IssueServiceImpl issueService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    // Existing test cases...
//
//    // New test cases...
//
//    // Helper methods...
//
//    private Issue createPendingIssue() {
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
//        issue.setIssueStatus(IssueStatus.PENDING);
//        issue.setSolved(true);
//        issue.setCreated_date(new Date().getTime());
//        issue.setUpdated_date(new Date().getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(createSampleProject());
//        issue.setUser_uploader(createSampleUser());
//        issue.setPic(createSampleUser());
//        issue.setResponsible_party(createSampleUser().getId());
//        issue.setResponsible_type(ResponsibleType.CLIENT);
//        return issue;
//    }
//
//    private User createSampleUser() {
//        User user = new User();
//        user.setId(1L);
//        user.setName("john.doe");
//        user.setEmail("john.doe@example.com");
//        user.setPassword("password");
//        user.setEnabled(true);
//        user.setRole(Role.EMPLOYEE);
//        return user;
//    }
//
//    private Project createSampleProject() {
//        Project project = new Project();
//        project.setId(1L);
//        project.setName("Sample Project");
//        project.setBackground("Sample Background");
//        project.setDuration(1);
//        project.setStart_date(new Date().getTime());
//        project.setEnd_date(new Date().getTime());
//        project.setObjective("Sample Objective");
//        project.setClosed(false);
//        return project;
//    }
//
//    @Test
//    void testGetPendingIssueListWhenUserIdProvidedThenReturnPendingIssues() {
//        // Arrange
//        long userId = 1L;
//        Issue pendingIssue = createPendingIssue();
//        List<Issue> pendingIssueList = Collections.singletonList(pendingIssue);
//
//        // Mock repository behavior
//        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, userId)).thenReturn(pendingIssueList);
//
//        // Act
//        List<IssueDto> issueDtos = issueService.getPendingIssueList(userId);
//
//        // Assert
//        assertEquals(1, issueDtos.size(), "Expected one pending issue in the result");
//
//        IssueDto resultIssueDto = issueDtos.get(0);
//        assertNotNull(resultIssueDto.getResponsible_party(), "Responsible party should not be null");
//        assertNotNull(resultIssueDto.getUser_pic(), "User pic should not be null");
//        assertNotNull(resultIssueDto.getUser_uploader(), "User uploader should not be null");
//        assertNotNull(resultIssueDto.getProjectDto(), "ProjectDto should not be null");
//
//        // Update this assertion to check the status property
//        assertEquals(IssueStatus.PENDING.toString(), resultIssueDto.getStatus(), "Status should be PENDING");
//
//        // Verify interactions
//        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, userId);
//        verify(modelMapper, atLeastOnce()).map(any(Issue.class), eq(IssueDto.class));
//    }
//
//
//
//
//
//    @Test
//    void testSave() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        when(issueRepository.save(issue)).thenReturn(issue);
//        issueRepository.save(issue);
//
//        verify(issueRepository, times(1)).save(issue);
//    }
//
//    private Client createSampleClient() {
//        Client client = new Client();
//        client.setId(1L);
//        client.setName("Sample Client");
//        // Set other properties as needed for your tests
//        return client;
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
//    @Test
//    void testGetAllIssues() {
//        List<Issue> issues = new ArrayList<>();
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        issues.add(issue);
//        when(issueRepository.findAll()).thenReturn(issues);
//        List<Issue> issues1 = issueRepository.findAll();
//        assertEquals(1, issues1.size());
//        verify(issueRepository, times(1)).findAll();
//    }
//
//    private Issue createSampleIssue() {
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
//    @Test
//    void testGetIssueById() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        when(issueRepository.findById(1L)).thenReturn(issue);
//        Issue issue1 = issueRepository.findById(1L);
//        assertEquals("Test", issue1.getTitle());
//        verify(issueRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testUpdateIssue() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        when(issueRepository.findById(1L)).thenReturn(issue);
//        Issue issue1 = issueRepository.findById(1L);
//        issue1.setTitle("Test2");
//        issueRepository.save(issue1);
//        assertEquals("Test2", issue1.getTitle());
//        verify(issueRepository, times(1)).save(issue1);
//    }
//
//    @Test
//    void testGetPendingIssueList() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//
//        // Mock data
//        long picId = 1L;
//        Issue pendingIssue = createPendingIssue();
//        List<Issue> pendingIssueList = Collections.singletonList(pendingIssue);
//
//        // Mock repository behavior
//        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, picId)).thenReturn(pendingIssueList);
//
//        // Mock mappings
//        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
//        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
//        when(modelMapper.map(any(Project.class), eq(ProjectDto.class))).thenReturn(new ProjectDto());
//
//        // Act
//        List<IssueDto> issueDtos = issueService.getPendingIssueList(picId);
//
//        // Assert
//        assertEquals(1, issueDtos.size(), "Expected one pending issue in the result");
//
//        IssueDto resultIssueDto = issueDtos.get(0);
//        assertNotNull(resultIssueDto.getResponsible_party(), "Responsible party should not be null");
//        assertNotNull(resultIssueDto.getUser_pic(), "User pic should not be null");
//        assertNotNull(resultIssueDto.getUser_uploader(), "User uploader should not be null");
//        assertNotNull(resultIssueDto.getProjectDto(), "ProjectDto should not be null");
//        assertEquals(IssueStatus.PENDING.toString(), resultIssueDto.getStatus(), "IssueStatus should be PENDING");
//
//        // You can add more specific assertions based on your mapping logic
//
//        // Verify interactions
//        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, picId);
//        verify(modelMapper, atLeastOnce()).map(any(Issue.class), eq(IssueDto.class));
//    }
//
//    @Test
//    void updateStatusOfIssueList() {
//
//    }
//
//    @Test
//    void testGetUnsolvedIssues() {
//        List<Issue> issues = new ArrayList<>();
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        issues.add(issue);
//        when(issueRepository.findAllBySolvedFalseAndPicId(1L)).thenReturn(issues);
//        List<Issue> issues1 = issueRepository.findAllBySolvedFalseAndPicId(1L);
//        assertEquals(1, issues1.size());
//        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(1L);
//    }
//
//    @Test
//    void countIssuesByProjectManagerId() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//
//        // Mock data
//        long projectManagerId = 1L;
//        Issue issue = new Issue();
//        issue.setId(1L);
//        issue.setIssueStatus(IssueStatus.PENDING);
//        issue.setPic(createSampleUser());
//        issue.setProject(createSampleProject());
//        List<Issue> issues = Collections.singletonList(issue);
//
//        // Mock repository behavior
//        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, projectManagerId)).thenReturn(issues);
//
//        // Act
//        long result = issueService.countIssuesByProjectManagerId(projectManagerId);
//
//        // Assert
//        assertEquals(1, result, "Expected one issue in the result");
//
//        // Verify interactions
//        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, projectManagerId);
//    }
//
//    @Test
//    void testGetIssuesByStatus() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        when(issueRepository.findByIssueStatus(IssueStatus.OPEN)).thenReturn(Arrays.asList(issue));
//        List<Issue> issues = issueRepository.findByIssueStatus(IssueStatus.OPEN);
//        assertEquals(1, issues.size());
//        verify(issueRepository, times(1)).findByIssueStatus(IssueStatus.OPEN);
//    }
//
//    @Test
//    void testGetIssuesBySolvedStatus() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        when(issueRepository.findAllBySolvedFalseAndPicId(1L)).thenReturn(Arrays.asList(issue));
//        List<Issue> issues = issueRepository.findAllBySolvedFalseAndPicId(1L);
//        assertEquals(1, issues.size());
//        verify(issueRepository, times(1)).findAllBySolvedFalseAndPicId(1L);
//    }
//
//    @Test
//    void testGetIssuesByCategory() {
//        Issue issue = new Issue();
//        issue.setTitle("Test");
//        issue.setDescription("Test");
//        issue.setPlace("Test");
//        issue.setImpact("Test");
//        issue.setRoot_cause("Test");
//        issue.setDirect_cause("Test");
//        issue.setCorrective_action("Test");
//        issue.setPreventive_action("Test");
//        issue.setSolved(true);
//        Date createdDate = new Date(System.currentTimeMillis());
//        Date updatedDate = new Date(System.currentTimeMillis());
//        issue.setCreated_date(createdDate.getTime());
//        issue.setUpdated_date(updatedDate.getTime());
//        issue.setIssueCategory(Category.TESTING);
//        issue.setProject(new Project());
//        issue.setUser_uploader(new User());
//        issue.setPic(new User());
//        when(issueRepository.findByIssueCategory(Category.TESTING)).thenReturn(Arrays.asList(issue));
//        List<Issue> issues = issueRepository.findByIssueCategory(Category.TESTING);
//        assertEquals(1, issues.size());
//        verify(issueRepository, times(1)).findByIssueCategory(Category.TESTING);
//    }
//
//    @Test
//    void testUpdateStatusOfIssueList() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//
//        // Mock data
//        List<IssueDto> inputIssues = Arrays.asList(
//                createIssueDto(1L, "IN_PROGRESS"),
//                createIssueDto(2L, "IN_PROGRESS"),
//                createIssueDto(3L, "PENDING")
//        );
//
//        // Mock repository behavior
//        when(issueRepository.findById(1L)).thenReturn(createIssue(1L, "IN_PROGRESS"));
//        when(issueRepository.findById(2L)).thenReturn(createIssue(2L, "IN_PROGRESS"));
//        when(issueRepository.findById(3L)).thenReturn(createIssue(3L, "PENDING"));
//
//        // Mock mappings
//        when(modelMapper.map(any(Issue.class), eq(IssueDto.class))).thenAnswer(invocation -> {
//            Issue issue = invocation.getArgument(0);
//            IssueDto issueDto = new IssueDto();
//            issueDto.setId(issue.getId());
//            issueDto.setStatus(issue.getIssueStatus().toString());
//
//            return issueDto;
//        });
//
//        // Act
//        List<IssueDto> updatedIssues = issueService.updateStatusOfIssueList(inputIssues);
//
//        // Check the updated status of each issue
//        assertEquals("IN_PROGRESS", updatedIssues.get(0).getStatus());
//        assertEquals("IN_PROGRESS", updatedIssues.get(1).getStatus());
//
//        // Check and handle the case where the third IssueDto is null
//        IssueDto thirdIssueDto = updatedIssues.get(2);
//        assertNotNull(thirdIssueDto, "The third IssueDto should not be null");
//        assertEquals("PENDING", thirdIssueDto.getStatus());
//
//        // Alternatively, you can use the following approach
//        for (IssueDto issueDto : updatedIssues) {
//            assertNotNull(issueDto, "IssueDto should not be null");
//            assertNotNull(issueDto.getStatus(), "Status should not be null");
//        }
//
//        // Verify interactions
//        verify(issueRepository, times(3)).findById(anyLong());
//        verify(issueRepository, times(3)).save(any(Issue.class));
//        verify(modelMapper, times(3)).map(any(Issue.class), eq(IssueDto.class));
//    }
//
//
//// Helper methods...
//
//    private Issue createIssue(long id, String status) {
//        Issue issue = new Issue();
//        issue.setId(id);
//        issue.setIssueStatus(IssueStatus.valueOf(status));
//        // Set other fields as needed
//        return issue;
//    }
//
//    private IssueDto createIssueDto(long id, String status) {
//        IssueDto issueDto = new IssueDto();
//        issueDto.setId(id);
//
//        // Log the status to identify the problematic value
//        System.out.println("Provided status: " + status);
//
//        // Check if the provided status is a valid enum constant
//        try {
//            IssueStatus issueStatus = IssueStatus.valueOf(status.toUpperCase());
//            issueDto.setStatus(issueStatus.toString());
//        } catch (IllegalArgumentException e) {
//            // Handle the case where the status is not a valid enum constant
//            throw new IllegalArgumentException("Invalid IssueStatus: " + status);
//        }
//
//        // Set other fields as needed
//        return issueDto;
//    }
//
////    @Test
////    void testGetIssuesBySolvedStatus() {
////        // Arrange
////        MockitoAnnotations.openMocks(this);
////
////        // Mock data
////        Issue issue1 = new Issue();
////        issue1.setId(1L);
////        issue1.setSolved(true);
////        User pic1 = new User();
////        pic1.setId(101L);
////        issue1.setPic(pic1);
////
////        Issue issue2 = new Issue();
////        issue2.setId(2L);
////        issue2.setSolved(false);
////        User pic2 = new User();
////        pic2.setId(102L);
////        issue2.setPic(pic2);
////
////        // Mock repository behavior
////        when(issueRepository.findAll()).thenReturn(Arrays.asList(issue1, issue2));
////
////        // Mock modelMapper behavior
////        when(modelMapper.map(issue1, IssueDto.class)).thenReturn(new IssueDto());
////        when(modelMapper.map(issue1.getPic(), UserDto.class)).thenReturn(new UserDto());
////
////        when(modelMapper.map(issue2, IssueDto.class)).thenReturn(new IssueDto());
////        when(modelMapper.map(issue2.getPic(), UserDto.class)).thenReturn(new UserDto());
////
////        // Act
////        List<IssueDto> result1 = issueService.getIssuesBySolvedStatus(true);
////        List<IssueDto> result2 = issueService.getIssuesBySolvedStatus(false);
////
////        // Logging for debugging
////        System.out.println("Result 1: " + result1);
////        System.out.println("Result 2: " + result2);
////
////        // Assert
////        assertEquals(1, result1.size(), "Expected one issue for solved status true");
////        assertEquals(1, result2.size(), "Expected one issue for solved status false");
////
////        // Verify interactions
////        verify(issueRepository, times(1)).findByIssueCategory(Category.BUG);  // Make sure findByIssueCategory is called once
////        verify(modelMapper, times(1)).map(any(), eq(IssueDto.class));  // Check mapping interactions
////        verify(modelMapper, times(1)).map(any(), eq(UserDto.class));
////        // Add any other necessary verifications based on your actual service implementation
////    }
//
//
////    @Test
////    void testGetIssuesByCategory() {
////        // Arrange
////        MockitoAnnotations.openMocks(this);
////
////        String category = "BUG"; // Replace with the actual category you want to test
////
////        // Mock data
////        Issue issue1 = new Issue();
////        issue1.setId(1L);
////        issue1.setIssueCategory(Category.BUG);
////        User pic1 = new User();
////        pic1.setId(101L);
////        issue1.setPic(pic1);
////
////        Issue issue2 = new Issue();
////        issue2.setId(2L);
////        issue2.setIssueCategory(Category.BUG);
////        User pic2 = new User();
////        pic2.setId(102L);
////        issue2.setPic(pic2);
////
////        // Mock repository behavior
////        when(issueRepository.findByIssueCategory(Category.BUG)).thenReturn(Arrays.asList(issue1));
////
////        // Mock modelMapper behavior
////        when(modelMapper.map(issue1, IssueDto.class)).thenReturn(new IssueDto());
////        when(modelMapper.map(issue1.getPic(), UserDto.class)).thenReturn(new UserDto());
////
////        // Act
////        List<IssueDto> result = issueService.getIssuesByCategory(category);
////
////        // Logging for debugging
////        System.out.println("Result: " + result);
////
////        // Assert
////        assertEquals(1, result.size(), "Expected one issue for the specified category");
////
////        // Verify interactions
////        verify(issueRepository, times(1)).findByIssueCategory(Category.BUG);  // Make sure findByIssueCategory is called once
////        verify(modelMapper, times(1)).map(any(), eq(IssueDto.class));  // Check mapping interactions
////        verify(modelMapper, times(1)).map(any(), eq(UserDto.class));
////
////
////    }
//
//
//
//}

package team.placeholder.internalprojectsmanagementsystem.service.impl.issue;


import ch.qos.logback.classic.Logger;
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

import java.util.*;

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

    // Existing test cases...


    private IsuDto validIsuDto;
    private Issue validIssue;
    private User validUser;
    private Project validProject;

    @BeforeEach
    void setUp() {
        validUser = new User();
        validUser.setId(1L);
        validUser.setName("John Doe");
        validUser.setEmail("john.doe@example.com");

        validProject = new Project();
        validProject.setId(1L);
        validProject.setName("Project Name");

        validIsuDto = new IsuDto();
        validIsuDto.setTitle("Issue Title");
        validIsuDto.setDescription("Issue Description");
        validIsuDto.setPlace("Issue Place");
        validIsuDto.setImpact("Issue Impact");
        validIsuDto.setRoot_cause("Root Cause");
        validIsuDto.setDirect_cause("Direct Cause");
        validIsuDto.setCorrective_action("Corrective Action");
        validIsuDto.setPreventive_action("Preventive Action");
        validIsuDto.setSolved(false);
        validIsuDto.setCreated_date(System.currentTimeMillis());
        validIsuDto.setUpdated_date(System.currentTimeMillis());
        validIsuDto.setSolved_date(System.currentTimeMillis());
        validIsuDto.setResponsible_type("EMPLOYEE");
        validIsuDto.setIssueCategory("BUG");
        validIsuDto.setStatus("PENDING");
        validIsuDto.setUser_uploader(1L);
        validIsuDto.setUser_pic(1L);
        validIsuDto.setResponsible_party(1L);
        validIsuDto.setProject_id(1L);

        validIssue = new Issue();
        validIssue.setId(1L);
        validIssue.setTitle(validIsuDto.getTitle());
        validIssue.setDescription(validIsuDto.getDescription());
        validIssue.setPlace(validIsuDto.getPlace());
        validIssue.setImpact(validIsuDto.getImpact());
        validIssue.setRoot_cause(validIsuDto.getRoot_cause());
        validIssue.setDirect_cause(validIsuDto.getDirect_cause());
        validIssue.setCorrective_action(validIsuDto.getCorrective_action());
        validIssue.setPreventive_action(validIsuDto.getPreventive_action());
        validIssue.setSolved(validIsuDto.isSolved());
        validIssue.setCreated_date(validIsuDto.getCreated_date());
        validIssue.setUpdated_date(validIsuDto.getUpdated_date());
        validIssue.setSolved_date(validIsuDto.getSolved_date());
        validIssue.setIssueStatus(IssueStatus.valueOf(validIsuDto.getStatus()));
        validIssue.setIssueCategory(Category.valueOf(validIsuDto.getIssueCategory()));
        validIssue.setResponsible_type(ResponsibleType.valueOf(validIsuDto.getResponsible_type()));
        validIssue.setUser_uploader(validUser);
        validIssue.setPic(validUser);
        validIssue.setResponsible_party(validUser.getId());
        validIssue.setProject(validProject);
    }



    @Test
    void testGetAllIssuesWhenInputIsEmptyThenReturnZero() {
        // Arrange
        when(issueRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<IssueDto> result = issueService.getAllIssues();

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(0, result.size(), "The result list should be empty");

        // Verify interactions
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    void testGetIssueByIdWhenInputIsValidThenReturnResult() {
        // Arrange
        long issueId = 1L;
        when(issueRepository.findById(issueId)).thenReturn(validIssue);
        when(modelMapper.map(any(Issue.class), eq(IssueDto.class))).thenReturn(new IssueDto());

        // Act
        IssueDto result = issueService.getIssueById(issueId);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(validIssue.getTitle(), result.getTitle(), "The title should match the input");
        assertEquals(validIssue.getDescription(), result.getDescription(), "The description should match the input");
        // Add more assertions as necessary to validate the result

        // Verify interactions
        verify(issueRepository, times(1)).findById(issueId);
        verify(modelMapper, times(1)).map(any(Issue.class), eq(IssueDto.class));
    }

    @Test
    void testUpdateIssueWhenInputIsValidThenReturnResult() {
        // Arrange
        IssueDto issueDtoToUpdate = new IssueDto();
        issueDtoToUpdate.setId(validIssue.getId());
        issueDtoToUpdate.setTitle("Updated Title");
        issueDtoToUpdate.setDescription("Updated Description");
        when(issueRepository.findById(validIssue.getId())).thenReturn(validIssue);
        when(issueRepository.save(any(Issue.class))).thenReturn(validIssue);
        when(modelMapper.map(any(Issue.class), eq(IssueDto.class))).thenReturn(issueDtoToUpdate);

        // Act
        IssueDto result = issueService.updateIssue(issueDtoToUpdate);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(issueDtoToUpdate.getTitle(), result.getTitle(), "The title should be updated");
        assertEquals(issueDtoToUpdate.getDescription(), result.getDescription(), "The description should be updated");
        // Add more assertions as necessary to validate the result

        // Verify interactions
        verify(issueRepository, times(1)).findById(validIssue.getId());
        verify(issueRepository, times(1)).save(any(Issue.class));
        verify(modelMapper, times(1)).map(any(Issue.class), eq(IssueDto.class));
    }


    @Test
    void testCalculateTotalWhenInputIsEmptyThenReturnZero() {
        // Arrange
        when(issueRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<IssueDto> result = issueService.getAllIssues();

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(0, result.size(), "The result list should be empty");
    }


    // New test cases...

    // Helper methods...

    private Issue createPendingIssue() {
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
        issue.setIssueStatus(IssueStatus.PENDING);
        issue.setSolved(true);
        issue.setCreated_date(new Date().getTime());
        issue.setUpdated_date(new Date().getTime());
        issue.setIssueCategory(Category.TESTING);
        issue.setProject(createSampleProject());
        issue.setUser_uploader(createSampleUser());
        issue.setPic(createSampleUser());
        issue.setResponsible_party(createSampleUser().getId());
        issue.setResponsible_type(ResponsibleType.CLIENT);
        return issue;
    }

    private User createSampleUser() {
        User user = new User();
        user.setId(1L);
        user.setName("john.doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setEnabled(true);
        user.setRole(Role.EMPLOYEE);
        return user;
    }

    private Project createSampleProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");
        project.setBackground("Sample Background");
        project.setDuration(1);
        project.setStart_date(new Date().getTime());
        project.setEnd_date(new Date().getTime());
        project.setObjective("Sample Objective");
        project.setClosed(false);
        return project;
    }

    @Test
    void testGetPendingIssueListWhenUserIdProvidedThenReturnPendingIssues() {
        // Arrange
        long userId = 1L;
        Issue pendingIssue = createPendingIssue();
        List<Issue> pendingIssueList = Collections.singletonList(pendingIssue);

        // Mock repository behavior
        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, userId)).thenReturn(pendingIssueList);

        // Act
        List<IssueDto> issueDtos = issueService.getPendingIssueList(userId);

        // Assert
        assertEquals(1, issueDtos.size(), "Expected one pending issue in the result");

        IssueDto resultIssueDto = issueDtos.get(0);
        assertNotNull(resultIssueDto.getResponsible_party(), "Responsible party should not be null");
        assertNotNull(resultIssueDto.getUser_pic(), "User pic should not be null");
        assertNotNull(resultIssueDto.getUser_uploader(), "User uploader should not be null");
        assertNotNull(resultIssueDto.getProjectDto(), "ProjectDto should not be null");

        // Update this assertion to check the status property
        assertEquals(IssueStatus.PENDING.toString(), resultIssueDto.getStatus(), "Status should be PENDING");

        // Verify interactions
        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, userId);
        verify(modelMapper, atLeastOnce()).map(any(Issue.class), eq(IssueDto.class));
    }



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
    void testGetPendingIssueList() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Mock data
        long picId = 1L;
        Issue pendingIssue = createPendingIssue();
        List<Issue> pendingIssueList = Collections.singletonList(pendingIssue);

        // Mock repository behavior
        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, picId)).thenReturn(pendingIssueList);

        // Mock mappings
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
        when(modelMapper.map(any(Project.class), eq(ProjectDto.class))).thenReturn(new ProjectDto());

        // Act
        List<IssueDto> issueDtos = issueService.getPendingIssueList(picId);

        // Assert
        assertEquals(1, issueDtos.size(), "Expected one pending issue in the result");

        IssueDto resultIssueDto = issueDtos.get(0);
        assertNotNull(resultIssueDto.getResponsible_party(), "Responsible party should not be null");
        assertNotNull(resultIssueDto.getUser_pic(), "User pic should not be null");
        assertNotNull(resultIssueDto.getUser_uploader(), "User uploader should not be null");
        assertNotNull(resultIssueDto.getProjectDto(), "ProjectDto should not be null");
        assertEquals(IssueStatus.PENDING.toString(), resultIssueDto.getStatus(), "IssueStatus should be PENDING");

        // You can add more specific assertions based on your mapping logic

        // Verify interactions
        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, picId);
        verify(modelMapper, atLeastOnce()).map(any(Issue.class), eq(IssueDto.class));
    }

    @Test
    void updateStatusOfIssueList() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Mock data
        List<IssueDto> inputIssues = Arrays.asList(
                createIssueDto(1L, "IN_PROGRESS"),
                createIssueDto(2L, "IN_PROGRESS"),
                createIssueDto(3L, "PENDING")
        );

        // Mock repository behavior
        when(issueRepository.findById(1L)).thenReturn(createIssue(1L, "IN_PROGRESS"));
        when(issueRepository.findById(2L)).thenReturn(createIssue(2L, "IN_PROGRESS"));
        when(issueRepository.findById(3L)).thenReturn(createIssue(3L, "PENDING"));

        // Mock mappings
        when(modelMapper.map(any(Issue.class), eq(IssueDto.class))).thenAnswer(invocation -> {
            Issue issue = invocation.getArgument(0);
            IssueDto issueDto = new IssueDto();
            issueDto.setId(issue.getId());
            issueDto.setStatus(issue.getIssueStatus().toString());

            return issueDto;
        });

        // Act
        List<IssueDto> updatedIssues = issueService.updateStatusOfIssueList(inputIssues);

        // Assert
        assertEquals("IN_PROGRESS", updatedIssues.get(0).getStatus());
        assertEquals("IN_PROGRESS", updatedIssues.get(1).getStatus());

        IssueDto thirdIssueDto = updatedIssues.get(2);
        assertNotNull(thirdIssueDto, "The third IssueDto should not be null");
        assertEquals("PENDING", thirdIssueDto.getStatus());

        // Alternatively, you can use the following approach
        for (IssueDto issueDto : updatedIssues) {
            assertNotNull(issueDto, "IssueDto should not be null");
            assertNotNull(issueDto.getStatus(), "Status should not be null");
        }

        // Verify interactions
        verify(issueRepository, times(3)).findById(anyLong());
        verify(issueRepository, times(3)).save(any(Issue.class));
        verify(modelMapper, times(3)).map(any(Issue.class), eq(IssueDto.class));

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
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Mock data
        long projectManagerId = 1L;
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setIssueStatus(IssueStatus.PENDING);
        issue.setPic(createSampleUser());
        issue.setProject(createSampleProject());
        List<Issue> issues = Collections.singletonList(issue);

        // Mock repository behavior
        when(issueRepository.findAllByIssueStatusAndPicId(IssueStatus.PENDING, projectManagerId)).thenReturn(issues);

        // Act
        long result = issueService.countIssuesByProjectManagerId(projectManagerId);

        // Assert
        assertEquals(1, result, "Expected one issue in the result");

        // Verify interactions
        verify(issueRepository, times(1)).findAllByIssueStatusAndPicId(IssueStatus.PENDING, projectManagerId);
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

    @Test
    void testUpdateStatusOfIssueList() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Mock data
        List<IssueDto> inputIssues = Arrays.asList(
                createIssueDto(1L, "IN_PROGRESS"),
                createIssueDto(2L, "IN_PROGRESS"),
                createIssueDto(3L, "PENDING")
        );

        // Mock repository behavior
        when(issueRepository.findById(1L)).thenReturn(createIssue(1L, "IN_PROGRESS"));
        when(issueRepository.findById(2L)).thenReturn(createIssue(2L, "IN_PROGRESS"));
        when(issueRepository.findById(3L)).thenReturn(createIssue(3L, "PENDING"));

        // Mock mappings
        when(modelMapper.map(any(Issue.class), eq(IssueDto.class))).thenAnswer(invocation -> {
            Issue issue = invocation.getArgument(0);
            IssueDto issueDto = new IssueDto();
            issueDto.setId(issue.getId());
            issueDto.setStatus(issue.getIssueStatus().toString());

            return issueDto;
        });

        // Act
        List<IssueDto> updatedIssues = issueService.updateStatusOfIssueList(inputIssues);

        // Check the updated status of each issue
        assertEquals("IN_PROGRESS", updatedIssues.get(0).getStatus());
        assertEquals("IN_PROGRESS", updatedIssues.get(1).getStatus());

        // Check and handle the case where the third IssueDto is null
        IssueDto thirdIssueDto = updatedIssues.get(2);
        assertNotNull(thirdIssueDto, "The third IssueDto should not be null");
        assertEquals("PENDING", thirdIssueDto.getStatus());

        // Alternatively, you can use the following approach
        for (IssueDto issueDto : updatedIssues) {
            assertNotNull(issueDto, "IssueDto should not be null");
            assertNotNull(issueDto.getStatus(), "Status should not be null");
        }

        // Verify interactions
        verify(issueRepository, times(3)).findById(anyLong());
        verify(issueRepository, times(3)).save(any(Issue.class));
        verify(modelMapper, times(3)).map(any(Issue.class), eq(IssueDto.class));
    }


// Helper methods...

    private Issue createIssue(long id, String status) {
        Issue issue = new Issue();
        issue.setId(id);
        issue.setIssueStatus(IssueStatus.valueOf(status));
        // Set other fields as needed
        return issue;
    }

    private IssueDto createIssueDto(long id, String status) {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(id);

        // Log the status to identify the problematic value
        System.out.println("Provided status: " + status);

        // Check if the provided status is a valid enum constant
        try {
            IssueStatus issueStatus = IssueStatus.valueOf(status.toUpperCase());
            issueDto.setStatus(issueStatus.toString());
        } catch (IllegalArgumentException e) {
            // Handle the case where the status is not a valid enum constant
            throw new IllegalArgumentException("Invalid IssueStatus: " + status);
        }

        // Set other fields as needed
        return issueDto;
    }


//    @Test
//    void testGetIssuesByCategory() {
//        // Arrange
//        MockitoAnnotations.openMocks(this);
//
//        String category = "BUG"; // Replace with the actual category you want to test
//
//        // Mock data
//        Issue issue1 = new Issue();
//        issue1.setId(1L);
//        issue1.setIssueCategory(Category.BUG);
//        User pic1 = new User();
//        pic1.setId(101L);
//        issue1.setPic(pic1);
//
//        Issue issue2 = new Issue();
//        issue2.setId(2L);
//        issue2.setIssueCategory(Category.BUG);
//        User pic2 = new User();
//        pic2.setId(102L);
//        issue2.setPic(pic2);
//
//        // Mock repository behavior
//        when(issueRepository.findByIssueCategory(Category.BUG)).thenReturn(Arrays.asList(issue1));
//
//        // Mock modelMapper behavior
//        when(modelMapper.map(issue1, IssueDto.class)).thenReturn(new IssueDto());
//        when(modelMapper.map(issue1.getPic(), UserDto.class)).thenReturn(new UserDto());
//
//        // Act
//        List<IssueDto> result = issueService.getIssuesByCategory(category);
//
//        // Logging for debugging
//        System.out.println("Result: " + result);
//
//        // Assert
//        assertEquals(1, result.size(), "Expected one issue for the specified category");
//
//        // Verify interactions
//        verify(issueRepository, times(1)).findByIssueCategory(Category.BUG);  // Make sure findByIssueCategory is called once
//        verify(modelMapper, times(1)).map(any(), eq(IssueDto.class));  // Check mapping interactions
//        verify(modelMapper, times(1)).map(any(), eq(UserDto.class));
//
//
//    }



}