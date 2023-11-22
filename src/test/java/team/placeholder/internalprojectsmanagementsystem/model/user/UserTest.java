package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.Notification;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserTest {
    @Mock
    private User user;
    @Mock
    private Notification notification;
    @Mock
    UserRepository repository = mock(UserRepository.class);
    @Mock
    ProjectRepository projectRepository = mock(ProjectRepository.class);

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John");
        user1.setEmail("john@example.com");

        User user2 = new User();
        user2.setId(1L); // Same ID as user1
        user2.setName("Jane");
        user2.setEmail("jane@example.com");

        User user3 = new User();
        user3.setId(2L); // Different ID
        user3.setName("John");
        user3.setEmail("john@example.com");

        // Act & Assert
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testRelationships() {
        // Arrange
        user = new User();

        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");

        user.getProjects().add(project);
        user.setDepartment(new Department());
        user.setTasks(new ArrayList<>());

        // Act & Assert
        assertEquals(1, user.getProjects().size());
        assertNotNull(user.getDepartment());
        assertNotNull(user.getTasks());
        assertTrue(user.getTasks().isEmpty());
    }

    @Test
    void testSetterGetter() {
        // Arrange
        user = new User();

        // Act
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setEnabled(true);
        user.setRole(Role.FOC);

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("John", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertTrue(user.isEnabled());
        assertEquals(Role.FOC, user.getRole());
    }







}

