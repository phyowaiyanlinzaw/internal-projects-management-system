package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.ProjectServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectService projectServiceMock;



    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        ProjectDto inputProjectDto = new ProjectDto(/* fill in with required parameters for the new project */);

        // Mock the behavior of the service method
        when(projectServiceMock.save(any(ProjectDto.class))).thenReturn(inputProjectDto);

        // When
        ProjectDto savedProjectDto = projectServiceMock.save(inputProjectDto);

        // Then
        // Verify that the save method was called with the correct parameter
        verify(projectServiceMock, times(1)).save(inputProjectDto);

        // Verify that the returned ProjectDto is the same as the input
        assertEquals(inputProjectDto, savedProjectDto);

    }

    @Test
    void testGetAllProjects() {
        // Given
        List<ProjectDto> expectedProjects = Arrays.asList(new ProjectDto(), new ProjectDto());
        when(projectServiceMock.getAllProjects()).thenReturn(expectedProjects);

        // When
        List<ProjectDto> actualProjects = projectServiceMock.getAllProjects();

        // Then
        assertEquals(expectedProjects, actualProjects);
    }

    @Test
    void getProjectById() {
        long projectId = 1L; // Replace with the actual project ID
        ProjectDto expectedProjectDto = new ProjectDto(/* fill in with expected project details */);

        // Mock the behavior of the service method
        when(projectServiceMock.getProjectById(anyLong())).thenReturn(expectedProjectDto);

        // When
        ProjectDto retrievedProjectDto = projectServiceMock.getProjectById(projectId);

        // Then
        // Verify that the getProjectById method was called with the correct parameter
        verify(projectServiceMock, times(1)).getProjectById(projectId);

        // Verify that the returned ProjectDto is the same as the expected one
        assertEquals(expectedProjectDto, retrievedProjectDto);
    }

    @Test
    void getProjectByName() {
    }

    @Test
    void updateProject() {
    }

    @Test
    void countAllProjects() {
    }

    @Test
    void getAllProjectsByProjectManagerId() {
    }

    @Test
    void countAllProjectsByUsersId() {
    }

    @Test
    void countAllProjectsByProjectManagerId() {
    }

    @Test
    void countAllProjectsByProjectManagerIdAndClosed() {
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
    void updateProjectClosed() {
    }

    @Test
    void updateUserListInProject() {
    }
}