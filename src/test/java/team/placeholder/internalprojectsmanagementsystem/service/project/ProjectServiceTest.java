package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProject() {
        ProjectDto projectDto = new ProjectDto();
        Mockito.when(projectService.save(projectDto)).thenReturn(projectDto);
        ProjectDto savedProject = projectService.save(projectDto);
        assertEquals(projectDto, savedProject);
    }

    @Test
    public void testGetAllProjects() {
        List<ProjectDto> projectDto = new ArrayList<>();
        Mockito.when(projectService.getAllProjects()).thenReturn(projectDto);
        List<ProjectDto> allProjects = projectService.getAllProjects();
        assertEquals(projectDto, allProjects);
    }

    @Test
    public void testGetProjectById() {
        int id = 1;
        ProjectDto projectDto = new ProjectDto();
        Mockito.when(projectService.getProjectById(id)).thenReturn(projectDto);
        ProjectDto projectById = projectService.getProjectById(1);
        assertEquals(projectDto, projectById);
    }

    @Test
    public void testGetProjectByName() {
        String name = "test";
        ProjectDto projectDto = new ProjectDto();
        Mockito.when(projectService.getProjectByName(name)).thenReturn(projectDto);
        ProjectDto projectByName = projectService.getProjectByName(name);
        assertEquals(projectDto, projectByName);
    }

    @Test
    public void testUpdateProject() {
        ProjectDto projectDto = new ProjectDto();
        Mockito.when(projectService.updateProject(projectDto)).thenReturn(projectDto);
        ProjectDto updatedProject = projectService.updateProject(projectDto);
        assertEquals(projectDto, updatedProject);
    }






}