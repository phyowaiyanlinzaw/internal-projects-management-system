package team.placeholder.internalprojectsmanagementsystem.repository.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryTest {

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testFindById() {
        // Arrange
        long id = 1L;
        Project project = new Project();
        project.setId(id);

        // Mock the behavior of projectRepository.findById
        when(projectRepository.findById(id)).thenReturn(project);

        // Act
        Project result = projectRepository.findById(id);

        // Assert
        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByName() {
        // Arrange
        String name = "TestProject";
        Project project = new Project();
        project.setName(name);

        // Mock the behavior of projectRepository.findByName
        when(projectRepository.findByName(name)).thenReturn(project);

        // Act
        Project result = projectRepository.findByName(name);

        // Assert
        assertEquals(name, result.getName());
    }
}
