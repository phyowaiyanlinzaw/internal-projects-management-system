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
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        user.setProject(new ArrayList<>());
        user.setManagedUsers(new ArrayList<>());

        // Act & Assert
        assertEquals(1, user.getProjects().size());
        assertNotNull(user.getDepartment());
        assertNotNull(user.getTasks());
        assertTrue(user.getTasks().isEmpty());
        assertNotNull(user.getProject());
        assertTrue(user.getProject().isEmpty());
        assertNotNull(user.getManagedUsers());
        assertTrue(user.getManagedUsers().isEmpty());

        User user = new User();

        // Act
        user.getProjects().add(project);
        user.setDepartment(new Department());
        user.setTasks(new ArrayList<>());

        // Assert
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

        User user = new User();

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

    @Test
    void testUserProjectRelationship() {
        // Arrange
        User user = new User();
        Project project = new Project();
        project.setId(1L);
        project.setName("Sample Project");

        // Act
        user.getProjects().add(project);

        // Assert
        assertEquals(1, user.getProjects().size());
        assertTrue(user.getProjects().contains(project));
    }

    @Test
    void testUserProjectManagerRelationship() {
        // Arrange
        User user = new User();
        User projectManager = new User();
        projectManager.setId(1L);
        projectManager.setName("Project Manager");

        // Act
        user.setProjectManager(projectManager);

        // Assert
        assertEquals(projectManager, user.getProjectManager());
    }

    @Test
    void testUserSave() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");

        // Set up mock behavior
        when(repository.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = repository.save(user);

        // Assert
        assertEquals(user, savedUser);
        verify(repository, times(1)).save(user);
    }

    @Test
    void testUserNullValues() {
        // Arrange
        User user = new User();

        // Act
        user.setId(Long.MIN_VALUE);
        user.setEmail(null);

        // Assert
        assertEquals(Long.MIN_VALUE, user.getId());
        assertNull(user.getEmail());
    }

    @Test
    void testProjectManagerRelationship() {
        // Arrange
        User projectManager = new User();
        projectManager.setId(1L);
        projectManager.setName("John");
        projectManager.setEmail("john@example.com");
        projectManager.setPassword("password");
        projectManager.setEnabled(true);
        projectManager.setRole(Role.FOC);

        // Creating projects
        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");

        // Adding projects to the project manager's list
        projectManager.getProjects().add(project1);
        projectManager.getProjects().add(project2);

        // Act & Assert
        assertEquals(2, projectManager.getProjects().size());
        assertTrue(projectManager.getProjects().contains(project1));
        assertTrue(projectManager.getProjects().contains(project2));
    }

    @Test
    void testUserNotifications() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setEnabled(true);
        user.setRole(Role.FOC);

        // Creating notifications
        Notification notification1 = new Notification();
        notification1.setId(1L);

        Notification notification2 = new Notification();
        notification2.setId(2L);

        // Adding notifications to the user's list
        user.getNotifications().add(notification1);
        user.getNotifications().add(notification2);

        // Act & Assert
        assertEquals(2, user.getNotifications().size());
        assertTrue(user.getNotifications().contains(notification1));
        assertTrue(user.getNotifications().contains(notification2));
    }


}

