package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
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
        // Given
        String projectName = "ExampleProject"; // Replace with the actual project name
        ProjectDto expectedProjectDto = new ProjectDto(/* fill in with expected project details */);

        // Mock the behavior of the service method
        when(projectServiceMock.getProjectByName(anyString())).thenReturn(expectedProjectDto);

        // When
        ProjectDto retrievedProjectDto = projectServiceMock.getProjectByName(projectName);

        // Then
        // Verify that the getProjectByName method was called with the correct parameter
        verify(projectServiceMock, times(1)).getProjectByName(projectName);

        // Verify that the returned ProjectDto is the same as the expected one
        assertEquals(expectedProjectDto, retrievedProjectDto);
    }


    @Test
    void updateProject() {
        // Given
        ProjectDto projectToUpdate = new ProjectDto(/* fill in with the details of the project to update */);
        ProjectDto updatedProjectDto = new ProjectDto(/* fill in with the updated project details */);

        // Mock the behavior of the service method
        when(projectServiceMock.updateProject(any(ProjectDto.class))).thenReturn(updatedProjectDto);

        // When
        ProjectDto result = projectServiceMock.updateProject(projectToUpdate);

        // Then
        // Verify that the updateProject method was called with the correct parameter
        verify(projectServiceMock, times(1)).updateProject(projectToUpdate);

        // Verify that the returned ProjectDto is the same as the expected updated one
        assertEquals(updatedProjectDto, result);
    }


    @Test
    void countAllProjects() {
        // Given
        long expectedCount = 5L; // Replace with the expected count

        // Mock the behavior of the service method
        when(projectServiceMock.countAllProjects()).thenReturn(expectedCount);

        // When
        long result = projectServiceMock.countAllProjects();

        // Then
        // Verify that the countAllProjects method was called
        verify(projectServiceMock, times(1)).countAllProjects();

        // Verify that the returned count is as expected
        assertEquals(expectedCount, result);
    }


    @Test
    void getAllProjectsByProjectManagerId() {
        // Given
        long projectManagerId = 1L; // Replace with the actual project manager ID
        List<ProjectDto> expectedProjects = Arrays.asList(new ProjectDto(/* fill in with project details */));

        // Mock the behavior of the service method
        when(projectServiceMock.getAllProjectsByProjectManagerId(anyLong())).thenReturn(expectedProjects);

        // When
        List<ProjectDto> result = projectServiceMock.getAllProjectsByProjectManagerId(projectManagerId);

        // Then
        // Verify that the getAllProjectsByProjectManagerId method was called with the correct parameter
        verify(projectServiceMock, times(1)).getAllProjectsByProjectManagerId(projectManagerId);

        // Verify that the returned list of projects is the same as the expected list
        assertEquals(expectedProjects, result);
    }

    @Test
    void countAllProjectsByUsersId() {
        // Given
        long userId = 1L; // Replace with the actual user ID
        long expectedCount = 3L; // Replace with the expected count

        // Mock the behavior of the service method
        when(projectServiceMock.countAllProjectsByUsersId(anyLong())).thenReturn(expectedCount);

        // When
        long result = projectServiceMock.countAllProjectsByUsersId(userId);

        // Then
        // Verify that the countAllProjectsByUsersId method was called with the correct parameter
        verify(projectServiceMock, times(1)).countAllProjectsByUsersId(userId);

        // Verify that the returned count is as expected
        assertEquals(expectedCount, result);
    }


    @Test
    void countAllProjectsByProjectManagerId() {
        // Given
        long projectManagerId = 1L; // Replace with the actual project manager ID
        long expectedCount = 2L; // Replace with the expected count

        // Mock the behavior of the service method
        when(projectServiceMock.countAllProjectsByProjectManagerId(anyLong())).thenReturn(expectedCount);

        // When
        long result = projectServiceMock.countAllProjectsByProjectManagerId(projectManagerId);

        // Then
        // Verify that the countAllProjectsByProjectManagerId method was called with the correct parameter
        verify(projectServiceMock, times(1)).countAllProjectsByProjectManagerId(projectManagerId);

        // Verify that the returned count is as expected
        assertEquals(expectedCount, result);
    }


    @Test
    void countAllProjectsByProjectManagerIdAndClosed() {
        // Given
        long projectManagerId = 1L; // Replace with the actual project manager ID
        boolean closed = false; // Replace with the expected closed status
        long expectedCount = 2L; // Replace with the expected count

        // Mock the behavior of the service method
        when(projectServiceMock.countAllProjectsByProjectManagerIdAndClosed(anyLong(), anyBoolean())).thenReturn(expectedCount);

        // When
        long result = projectServiceMock.countAllProjectsByProjectManagerIdAndClosed(projectManagerId, closed);

        // Then
        // Verify that the countAllProjectsByProjectManagerIdAndClosed method was called with the correct parameters
        verify(projectServiceMock, times(1)).countAllProjectsByProjectManagerIdAndClosed(projectManagerId, closed);

        // Verify that the returned count is as expected
        assertEquals(expectedCount, result);
    }

    @Test
    void getAllProjectsByDepartmentId() {
        // Given
        long departmentId = 1L; // Replace with the actual department ID
        List<ProjectDto> expectedProjects = Arrays.asList(new ProjectDto(/* fill in with project details */));

        // Mock the behavior of the service method
        when(projectServiceMock.getAllProjectsByDepartmentId(anyLong())).thenReturn(expectedProjects);

        // When
        List<ProjectDto> result = projectServiceMock.getAllProjectsByDepartmentId(departmentId);

        // Then
        // Verify that the getAllProjectsByDepartmentId method was called with the correct parameter
        verify(projectServiceMock, times(1)).getAllProjectsByDepartmentId(departmentId);

        // Verify that the returned list of projects is the same as the expected list
        assertEquals(expectedProjects, result);
    }


    @Test
    void countAllProjectsByDepartmentId() {
        // Given
        long departmentId = 1L; // Replace with the actual department ID
        long expectedCount = 3L; // Replace with the expected count

        // Mock the behavior of the service method
        when(projectServiceMock.countAllProjectsByDepartmentId(anyLong())).thenReturn(expectedCount);

        // When
        long result = projectServiceMock.countAllProjectsByDepartmentId(departmentId);

        // Then
        // Verify that the countAllProjectsByDepartmentId method was called with the correct parameter
        verify(projectServiceMock, times(1)).countAllProjectsByDepartmentId(departmentId);

        // Verify that the returned count is as expected
        assertEquals(expectedCount, result);
    }


    @Test
    void findAllByUserId() {
        // Given
        long userId = 1L; // Replace with the actual user ID
        List<ProjectDto> expectedProjects = Arrays.asList(new ProjectDto(/* fill in with project details */));

        // Mock the behavior of the service method
        when(projectServiceMock.findAllByUserId(anyLong())).thenReturn(expectedProjects);

        // When
        List<ProjectDto> result = projectServiceMock.findAllByUserId(userId);

        // Then
        // Verify that the findAllByUserId method was called with the correct parameter
        verify(projectServiceMock, times(1)).findAllByUserId(userId);

        // Verify that the returned list of projects is the same as the expected list
        assertEquals(expectedProjects, result);
    }

    @Test
    void getAllProjectsByDepartmentName() {
        // Given
        String departmentName = "ExampleDepartment"; // Replace with the actual department name
        List<ProjectDto> expectedProjects = Arrays.asList(new ProjectDto(/* fill in with project details */));

        // Mock the behavior of the service method
        when(projectServiceMock.getAllProjectsByDepartmentName(anyString())).thenReturn(expectedProjects);

        // When
        List<ProjectDto> result = projectServiceMock.getAllProjectsByDepartmentName(departmentName);

        // Then
        // Verify that the getAllProjectsByDepartmentName method was called with the correct parameter
        verify(projectServiceMock, times(1)).getAllProjectsByDepartmentName(departmentName);

        // Verify that the returned list of projects is the same as the expected list
        assertEquals(expectedProjects, result);
    }


    @Test
    void updateProjectClosed() {
        // Given
        long projectId = 1L; // Replace with the actual project ID
        boolean newClosedCondition = true; // Replace with the expected new closed condition

        // Mock the behavior of the service method
        doNothing().when(projectServiceMock).updateProjectClosed(anyLong(), anyBoolean());

        // When
        projectServiceMock.updateProjectClosed(projectId, newClosedCondition);

        // Then
        // Verify that the updateProjectClosed method was called with the correct parameters
        verify(projectServiceMock, times(1)).updateProjectClosed(projectId, newClosedCondition);
    }

    @Test
    void updateUserListInProject() {
        // Given
        long projectId = 1L; // Replace with the actual project ID
        List<UserDto> newUsers = Arrays.asList(new UserDto(/* fill in with user details */));

        // Mock the behavior of the service method
        doNothing().when(projectServiceMock).updateUserListInProject(anyLong(), anyList());

        // When
        projectServiceMock.updateUserListInProject(projectId, newUsers);

        // Then
        // Verify that the updateUserListInProject method was called with the correct parameters
        verify(projectServiceMock, times(1)).updateUserListInProject(projectId, newUsers);
    }

}