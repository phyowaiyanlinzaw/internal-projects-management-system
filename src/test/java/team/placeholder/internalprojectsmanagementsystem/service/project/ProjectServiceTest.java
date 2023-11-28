package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.project.TaskRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectService projectService;

    @Mock
    private TaskRepository taskRepository; // Assuming TaskRepository is your repository for Task entities

    @InjectMocks
    private TasksService taskService;

    @Mock
    ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProject() {
        ProjectDto projectDto = new ProjectDto();
        when(projectService.save(projectDto)).thenReturn(projectDto);
        ProjectDto savedProject = projectService.save(projectDto);
        assertEquals(projectDto, savedProject);
    }

    @Test
    public void testGetAllProjects() {
        List<ProjectDto> projectDto = new ArrayList<>();
        when(projectService.getAllProjects()).thenReturn(projectDto);
        List<ProjectDto> allProjects = projectService.getAllProjects();
        assertEquals(projectDto, allProjects);
    }

    @Test
    public void testGetProjectById() {
        int id = 1;
        ProjectDto projectDto = new ProjectDto();
        when(projectService.getProjectById(id)).thenReturn(projectDto);
        ProjectDto projectById = projectService.getProjectById(1);
        assertEquals(projectDto, projectById);
    }

    @Test
    public void testGetProjectByName() {
        String name = "test";
        ProjectDto projectDto = new ProjectDto();
        when(projectService.getProjectByName(name)).thenReturn(projectDto);
        ProjectDto projectByName = projectService.getProjectByName(name);
        assertEquals(projectDto, projectByName);
    }

    @Test
    public void testUpdateProject() {
        ProjectDto projectDto = new ProjectDto();
        when(projectService.updateProject(projectDto)).thenReturn(projectDto);
        ProjectDto updatedProject = projectService.updateProject(projectDto);
        assertEquals(projectDto, updatedProject);
    }

    @Test
    public void testCountAllProjects(){
        long expectedProjectCount = 10;

        when(projectService.countAllProjects()).thenReturn(expectedProjectCount);

        // When
        long result = projectService.countAllProjects();

        // Then
        assertEquals(expectedProjectCount, result);

    }

    @Test
    public void testGetAllProjectsByProjectManagerId(){
        long projectManagerId = 1L;
        List<ProjectDto> expectedProjectList = Arrays.asList(new ProjectDto(), new ProjectDto());

        when(projectService.getAllProjectsByProjectManagerId(projectManagerId)).thenReturn(expectedProjectList);

        // When
        List<ProjectDto> result = projectService.getAllProjectsByProjectManagerId(projectManagerId);

        // Then
        assertEquals(expectedProjectList, result);
    }

    @Test
    public void testCountAllProjectsByUsersId(){
        long userId = 1L;
        long expectedProjectCount = 5;

        when(projectService.countAllProjectsByUsersId(userId)).thenReturn(expectedProjectCount);

        // When
        long result = projectService.countAllProjectsByUsersId(userId);

        // Then
        assertEquals(expectedProjectCount, result);
    }

    @Test
    public void testCountAllProjectsByProjectManagerId() {
        // Given
        long projectManagerId = 1L;
        long expectedProjectCount = 7;

        when(projectService.countAllProjectsByProjectManagerId(projectManagerId)).thenReturn(expectedProjectCount);

        // When
        long result = projectService.countAllProjectsByProjectManagerId(projectManagerId);

        // Then
        assertEquals(expectedProjectCount, result);
    }

    @Test
    public void testCountAllProjectsByProjectManagerIdAndClosed() {
        // Given
        long projectManagerId = 1L;
        boolean closedStatus = true;
        long expectedProjectCount = 3;

        when(projectService.countAllProjectsByProjectManagerIdAndClosed(projectManagerId, closedStatus)).thenReturn(expectedProjectCount);

        // When
        long result = projectService.countAllProjectsByProjectManagerIdAndClosed(projectManagerId, closedStatus);

        // Then
        assertEquals(expectedProjectCount, result);
    }


    @Test
    public void testGetAllProjectsByDepartmentId() {
        // Given
        long departmentId = 1L;
        List<ProjectDto> expectedProjectList = Arrays.asList(new ProjectDto(), new ProjectDto());

        when(projectService.getAllProjectsByDepartmentId(departmentId)).thenReturn(expectedProjectList);

        // When
        List<ProjectDto> result = projectService.getAllProjectsByDepartmentId(departmentId);

        // Then
        assertEquals(expectedProjectList, result);
    }


    @Test
    public void testCountAllProjectsByDepartmentId() {
        // Given
        long departmentId = 1L;
        long expectedProjectCount = 5;

        when(projectService.countAllProjectsByDepartmentId(departmentId)).thenReturn(expectedProjectCount);

        // When
        long result = projectService.countAllProjectsByDepartmentId(departmentId);

        // Then
        assertEquals(expectedProjectCount, result);
    }

    @Test
    public void testFindAllByUserId() {
        // Given
        long userId = 1L;
        List<ProjectDto> expectedProjectList = Arrays.asList(new ProjectDto(), new ProjectDto());

        when(projectService.findAllByUserId(userId)).thenReturn(expectedProjectList);

        // When
        List<ProjectDto> result = projectService.findAllByUserId(userId);

        // Then
        assertEquals(expectedProjectList, result);
    }

    @Test
    public void testGetAllProjectsByDepartmentName() {
        // Given
        String departmentName = "Engineering";
        List<ProjectDto> expectedProjectList = Arrays.asList(new ProjectDto(), new ProjectDto());

        when(projectService.getAllProjectsByDepartmentName(departmentName)).thenReturn(expectedProjectList);

        // When
        List<ProjectDto> result = projectService.getAllProjectsByDepartmentName(departmentName);

        // Then
        assertEquals(expectedProjectList, result);
    }



    @Test
    public void testUpdateProjectClosed() {

    }

    @Test
    public void testUpdateUserListInProject(){

    }

}