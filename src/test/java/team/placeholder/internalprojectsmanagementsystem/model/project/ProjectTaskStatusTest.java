package team.placeholder.internalprojectsmanagementsystem.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.project.projectenums.TaskStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTaskStatusTest {
    @Mock
    private TaskStatus taskStatus;

    @Test
    public void testConstructorAndGettersSetters() {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setId(1);
        projectStatus.setName(TaskStatus.IN_PROGRESS);

        assertEquals(1, projectStatus.getId());
        assertEquals(TaskStatus.IN_PROGRESS, projectStatus.getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        ProjectStatus projectStatus1 = new ProjectStatus();
        projectStatus1.setId(1);

        ProjectStatus projectStatus2 = new ProjectStatus();
        projectStatus2.setId(1);

        ProjectStatus projectStatus3 = new ProjectStatus();
        projectStatus3.setId(2);

        // Check equals method
        assertTrue(projectStatus1.equals(projectStatus2));
        assertFalse(projectStatus1.equals(projectStatus3));

        // Check hashCode method
        assertEquals(projectStatus1.hashCode(), projectStatus2.hashCode());
        assertNotEquals(projectStatus1.hashCode(), projectStatus3.hashCode());
    }

    @Test
    public void testNullEquality() {
        ProjectStatus projectStatus1 = new ProjectStatus();
        ProjectStatus projectStatus2 = null;

        assertFalse(projectStatus1.equals(projectStatus2));
    }

    @Test
    public void testEqualityWithDifferentClass() {
        ProjectStatus projectStatus = new ProjectStatus();
        Object otherObject = new Object();

        assertFalse(projectStatus.equals(otherObject));
    }

    @Test
    public void testEqualityWithDifferentIds() {
        ProjectStatus projectStatus1 = new ProjectStatus();
        projectStatus1.setId(1);

        ProjectStatus projectStatus2 = new ProjectStatus();
        projectStatus2.setId(2);

        assertFalse(projectStatus1.equals(projectStatus2));
    }

    @Test
    public void testHashCodeConsistency() {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setId(1);

        int initialHashCode = projectStatus.hashCode();
        assertEquals(initialHashCode, projectStatus.hashCode());
    }

    @Test
    public void testHashCodeWithNullId() {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setId(1);

        assertEquals(Objects.hash(1), projectStatus.hashCode());
    }

    @Test
    public void testProjectAssociation() {
        ProjectStatus projectStatus = new ProjectStatus();
        Project project = new Project();
        project.setId(1L);

        // Set the project in the ProjectStatus object
        projectStatus.setProject(project);

        // Verify that the project can be retrieved
        assertEquals(project, projectStatus.getProject());

        // Make sure the project's ID matches what we set
        assertEquals(1L, projectStatus.getProject().getId());

    }

}

