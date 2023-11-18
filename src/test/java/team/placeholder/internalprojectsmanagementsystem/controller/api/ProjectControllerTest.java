package team.placeholder.internalprojectsmanagementsystem.controller.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.Amount;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import team.placeholder.internalprojectsmanagementsystem.service.project.TasksService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectServiceImpl projectService;

    @Mock
    private ArchitectureServiceImpl architectureService;

    @Mock
    private DeliverableTypeServiceImpl deliverableTypeService;

    @Mock
    private UserServiceImpl userService;


    @Mock
    private TaskServiceImpl taskService;

    @Mock
    private ProjectRepository projectRepository;



    @Mock
    private FakerService fakerService;

    @Mock
    private Amount amount;

    @Mock
    private Department department;

    @Mock
    private TasksService tasksService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
        void generateFakeProjects() {
            int count = 5;

            doNothing().when(fakerService).generateAndSaveFakeProjects(count);

            ResponseEntity<String> result = projectController.generateFakeProjects(count);

            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals("Projects generated successfully", result.getBody());

            verify(fakerService, times(1)).generateAndSaveFakeProjects(count);

        }

    @Test
    void save() {
        ProjectDto projectDto = new ProjectDto();
        when(projectService.save(projectDto)).thenReturn(projectDto);
        ResponseEntity<ProjectDto> result = projectController.save(projectDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(projectDto, result.getBody());
        verify(projectService, times(1)).save(projectDto);
    }

    @Test
    void saveIsNull() {
        ProjectDto projectDto = new ProjectDto();

        when(projectService.save(projectDto)).thenReturn(null);

        ResponseEntity<ProjectDto> result = projectController.save(projectDto);

        verify(projectService, times(1)).save(projectDto);

        // Assertions
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
    }


    @Test
    void getAllProjects() {
        List<ProjectDto> projectDtos = Arrays.asList(new ProjectDto(), new ProjectDto());
        when(projectService.getAllProjects()).thenReturn(projectDtos);

        ResponseEntity<List<ProjectDto>> result1 = projectController.getAllProjects();
        assertEquals(HttpStatus.OK, result1.getStatusCode());
        assertEquals(projectDtos, result1.getBody());
        verify(projectService, times(1)).getAllProjects();
        ResponseEntity<List<ProjectDto>> result2 = ResponseEntity.ok(projectDtos);
        assertEquals(result1, result2);

    }

    @Test
    void testGetAllProjectsByUserId() {
         long userId = 1L;
        List<ProjectDto> mockProjects = List.of(new ProjectDto(), new ProjectDto());  // Replace with your mock data

        when(projectService.getAllProjectsByProjectManagerId(userId)).thenReturn(mockProjects);
        // Act
        ResponseEntity<List<ProjectDto>> result = projectController.getAllProjectsByUserId(userId);
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockProjects, result.getBody());

        verify(projectService, times(1)).getAllProjectsByProjectManagerId(userId);
    }

    @Test
    void getProjectById() {
        long id = 1L;
        ProjectDto projectDto = new ProjectDto();
        when(projectService.getProjectById(id)).thenReturn(projectDto);

        ResponseEntity<ProjectDto> result1 = projectController.getProjectById(id);
        assertEquals(HttpStatus.OK, result1.getStatusCode());
        assertEquals(projectDto, result1.getBody());
        verify(projectService, times(1)).getProjectById(id);
    }

    @Test
    void testGetProjectByIdNotFound() {
        long nonExistentId = 1L;
        when(projectService.getProjectById(nonExistentId)).thenReturn(null);
        ResponseEntity<ProjectDto> result = projectController.getProjectById(nonExistentId);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(projectService, times(1)).getProjectById(nonExistentId);
    }


    @Test
    void testUpdateAmountById() {
        // Arrange
        long projectId = 1L;
        AmountDto amountDto = new AmountDto();
        Project mockProject = new Project();

        mockProject.setAmount(amount);

        when(projectRepository.getReferenceById(projectId)).thenReturn(mockProject);

        ResponseEntity<ProjectDto> result = projectController.updateAmountById(projectId, amountDto);


        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(projectRepository, times(1)).getReferenceById(projectId);

        verify(mockProject.getAmount(), times(1)).setBasic_design(amountDto.getBasic_design());
        verify(mockProject.getAmount(), times(1)).setCoding(amountDto.getCoding());
        verify(mockProject.getAmount(), times(1)).setDetail_design(amountDto.getDetail_design());
        verify(mockProject.getAmount(), times(1)).setUnit_testing(amountDto.getUnit_testing());
        verify(mockProject.getAmount(), times(1)).setIntegrated_testing(amountDto.getIntegrated_testing());

        verify(projectRepository, times(1)).save(mockProject);
        verify(projectService, times(1)).getProjectById(projectId);
    }

    @Test
    void testGetAllArchitecture() {

        List<ArchitectureDto> mockArchitectureList = Arrays.asList(new ArchitectureDto(), new ArchitectureDto());

        when(architectureService.getAllArchitecture()).thenReturn(mockArchitectureList);

        ResponseEntity<List<ArchitectureDto>> responseEntity = projectController.getAllArchitecture();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockArchitectureList, responseEntity.getBody());
        verify(architectureService, times(1)).getAllArchitecture();
    }

    @Test
    void testGetProjectByName() {
        // Arrange
        String projectName = "Project1";
        ProjectDto mockProject = new ProjectDto();
        when(projectService.getProjectByName(projectName)).thenReturn(mockProject);

        // Act
        ResponseEntity<ProjectDto> result = projectController.getProjectByName(projectName);

        // Assert
        if (mockProject != null) {
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(mockProject, result.getBody());
        } else {
            assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        }

        verify(projectService, times(1)).getProjectByName(projectName);
    }

    @Test
    void testGetDeliverableType() {
        // Arrange
        Set<DeliverableTypeDto> mockDeliverableTypes = new HashSet<>();
        mockDeliverableTypes.add(new DeliverableTypeDto());
        mockDeliverableTypes.add(new DeliverableTypeDto());

        when(deliverableTypeService.getAll()).thenReturn(mockDeliverableTypes);

        // Act
        ResponseEntity<Set<DeliverableTypeDto>> responseEntity = projectController.getDeliverableType();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockDeliverableTypes, responseEntity.getBody());

        // Verify that the service method was called
        verify(deliverableTypeService, times(1)).getAll();
    }
    @Test
    void testGetProjectByNameNotFound() {
        // Arrange
        String projectName = "NonExistentProject";

        // Mocking the service method to return null for a non-existent project
        when(projectService.getProjectByName(projectName)).thenReturn(null);

        // Act
        ResponseEntity<ProjectDto> result = projectController.getProjectByName(projectName);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(null, result.getBody());

        verify(projectService, times(1)).getProjectByName(projectName);
    }

    @Test
    void testUpdateProject() throws Exception {
        // Arrange
        ProjectDto mockProject = new ProjectDto(); // Replace with your mock data
        when(projectService.updateProject(mockProject)).thenReturn(mockProject);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(mockProject);

        // Act
        ResponseEntity<ProjectDto> result = projectController.updatePrject(mockProject);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockProject, result.getBody());

        verify(projectService, times(1)).updateProject(mockProject);
    }



    @Test
    void testGetAllProjectsByDepartmentName() {
        // Arrange
        String departmentName = "Department1";
        List<ProjectDto> mockProjects = Arrays.asList(
                new ProjectDto(),
                new ProjectDto()
        );

        // Case where departmentName is found
        when(projectService.getAllProjectsByDepartmentName(departmentName)).thenReturn(mockProjects);

        // Act
        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getAllProjectsByDepartmentName(departmentName);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockProjects, responseEntity.getBody());

        verify(projectService, times(1)).getAllProjectsByDepartmentName(departmentName);

        reset(projectService);

        when(projectService.getAllProjectsByDepartmentName(departmentName)).thenReturn(Collections.emptyList());

        responseEntity = projectController.getAllProjectsByDepartmentName(departmentName);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());

        // Verify that the service method was called once with the given department name
        verify(projectService, times(1)).getAllProjectsByDepartmentName(departmentName);
    }

    @Test
    void testSortProjectByDepId() {
        // Arrange
        List<ProjectDto> mockProjects = Arrays.asList(
                new ProjectDto(1L, new DepartmentDto(1L), "Project1"),
                new ProjectDto(2L, new DepartmentDto(2L), "Project2"),
                new ProjectDto(3L, new DepartmentDto(1L), "Project3")
        );

        when(projectService.getAllProjects()).thenReturn(mockProjects);
        ResponseEntity<Map<Long, List<Long>>> responseEntity = projectController.sortProjectByDepId();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<Long, List<Long>> expectedMap = new HashMap<>();
        expectedMap.put(1L, Arrays.asList(1L, 3L));
        expectedMap.put(2L, Collections.singletonList(2L));

        assertEquals(expectedMap, responseEntity.getBody());

        verify(projectService, times(1)).getAllProjects();
    }


    @Test
    public void testGetProjectByIdAndStatus() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setStatus("IN_PROGRESS");

        ClientDto clientDto = new ClientDto();

        List<UserDto> userDtos = new ArrayList<>();

        projectDto.setClientDto(clientDto);
        projectDto.setMembersUserDto(userDtos);

        List<ProjectDto> projectList = new ArrayList<>();
        projectList.add(projectDto);

        when(projectService.findAllByUserId(anyLong())).thenReturn(projectList);

        ResponseEntity<Map<String, Object>> responseEntity = projectController.getProjectByIdAndStatus(1L, "IN_PROGRESS");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<String, Object> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);

        assertEquals(1L, responseBody.get("projectId"));
        assertEquals(clientDto, responseBody.get("client"));
        assertEquals(userDtos, responseBody.get("userList"));

        ResponseEntity<Map<String, Object>> invalidStatusResponse = projectController.getProjectByIdAndStatus(1L, "invalidStatus");

        assertEquals(HttpStatus.OK, invalidStatusResponse.getStatusCode());
        Map<String, Object> invalidStatusResponseBody = invalidStatusResponse.getBody();
        assertNotNull(invalidStatusResponseBody);
        assertEquals(null, invalidStatusResponseBody.get("projectId"));
    }

    @Test
    public void testGetAllProjectsByRole_ProjectManager() {
        // Mock data
        List<ProjectDto> projects = new ArrayList<>();

        when(projectService.getAllProjectsByProjectManagerId(anyLong())).thenReturn(projects);

        String role = "PROJECT_MANAGER";
        Long id = 123L;
        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getAllProjectsByRole(role, id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(projects, responseEntity.getBody());
    }

    @Test
    public void testGetAllProjectsByRole_DepartmentHead() {

        List<ProjectDto> projects = new ArrayList<>();

        when(projectService.getAllProjectsByDepartmentId(anyLong())).thenReturn(projects);

        String role = "DEPARTMENT_HEAD";
        Long id = 4L;

        UserDto userDto = new UserDto();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(7L);
        userDto.setDepartmentdto(departmentDto);
        when(userService.getUserById(anyLong())).thenReturn(userDto);

        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getAllProjectsByRole(role, id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(projects, responseEntity.getBody());
    }

    @Test
    void testGetAllProjectsByRoleForMember() {
        List<ProjectDto> projects = new ArrayList<>();

        when(projectService.findAllByUserId(anyLong())).thenReturn(projects);

        String role = "MEMBER";
        Long id = 4L;

        UserDto userDto = new UserDto();
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(7L);
        userDto.setDepartmentdto(departmentDto);
        when(userService.getUserById(anyLong())).thenReturn(userDto);

        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getAllProjectsByRole(role, id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Use isAssignableFrom to specify the expected type of the returned projects
        assertTrue(responseEntity.getBody() instanceof List<?>);
        assertEquals(projects, responseEntity.getBody());
    }


    @Test
    void testGetAllProjectsByRoleForDefault() {

        when(projectService.getAllProjects())
                .thenReturn(Arrays.asList(new ProjectDto(), new ProjectDto()));

        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getAllProjectsByRole("UNKNOWN_ROLE", 1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof List<?>);

    }


    @Test
    public void testGetProjectWithDpId() {
        // Mock data
        Department department1 = new Department();
        department1.setId(1L);

        Department department2 = new Department();
        department2.setId(2L);

        Project project1 = new Project();
        project1.setId(1L);
        project1.setDepartment(department1);

        Project project2 = new Project();
        project2.setId(2L);
        project2.setDepartment(department1);

        Project project3 = new Project();
        project3.setId(3L);
        project3.setDepartment(department2);

        List<Project> projectList = new ArrayList<>();
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);

        // Mocking projectRepository
        when(projectRepository.findAll()).thenReturn(projectList);

        // Test when projects are available
        ResponseEntity<Map<Long, List<Long>>> responseEntity = projectController.getProjectWithDpId();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<Long, List<Long>> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);

        // Validate the content of the response body
        assertEquals(2, responseBody.size());
        assertTrue(responseBody.containsKey(department1.getId()));
        assertTrue(responseBody.containsKey(department2.getId()));

        // Validate project ids for department1
        assertEquals(2, responseBody.get(department1.getId()).size());
        assertTrue(responseBody.get(department1.getId()).contains(project1.getId()));
        assertTrue(responseBody.get(department1.getId()).contains(project2.getId()));

        // Validate project ids for department2
        assertEquals(1, responseBody.get(department2.getId()).size());
        assertTrue(responseBody.get(department2.getId()).contains(project3.getId()));

        // Test when no projects are available
        when(projectRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Map<Long, List<Long>>> emptyResponseEntity = projectController.getProjectWithDpId();

        assertEquals(HttpStatus.OK, emptyResponseEntity.getStatusCode());

        Map<Long, List<Long>> emptyResponseBody = emptyResponseEntity.getBody();
        assertNotNull(emptyResponseBody);

        // Validate the content of the response body for an empty project list
        assertEquals(0, emptyResponseBody.size());
    }





    @Test
    void testCountAllProjects() {
        // Arrange
        Long mockCount = 10L;
        when(projectService.countAllProjects()).thenReturn(mockCount);

        // Act
        ResponseEntity<Long> responseEntity = projectController.countAllProjects();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockCount, responseEntity.getBody());
    }
    @Test
    void countByUserId() {

            long userId = 123L;
            Long mockCount = 5L;
            when(projectService.countAllProjectsByUsersId(userId)).thenReturn(mockCount);

            // Act
            ResponseEntity<Long> responseEntity = projectController.countByUserId(userId);

            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(mockCount, responseEntity.getBody());
        }



    @Test
    void countAllByDepartmentId() {

            long departmentId = 123L;
            Long mockCount = 5L;
            when(projectService.countAllProjectsByDepartmentId(departmentId)).thenReturn(mockCount);

            // Act
            ResponseEntity<Long> responseEntity = projectController.countAllByDepartmentId(departmentId);

            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(mockCount, responseEntity.getBody());
        }

    @Test
    public void testGetListResponseEntity() {
        // Mock data
        List<ProjectDto> projects = new ArrayList<>();

        // Mocking the service methods
        when(taskService.countByProjectId(anyLong())).thenReturn(5L);
        when(taskService.countTaskByProjectIdAndStatus(anyLong(), any())).thenReturn(2L);

        // Test the method
        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getListResponseEntity(projects);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(projects, responseEntity.getBody());

         for (ProjectDto projectDto : projects) {
            verify(taskService, times(1)).countByProjectId(projectDto.getId());
            verify(taskService, times(1)).countTaskByProjectIdAndStatus(projectDto.getId(), TaskStatus.FINISHED);


            if (projectDto.getTasksDto() != null) {
                verify(projectDto.getTasksDto(), times(1)).clear();
            }
            if (projectDto.getIssueDto() != null) {
                verify(projectDto.getIssueDto(), times(1)).clear();
            }

        }
    }




}