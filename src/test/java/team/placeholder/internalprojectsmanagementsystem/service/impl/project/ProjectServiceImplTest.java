package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.*;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ArchitectureServiceImpl architectureService;

    @Mock
    private ArchitectureRepository architectureRepository;

    @Mock
    private DeliverableTypeServiceImpl deliverableTypeService;

    @Mock
    private DeliverableTypeRepo deliverableR;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DeliverableRepository deliverableRepository;

    @Mock
    private ReviewRepo reviewRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AESImpl aes;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProjectServiceImpl projectService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {

    }


    @Test
    void getAllProjects() {

    }


    @Test
    void getProjectById() {
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
    void getAllProjectsByProjectManagerId() {
    }

    @Test
    void getAllProjectsByDepartmentId() {
    }

    @Test
    void countAllProjectsByDepartmentId() {
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



}