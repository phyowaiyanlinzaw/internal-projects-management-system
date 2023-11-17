package team.placeholder.internalprojectsmanagementsystem.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.AmountDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Architecture;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ArchitectureServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ArchitectureServiceImpl architectureService;


    @Mock
    private FakerService fakerService;

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
    void testUpdateAmountById() throws Exception {
        // Arrange
        long projectId = 1L;
        ProjectDto mockProjectDto = new ProjectDto(); // Replace with your mock data
        AmountDto mockAmountDto = new AmountDto(); // Replace with your mock data

        when(projectRepository.getReferenceById(projectId)).thenReturn(new Project());
        when(projectService.getProjectById(projectId)).thenReturn(mockProjectDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(mockAmountDto);

        // Act
        ResponseEntity<ProjectDto> result = projectController.updateAmountById(projectId, mockAmountDto);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockProjectDto, result.getBody());

        // Verify that the repository save and service method were called
        verify(projectRepository, times(1)).getReferenceById(projectId);
        verify(projectRepository, times(1)).save(any(Project.class));
        verify(projectService, times(1)).getProjectById(projectId);
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

            long userId = 123L; // replace with your user ID
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

            long departmentId = 123L; // replace with your department ID
            Long mockCount = 5L;
            when(projectService.countAllProjectsByDepartmentId(departmentId)).thenReturn(mockCount);

            // Act
            ResponseEntity<Long> responseEntity = projectController.countAllByDepartmentId(departmentId);

            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(mockCount, responseEntity.getBody());
        }






}