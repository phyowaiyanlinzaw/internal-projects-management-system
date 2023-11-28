package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ArchitectureRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private DeliverableRepository deliverableRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ArchitectureRepository architectureRepository;

    @Mock
    private DeliverableTypeServiceImpl deliverableTypeService;

    @Mock
    private ArchitectureServiceImpl architectureService;

    @Mock
    private AESImpl aes;

    @Mock
    private UserService userService;

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private ProjectServiceImpl projectService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Arrange
        ProjectDto projectDto = createSampleProjectDto(); // create a sample DTO for testing

        // Mocking behavior for ArchitectureService
        when(architectureService.save(any(ArchitectureDto.class))).thenReturn(createSampleArchitecture());

        // Mocking behavior for DeliverableTypeService
        when(deliverableTypeService.save(any(DeliverableTypeDto.class))).thenReturn(createSampleDeliverableType());

        // Mocking behavior for UserRepository
        when(userRepository.getReferenceById(anyLong())).thenReturn(createSampleUser());

        // Mocking behavior for ModelMapper
        when(modelMapper.map(any(), any())).thenReturn(createSampleObject()); // adjust as needed

        // Act
        ProjectDto result = projectService.save(projectDto);

        // Assert
        assertNotNull(result);
        assertEquals(projectDto.getName(), result.getName());
        // Add more assertions based on your expectations

        // Verify other method invocations as needed
        verify(aes, times(projectDto.getMembersUserDto().size())).getAvailableUserByUserId(anyLong());
        verify(notificationService, times(projectDto.getMembersUserDto().size()))
                .save(eq("You have been assigned to new project"), anyLong(), eq("project-save-event"));

        // Add more assertions based on your expectations
    }

    private ProjectDto createSampleProjectDto() {
       ProjectDto projectDto = new ProjectDto();
        projectDto.setName("TestProject");
        projectDto.setObjective("TestObjective");
        projectDto.setStart_date(1637347200000L);
        projectDto.setEnd_date(1637347200000L);
        projectDto.setDuration(30);
        projectDto.setBackground("TestBackground");
        projectDto.setClientDto(new ClientDto());
        projectDto.setDepartmentDto(new DepartmentDto());
        projectDto.setProjectManagerUserDto(new UserDto());
        projectDto.setAmountDto(new AmountDto());
        projectDto.setArchitectureDto(new HashSet<>(Arrays.asList(new ArchitectureDto())));
        projectDto.setReviewDto(new ReviewDto());
        projectDto.setSystemOutLineDto(new SystemOutLineDto());
        projectDto.setDeliverableDto(Arrays.asList(new DeliverableDto()));
        projectDto.setCompleteTaskCount(1L);
        projectDto.setTotalTaskCount(1L);
        projectDto.setClosed(true);
        projectDto.setMembersUserDto(Arrays.asList(new UserDto()));
        projectDto.setTasksDto(Arrays.asList(new TasksDto()));
        projectDto.setIssueDto(Arrays.asList(new IssueDto()));
        return projectDto;



    }

    private Architecture createSampleArchitecture() {

        Architecture architecture = new Architecture();
        architecture.setTech_name("TestArchitecture");
        return architecture;
    }

    private DeliverableType createSampleDeliverableType() {
        DeliverableType deliverableType = new DeliverableType();
        deliverableType.setType("TestDeliverableType");
        return deliverableType;
    }

    private User createSampleUser() {
        User user = new User();
        user.setName("TestUser");
        return user;
    }

    private Object createSampleObject() {
        return new Object();
    }



    @Test
    public void testGetAllProjectsEmptyList() {
        // Arrange
        when(projectRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<ProjectDto> result = projectService.getAllProjects();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify interactions
        verify(projectRepository, times(1)).findAll();
        // Verify other interactions as needed
    }

    @Test
    void testGetAllProjects() {

    }

    @Test
    void getProjectByName() {
        String projectName = "TestProject";
        Project project = new Project();
        project.setName(projectName);

        when(projectRepository.findByName(projectName)).thenReturn(project);
        when(modelMapper.map(project, ProjectDto.class)).thenReturn(new ProjectDto());

        // Act
        ProjectDto result = projectService.getProjectByName(projectName);

        // Assert
        // Add assertions based on the expected behavior

        // Verify that projectRepository.findByName is called
        verify(projectRepository, times(1)).findByName(projectName);

        // Verify that modelMapper.map is called with the correct arguments
        verify(modelMapper, times(1)).map(project, ProjectDto.class);

        // Assert the expected result
        assertEquals(ProjectDto.class, result.getClass());
    }

    @Test
    void updateProject() {
        long projectId = 1L;
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectId);
        projectDto.setName("UpdatedProjectName");
        // Set other fields accordingly

        Project existingProject = new Project();
        existingProject.setId(projectId);
        // Set other fields accordingly

        when(projectRepository.findById(projectId)).thenReturn(existingProject);
        when(projectRepository.save(existingProject)).thenReturn(existingProject);
        when(modelMapper.map(existingProject, ProjectDto.class)).thenReturn(new ProjectDto());

        // Act
        ProjectDto result = projectService.updateProject(projectDto);

        // Assert
        // Add assertions based on the expected behavior

        // Verify that projectRepository.findById is called
        verify(projectRepository, times(1)).findById(projectId);

        // Verify that projectRepository.save is called with the correct argument
        verify(projectRepository, times(1)).save(existingProject);

        // Verify that modelMapper.map is called with the correct arguments
        verify(modelMapper, times(1)).map(existingProject, ProjectDto.class);

        // Assert the expected result
        assertEquals(ProjectDto.class, result.getClass());
    }

    @Test
    void countAllProjects() {

        long expectedCount = 10L; // Set the expected count based on your scenario

        // Mock the behavior of the projectRepository
        when(projectRepository.count()).thenReturn(expectedCount);

        // Act
        long result = projectService.countAllProjects();

        // Assert
        // Verify that projectRepository.count() is called
        verify(projectRepository, times(1)).count();

        // Assert the expected result
        assertEquals(expectedCount, result);
    }

    @Test
    void countAllProjectsByUsersId() {
        long userId = 1L; // Set the user ID based on your scenario
        long expectedCount = 5L; // Set the expected count based on your scenario

        // Mock the behavior of the projectRepository
        when(projectRepository.countAllByUsersId(userId)).thenReturn(expectedCount);

        // Act
        long result = projectService.countAllProjectsByUsersId(userId);

        // Assert
        // Verify that projectRepository.countAllByUsersId() is called with the correct parameter
        verify(projectRepository, times(1)).countAllByUsersId(userId);

        // Assert the expected result
        assertEquals(expectedCount, result);
    }

    @Test
    void testGetAllProjectsByProjectManagerIdWhenManagerIdIsGivenThenReturnListOfProjects() {
        // Arrange
        long managerId = 1L;
        List<Project> mockProjectList = Arrays.asList(new Project(), new Project(), new Project());
        when(projectRepository.findAllByProjectManagerId(managerId)).thenReturn(mockProjectList);

        // Mocking modelMapper mappings
        when(modelMapper.map(any(Project.class), eq(ProjectDto.class))).thenReturn(new ProjectDto());
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
        when(modelMapper.map(any(Deliverable.class), eq(DeliverableDto.class))).thenReturn(new DeliverableDto());
        when(modelMapper.map(any(DeliverableType.class), eq(DeliverableTypeDto.class))).thenReturn(new DeliverableTypeDto());
        when(modelMapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(new DepartmentDto());
        when(modelMapper.map(any(Amount.class), eq(AmountDto.class))).thenReturn(new AmountDto());
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
        when(modelMapper.map(any(Review.class), eq(ReviewDto.class))).thenReturn(new ReviewDto());
        when(modelMapper.map(any(SystemOutLine.class), eq(SystemOutLineDto.class))).thenReturn(new SystemOutLineDto());

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByProjectManagerId(managerId);

        // Assert
        assertNotNull(result);
        assertEquals(mockProjectList.size(), result.size());

        // Log additional information for troubleshooting
        for (Project project : mockProjectList) {
            System.out.println("Project: " + (project != null ? project.getName() : "null"));

            if (project != null && project.getProjectManager() != null) {
                System.out.println("Project manager: " + project.getProjectManager().getName());
            } else {
                System.out.println("Project manager: null");
            }

            System.out.println("Deliverables: " + (project != null && project.getDeliverables() != null ? project.getDeliverables().size() : "null"));
            System.out.println("Users: " + (project != null && project.getUsers() != null ? project.getUsers().size() : "null"));
            System.out.println("Tasks: " + (project != null && project.getTasks() != null ? project.getTasks().size() : "null"));
        }
    }

    @Test
    void testGetAllProjectsByProjectManagerIdWhenNoProjectsThenReturnEmptyList() {
        // Arrange
        long managerId = 1L;
        when(projectRepository.findAllByProjectManagerId(managerId)).thenReturn(Collections.emptyList());

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByProjectManagerId(managerId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify interactions
        verify(projectRepository, times(1)).findAllByProjectManagerId(managerId);
    }

    @Test
    void testGetAllProjectsByProjectManagerIdWhenIdDoesNotExistThenReturnEmptyList() {
        // Arrange
        long managerId = 1L;
        when(projectRepository.findAllByProjectManagerId(managerId)).thenReturn(Collections.emptyList());

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByProjectManagerId(managerId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify interactions
        verify(projectRepository, times(1)).findAllByProjectManagerId(managerId);
    }

    @Test
    void testGetAllProjectsByProjectManagerIdWhenCompleteTaskCountIsCalculated() {
        // Arrange
        long managerId = 1L;
        List<Project> mockProjectList = Arrays.asList(new Project(), new Project(), new Project());
        when(projectRepository.findAllByProjectManagerId(managerId)).thenReturn(mockProjectList);

        // Mocking modelMapper mappings
        when(modelMapper.map(any(Project.class), eq(ProjectDto.class))).thenReturn(new ProjectDto());
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
        when(modelMapper.map(any(Deliverable.class), eq(DeliverableDto.class))).thenReturn(new DeliverableDto());
        when(modelMapper.map(any(DeliverableType.class), eq(DeliverableTypeDto.class))).thenReturn(new DeliverableTypeDto());
        when(modelMapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(new DepartmentDto());
        when(modelMapper.map(any(Amount.class), eq(AmountDto.class))).thenReturn(new AmountDto());
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
        when(modelMapper.map(any(Review.class), eq(ReviewDto.class))).thenReturn(new ReviewDto());
        when(modelMapper.map(any(SystemOutLine.class), eq(SystemOutLineDto.class))).thenReturn(new SystemOutLineDto());

        // Mocking taskRepository
        when(taskRepository.countByProjectIdAndDeletedFalse(anyLong())).thenReturn(5L);

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByProjectManagerId(managerId);

        // Assert
        assertNotNull(result);
        assertEquals(mockProjectList.size(), result.size());

        // Log additional information for troubleshooting
        for (Project project : mockProjectList) {
            System.out.println("Project: " + (project != null ? project.getName() : "null"));

            if (project != null && project.getProjectManager() != null) {
                System.out.println("Project manager: " + project.getProjectManager().getName());
            } else {
                System.out.println("Project manager: null");
            }

            System.out.println("Deliverables: " + (project != null && project.getDeliverables() != null ? project.getDeliverables().size() : "null"));
            System.out.println("Users: " + (project != null && project.getUsers() != null ? project.getUsers().size() : "null"));
            System.out.println("Tasks: " + (project != null && project.getTasks() != null ? project.getTasks().size() : "null"));
            System.out.println("Complete Task Count: " + (project != null && project.getTasks() != null ? project.getTasks().stream()
                    .filter(task -> task.getStatus().equals(TaskStatus.FINISHED) && !task.isDeleted())
                    .count() : "null"));
        }
    }

    @Test
    void testGetAllProjectsByDepartmentIdWhenDepartmentIdIsGivenThenReturnListOfProjects() {
        // Arrange
        long departmentId = 1L;
        List<Project> mockProjectList = Arrays.asList(new Project(), new Project(), new Project());
        when(projectRepository.findByDepartmentId(departmentId)).thenReturn(mockProjectList);

        // Mocking modelMapper mappings
        when(modelMapper.map(any(Project.class), eq(ProjectDto.class))).thenReturn(new ProjectDto());
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
        when(modelMapper.map(any(Deliverable.class), eq(DeliverableDto.class))).thenReturn(new DeliverableDto());
        when(modelMapper.map(any(DeliverableType.class), eq(DeliverableTypeDto.class))).thenReturn(new DeliverableTypeDto());
        when(modelMapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(new DepartmentDto());
        when(modelMapper.map(any(Amount.class), eq(AmountDto.class))).thenReturn(new AmountDto());
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(new ClientDto());
        when(modelMapper.map(any(Review.class), eq(ReviewDto.class))).thenReturn(new ReviewDto());
        when(modelMapper.map(any(SystemOutLine.class), eq(SystemOutLineDto.class))).thenReturn(new SystemOutLineDto());
        when(modelMapper.map(isNull(), eq(UserDto.class))).thenReturn(new UserDto());  // Handle null for UserDto
        when(modelMapper.map(isNull(), eq(DepartmentDto.class))).thenReturn(new DepartmentDto());  // Handle null for DepartmentDto
        // ... add similar lines for other mappings

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByDepartmentId(departmentId);

        // Assert
        assertNotNull(result);
        assertEquals(mockProjectList.size(), result.size());

        // Log additional information for troubleshooting
        for (Project project : mockProjectList) {
            System.out.println("Project: " + (project != null ? project.getName() : "null"));

            if (project != null && project.getProjectManager() != null) {
                System.out.println("Project manager: " + project.getProjectManager().getName());
            } else {
                System.out.println("Project manager: null");
            }

            System.out.println("Deliverables: " + (project != null && project.getDeliverables() != null ? project.getDeliverables().size() : "null"));
            System.out.println("Users: " + (project != null && project.getUsers() != null ? project.getUsers().size() : "null"));
            System.out.println("Tasks: " + (project != null && project.getTasks() != null ? project.getTasks().size() : "null"));
        }
    }

    @Test
    void testGetAllProjectsByDepartmentIdWhenNoProjectsThenReturnEmptyList() {
        // Arrange
        long departmentId = 1L;
        when(projectRepository.findByDepartmentId(departmentId)).thenReturn(Collections.emptyList());

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByDepartmentId(departmentId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify interactionsissue_ledgar
        verify(projectRepository, times(1)).findByDepartmentId(departmentId);
        verifyNoInteractions(modelMapper);
        verifyNoInteractions(taskRepository);
    }

    @Test
    void testGetAllProjectsByDepartmentIdWhenIdDoesNotExistThenReturnEmptyList() {
        // Arrange
        long departmentId = 1L;
        when(projectRepository.findByDepartmentId(departmentId)).thenReturn(Collections.emptyList());

        // Act
        List<ProjectDto> result = projectService.getAllProjectsByDepartmentId(departmentId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Verify interactions
        verify(projectRepository, times(1)).findByDepartmentId(departmentId);
        verifyNoInteractions(modelMapper);
        verifyNoInteractions(taskRepository);
    }

    @Test
    void testCountAllProjectsByDepartmentId() {
        // Arrange
        long departmentId = 1L;
        long expectedCount = 5L;

        // Mock the behavior of projectRepository.countAllByDepartmentId
        when(projectRepository.countAllByDepartmentId(departmentId)).thenReturn(expectedCount);

        // Act
        long result = projectService.countAllProjectsByDepartmentId(departmentId);

        // Assert
        assertEquals(expectedCount, result);

        // Verify interactions
        verify(projectRepository, times(1)).countAllByDepartmentId(departmentId);
    }

    @Test
    void findAllByUserId() {
    }

    @Test
    void getAllProjectsByDepartmentName() {
    }

    @Test
    void countTaskById() {
    }

    @Test
    void getProjectByUsersIdAndStatus() {

    }

    @Test
    void calculateEndDateMillis() {


    }

    @Test
    void convertMillisToLocalDate() {
        long millis = 1637347200000L; // Replace this with the millis value you want to test
        LocalDate expectedLocalDate = LocalDate.of(2021, 11, 20); // Replace this with the expected local date

        LocalDate resultLocalDate = projectService.convertMillisToLocalDate(millis);

        assertEquals(expectedLocalDate, resultLocalDate);
    }

    @Test
    void getAllClientsFromProjects() {
        Project project1 = new Project(); // Create a sample project with a client
        // Add more sample projects as needed

        List<Project> projectsWithClients = Arrays.asList(project1); // Adjust the list based on your scenario
        List<ClientDto> expectedClientDtos = Arrays.asList(new ClientDto()); // Adjust the expected list based on your scenario

        // Mock the behavior of the projectRepository
        when(projectRepository.findAllByClientIsNotNull()).thenReturn(projectsWithClients);

        // Mock the behavior of the modelMapper
        when(modelMapper.map(any(), eq(ClientDto.class))).thenReturn(new ClientDto()); // Adjust the mapping based on your scenario

        // Act
        List<ClientDto> result = projectService.getAllClientsFromProjects();

        // Assert
        // Verify that projectRepository.findAllByClientIsNotNull() is called
        verify(projectRepository, times(1)).findAllByClientIsNotNull();

        // Verify that modelMapper.map() is called for each project
        verify(modelMapper, times(projectsWithClients.size())).map(any(), eq(ClientDto.class));

        // Assert the expected result
        assertEquals(expectedClientDtos, result);
    }

    @Test
    void getAllPM() {

    }

    // ... Other test methods ...
}