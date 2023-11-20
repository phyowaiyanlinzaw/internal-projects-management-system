package team.placeholder.internalprojectsmanagementsystem.repository.project;

import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.parameters.P;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.project.Tasks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProjectRepositoryTest {
    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFindById() {
        long id = 1L;
        Project project = new Project();
        project.setId(id);

        when(projectRepository.findById(id)).thenReturn(project);

        Project result = projectRepository.findById(id);

        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByName() {

        String name = "TestProject";
        Project project = new Project();
        project.setName(name);

        when(projectRepository.findByName(name)).thenReturn(project);

        Project result = projectRepository.findByName(name);

        assertEquals(name, result.getName());
    }

    @Test
    void countAllByUsersId() {
        long userId = 1L;

        long expectedCount = 5L;

        when(projectRepository.countAllByUsersId(userId)).thenReturn(expectedCount);


        long result = projectRepository.countAllByUsersId(userId);

        assertEquals(expectedCount, result);
    }

    @Test
    void findAllByProjectManagerId() {

        long pmId = 1L;
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectRepository.findAllByProjectManagerId(pmId)).thenReturn(projects);
        List<Project> result = projectRepository.findAllByProjectManagerId(pmId);
        assertEquals(projects.size(), result.size());
        for (int i =0; i< projects.size(); i++){
            assertEquals(projects.get(i), result.get(i));
        }
    }

    @Test
    void findAllByReviewsId() {
        long reviewId = 1L;
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectRepository.findAllByProjectManagerId(reviewId)).thenReturn(projects);
        List<Project> result = projectRepository.findAllByReviewsId(reviewId);
        assertEquals(projects.size(), result.size());
        for (int i =0; i< projects.size(); i++){
            assertEquals(projects.get(i), result.get(i));
        }
    }

    @Test
    void findAllByUsersId() {

        long userId = 1L;
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectRepository.findAllByUsersId(userId)).thenReturn(projects);
        List<Project> result = projectRepository.findAllByProjectManagerId(userId);
        assertEquals(projects.size(), result.size());
        for (int i =0; i< projects.size(); i++){
            assertEquals(projects.get(i), result.get(i));
        }
    }

    @Test
    void findAllByClientIsNotNull() {
        List<Project> expectedProjects = new ArrayList<>();
        expectedProjects.add(new Project());

        when(projectRepository.findAllByClientIsNotNull()).thenReturn(expectedProjects);

        List<Project> result = projectRepository.findAllByClientIsNotNull();

        assertEquals(expectedProjects.size(), result.size());
        for (int i = 0; i < expectedProjects.size(); i++) {
            assertEquals(expectedProjects.get(i), result.get(i));
        }
    }

    @Test
    void findAllByProjectManagerIsNotNull() {
        List<Project> expectedProjects = new ArrayList<>();
        expectedProjects.add(new Project());

        when(projectRepository.findAllByProjectManagerIsNotNull()).thenReturn(expectedProjects);

        // Act
        List<Project> result = projectRepository.findAllByProjectManagerIsNotNull();

        // Assert
        assertEquals(expectedProjects.size(), result.size());
        for (int i = 0; i < expectedProjects.size(); i++) {
            assertEquals(expectedProjects.get(i), result.get(i));
        }
    }

    @Test
    void findByDepartmentId() {

        long dpId = 1L;

        List<Project> expectedPro = new ArrayList<>();
        expectedPro.add(new Project());

        when(projectRepository.findByDepartmentId(dpId)).thenReturn(expectedPro);

        List<Project> result = projectRepository.findByDepartmentId(dpId);

        assertEquals(expectedPro.size(), result.size());
        for (int i = 0; i < expectedPro.size(); i++) {
            assertEquals(expectedPro.get(i), result.get(i));
        }

        }


    @Test
    void findByDepartmentName() {
        String dpname = "Test";

        List<Project> expectedPro = new ArrayList<>();
        expectedPro.add(new Project());

        when(projectRepository.findByDepartmentName(dpname)).thenReturn(expectedPro);

        List<Project> result = projectRepository.findByDepartmentId(Long.parseLong(dpname));

        assertEquals(expectedPro.size(), result.size());
        for (int i = 0; i < expectedPro.size(); i++) {
            assertEquals(expectedPro.get(i), result.get(i));
        }
    }

    @Test
    void findByUsersId() {
        long userId = 1L;
        Project expectedProject = new Project();

        // Mock the behavior of projectRepository.findByUsersId
        when(projectRepository.findByUsersId(userId)).thenReturn(expectedProject);

        // Act
        Project result = projectRepository.findByUsersId(userId);

        // Assert
        assertEquals(expectedProject, result);
    }
}