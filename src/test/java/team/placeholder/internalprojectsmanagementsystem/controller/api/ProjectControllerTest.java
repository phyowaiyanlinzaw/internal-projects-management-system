package team.placeholder.internalprojectsmanagementsystem.controller.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;

import team.placeholder.internalprojectsmanagementsystem.dto.model.project.*;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.ProListDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.Amount;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.TaskServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import team.placeholder.internalprojectsmanagementsystem.service.project.ProjectService;
import team.placeholder.internalprojectsmanagementsystem.service.project.TasksService;

import java.time.LocalDate;
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
    public void testSaveWithNonNullSavedProject() {

        ProjectDto projectDto = new ProjectDto();
        when(projectService.save(any(ProjectDto.class))).thenReturn(new ProjectDto(/* provide necessary data */));

        ResponseEntity<ProListDto> responseEntity = projectController.save(projectDto);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ProListDto proListDto = responseEntity.getBody();
        assertNotNull(proListDto);

    }

    @Test
    public void testSaveWithNullSavedProject() {
        ProjectDto projectDto = new ProjectDto();
        when(projectService.save(any(ProjectDto.class))).thenReturn(null);


        ResponseEntity<ProListDto> responseEntity = projectController.save(projectDto);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ProListDto proListDto = responseEntity.getBody();
        assertNotNull(proListDto);

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
    public void testGetProjectByIdWithExistingProject() {
        long projectId = 1L;
        ProjectDto expectedProject = new ProjectDto();
        when(projectService.getProjectById(eq(projectId))).thenReturn(expectedProject);


        ResponseEntity<ProjectDto> responseEntity = projectController.getProjectById(projectId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ProjectDto actualProject = responseEntity.getBody();
        assertNotNull(actualProject);

    }

    @Test
    public void testGetProjectByIdWithNonExistingProject() {

        long projectId = 2L;
        when(projectService.getProjectById(eq(projectId))).thenReturn(null);

        ResponseEntity<ProjectDto> responseEntity = projectController.getProjectById(projectId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
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
    public void testGetProjectByIdAndStatusWithMatchingProject() {
        long userId = 1L;
        String status = "false";
        List<ProjectDto> projects = Arrays.asList(
                new ProjectDto(),
                new ProjectDto()
        );
        when(projectService.findAllByUserId(eq(userId))).thenReturn(projects);

        ResponseEntity<Map<String, Object>> responseEntity = projectController.getProjectByIdAndStatus(userId, status);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, Object> projectMap = responseEntity.getBody();
        assertNotNull(projectMap);

    }

    @Test
    public void testGetProjectByIdAndStatusWithNoMatchingProject() {
         long userId = 2L;
        String status = "true";
        List<ProjectDto> projects = Arrays.asList(
                new ProjectDto(),
                new ProjectDto()
        );
        when(projectService.findAllByUserId(eq(userId))).thenReturn(projects);

        ResponseEntity<Map<String, Object>> responseEntity = projectController.getProjectByIdAndStatus(userId, status);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
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
    void testGetAllProjectsByRoleForProjectManager() {
        // Setup mocks and test for PROJECT_MANAGER role
        Long projectManagerId = 1L;
        List<ProjectDto> projects = Arrays.asList(new ProjectDto(), new ProjectDto());
        when(projectService.getAllProjectsByProjectManagerId(projectManagerId)).thenReturn(projects);

        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getAllProjectsByRole("PROJECT_MANAGER", projectManagerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof List<?>);
        assertEquals(projects, responseEntity.getBody());
        // Add more assertions specific to the PROJECT_MANAGER role
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
    public void testGetListResponseEntity() {
        List<ProjectDto> projects = new ArrayList<>();
        ProjectDto project1 = new ProjectDto();
        ProjectDto project2 = new ProjectDto();
        projects.add(project1);
        projects.add(project2);

        // Mocking taskService.countByProjectId to return a specific count
        when(taskService.countByProjectId(anyLong())).thenReturn(5L);

        // Mocking taskService.countTaskByProjectIdAndStatus to return a specific count
        when(taskService.countTaskByProjectIdAndStatus(anyLong(), eq(TaskStatus.FINISHED))).thenReturn(3L);

        // Call the getListResponseEntity method
        ResponseEntity<List<ProjectDto>> responseEntity = projectController.getListResponseEntity(projects);

        // Check if the method returned OK status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Check modifications in each ProjectDto in the list
        for (ProjectDto projectDto : responseEntity.getBody()) {
            assertEquals(5, projectDto.getTotalTaskCount());
            assertEquals(3, projectDto.getCompleteTaskCount());

            // Check if tasksDto and issueDto are cleared
            assertNull(projectDto.getTasksDto());
            assertNull(projectDto.getIssueDto());


        }
    }




    @Test
    public void testNewListView() {
        // Sample role and ID for testing
        String role = "PROJECT_MANAGER";
        Long id = 1L;

        List<ProListDto> proListDtoList = new ArrayList<>();

        when(projectService.newProjectLook(any(Role.class), anyLong())).thenReturn(proListDtoList);

        // Call the newListView method
        ResponseEntity<List<ProListDto>> responseEntity = projectController.newListView(role, id);

        // Verify that the method returned OK status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the projectService.newProjectLook method was called with the expected parameters
        verify(projectService).newProjectLook(Role.valueOf(role), id);

        // You can further validate the contents of the returned list if needed
        assertEquals(proListDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateProjectStatus() {
        // Sample project ID and condition for testing
        long projectId = 1L;
        boolean condition = true;

        // Call the updateProjectStatus method
        ResponseEntity<String> responseEntity = projectController.updateProjectStatus(projectId, condition);

        // Verify that the method returned OK status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the projectService.updateProjectClosed method was called with the expected parameters
        verify(projectService).updateProjectClosed(projectId, condition);

        // You can further validate the content of the response if needed
        assertEquals(" i don't know", responseEntity.getBody());
    }

    @Test
    public void testUpdateUserListInProejct() {
        long projectId = 1L;
        List<UserDto> userDtos = Arrays.asList(
                new UserDto(),
                new UserDto()
        );

        // Call the updateUserListInProejct method
        ResponseEntity<String> responseEntity = projectController.updateUserListInProejct(projectId, userDtos);

        // Verify that the method returned OK status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the projectService.updateUserListInProject method was called with the expected parameters
        verify(projectService).updateUserListInProject(eq(projectId), any(List.class));

        // You can further validate the content of the response if needed
        assertEquals("User list updated successfully", responseEntity.getBody());
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
    public void testCountByProjectManagerIdAndStatus() {
        long managerId = 1L;
        String status = "true"; // or "false" depending on your implementation
        boolean parsedStatus = Boolean.parseBoolean(status);
        long expectedCount = 10L;


        when(projectService.countAllProjectsByProjectManagerIdAndClosed(managerId, parsedStatus)).thenReturn(expectedCount);


        ResponseEntity<Long> responseEntity = projectController.countByProjectManagerIdAndStatus(managerId, status);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


        verify(projectService).countAllProjectsByProjectManagerIdAndClosed(managerId, parsedStatus);

        assertEquals(expectedCount, responseEntity.getBody().longValue());
    }

    @Test
    public void testCountByProjectManagerId() {
        long managerId = 1L;
        long expectedCount = 5L;

        when(projectService.countAllProjectsByProjectManagerId(managerId)).thenReturn(expectedCount);

        ResponseEntity<Long> responseEntity = projectController.countByProjectManagerId(managerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(projectService).countAllProjectsByProjectManagerId(managerId);


        assertEquals(expectedCount, responseEntity.getBody().longValue());
    }


    @Test
    public void testGetAllProjectsByRole_ProjectManager() {
        // Mocking necessary data
        String role = "PROJECT_MANAGER";
        Long id = 1L;
        List<ProjectDto> projects = Collections.singletonList(new ProjectDto(/* provide necessary data */));

        // Mocking behavior for projectService
        when(projectService.getAllProjectsByProjectManagerId(id)).thenReturn(projects);

        // Calling the method under test
        ResponseEntity<List<ProjectDto>> response = projectController.getAllProjectsByRole(role, id);

        // Verifying interactions and assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectService, times(1)).getAllProjectsByProjectManagerId(id);
        assertEquals(projects, response.getBody());
    }

    @Test
    public void testGetAllProjectsByRole_DepartmentHead() {
        // Mocking necessary data
        String role = "DEPARTMENT_HEAD";
        Long id = 1L;
        Long departmentId = 2L;
        List<ProjectDto> projects = Collections.singletonList(new ProjectDto(/* provide necessary data */));

        // Mocking behavior for userService and projectService
        when(userService.getUserById(id)).thenReturn(new UserDto(/* provide necessary data */));
        when(userService.getUserById(id).getDepartmentdto().getId()).thenReturn(departmentId);
        when(projectService.getAllProjectsByDepartmentId(departmentId)).thenReturn(projects);

        // Calling the method under test
        ResponseEntity<List<ProjectDto>> response = projectController.getAllProjectsByRole(role, id);

        // Verifying interactions and assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).getUserById(id);
        verify(userService.getUserById(id), times(1)).getDepartmentdto();
        verify(projectService, times(1)).getAllProjectsByDepartmentId(departmentId);
        assertEquals(projects, response.getBody());
    }

    @Test
    public void testGetAllProjectsByRole_Member() {
        // Mocking necessary data
        String role = "MEMBER";
        Long id = 1L;
        List<ProjectDto> projects = Collections.singletonList(new ProjectDto());

        // Mocking behavior for projectService
        when(projectService.findAllByUserId(id)).thenReturn(projects);

        // Calling the method under test
        ResponseEntity<List<ProjectDto>> response = projectController.getAllProjectsByRole(role, id);

        // Verifying interactions and assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectService, times(1)).findAllByUserId(id);
        assertEquals(projects, response.getBody());
    }

    @Test
    public void testGetAllProjectsByRole_Default() {
        // Mocking necessary data
        String role = "SOME_OTHER_ROLE";
        List<ProjectDto> projects = Collections.singletonList(new ProjectDto());

        // Mocking behavior for projectService
        when(projectService.getAllProjects()).thenReturn(projects);

        // Calling the method under test
        ResponseEntity<List<ProjectDto>> response = projectController.getAllProjectsByRole(role, null);

        // Verifying interactions and assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectService, times(1)).getAllProjects();
        assertEquals(projects, response.getBody());
    }

    @Test
    void testUpdateDeliverableList() {

    }


    @Test
    void testUpdateArchitectureList() {
        // Create test data
        long projectId = 1L;
        long architectureId = 2L;

        Map<String, Long> requestBody = new HashMap<>();
        requestBody.put("arcid", architectureId);

        Architecture architecture = new Architecture();
        architecture.setId(architectureId);

        Project project = new Project();
        project.setId(projectId);
        project.setArchitectures(new HashSet<>(Collections.singletonList(architecture)));


        // Mock behavior of your service/repository methods
        when(projectRepository.getReferenceById(projectId)).thenReturn(project);
        when(architectureService.findById(architectureId)).thenReturn(architecture);

        // Execute the method
        ResponseEntity<List<ArchitectureDto>> response = projectController.updateArchitectureList(projectId, requestBody);

        // Assert the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size()); // Assuming the architecture is removed

        // Verify that the methods were called with the expected arguments
        verify(projectRepository).getReferenceById(projectId);
        verify(architectureService).findById(architectureId);
        verify(projectRepository).save(project);
    }

    @Test
    void testGetArchitectureList() {
        // Create test data
        long projectId = 1L;

        Project project = new Project();
        project.setId(projectId);

        ArchitectureDto architectureDto1 = new ArchitectureDto();
        architectureDto1.setId(2L);
        ArchitectureDto architectureDto2 = new ArchitectureDto();
        architectureDto2.setId(3L);

        List<ArchitectureDto> architectureDtos = new ArrayList<>(List.of(architectureDto1, architectureDto2));

        // Mock behavior of your service/repository methods
        when(projectRepository.getReferenceById(projectId)).thenReturn(project);
        when(architectureService.getAllArchitecture()).thenReturn(architectureDtos);
        when(architectureService.findById(architectureDto1.getId())).thenReturn(new Architecture());
        when(architectureService.findById(architectureDto2.getId())).thenReturn(new Architecture());

        // Execute the method
        ResponseEntity<List<ArchitectureDto>> response = projectController.getArchitectureList(projectId);

        // Assert the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size()); // Assuming both architectureDto1 and architectureDto2 are not in project

        // Verify that the methods were called with the expected arguments
        verify(projectRepository).getReferenceById(projectId);
        verify(architectureService).getAllArchitecture();
        verify(architectureService, times(2)).findById(anyLong()); // It's called for each ArchitectureDto
    }






}