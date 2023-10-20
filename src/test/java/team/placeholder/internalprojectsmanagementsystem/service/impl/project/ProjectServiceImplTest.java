//package team.placeholder.internalprojectsmanagementsystem.service.impl.project;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import team.placeholder.internalprojectsmanagementsystem.dto.mapper.project.ProjectMapper;
//import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
//import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
//import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class ProjectServiceImplTest {
//
//    @Mock
//    private ProjectRepository projectRepository;
//
//    @Mock
//    private ProjectMapper projectMapper;
//
//    @InjectMocks
//    private ProjectServiceImpl projectService;
//
//    public ProjectServiceImplTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void save() {
//        // Create a sample ProjectDto and Project objects
//        ProjectDto projectDto = new ProjectDto(); // Set necessary properties
//        Project project = new Project(); // Set necessary properties
//
//        // Mock the behavior of projectMapper and projectRepository
//        when(projectMapper.toProject(projectDto)).thenReturn(project);
//        when(projectRepository.save(project)).thenReturn(project);
//
//        // Call the method to be tested
//        ProjectDto savedProjectDto = projectService.save(projectDto);
//
//        // Verify that the save method was called with the correct argument
//        verify(projectRepository, times(1)).save(project);
//
//        // Verify that projectMapper.toProjectDto was called
//        verify(projectMapper, times(1)).toProjectDto(project);
//
//        // Assert the result
//        assertEquals(savedProjectDto, projectMapper.toProjectDto(project));
//    }
//
//    @Test
//    void getAllProjects() {
//        // Create a list of sample Project objects
//        List<Project> projects = new ArrayList<>();
//        projects.add(new Project()); // Add sample projects
//
//        // Mock the behavior of projectRepository
//        when(projectRepository.findAll()).thenReturn(projects);
//
//        // Call the method to be tested
//        List<ProjectDto> projectDtos = projectService.getAllProjects();
//
//        // Assert the result
//        assertEquals(projects.size(), projectDtos.size());
//    }
//
//    // Add more test methods for other methods in ProjectServiceImpl as needed
//}
