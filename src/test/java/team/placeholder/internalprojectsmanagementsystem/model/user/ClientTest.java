package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @MockBean
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
    }

    @Test
    public void testId() {
        long clientId = 1;
        client.setId(clientId);
        assertEquals(clientId, client.getId());
    }

    @Test
    public void testName() {
        String name = "Ei Ei Phyo";
        client.setName(name);
        assertEquals(name, client.getName());
    }

    @Test
    public void testPhone() {
        String phone = "09-123456789";
        client.setPhone(phone);
        assertEquals(phone, client.getPhone());
    }

    @Test
    public void testEmail() {
        String email = "eep@gmail.com";
        client.setEmail(email);
        assertEquals(email, client.getEmail());
    }

    @Test
    public void testProjects() {
        List<Project> projects = new ArrayList<>();
        Project project1 = new Project();
        Project project2 = new Project();

        projects.add(project1);
        projects.add(project2);

        client.setProject(projects);
        assertEquals(projects, client.getProject());

        // Test cascade and orphan removal
        client.setProject(new ArrayList<>());
        assertTrue(client.getProject().isEmpty());
    }
    @Test
    public void testEqualsAndHashCode() {
        // Create two Client objects with the same id
        Client client1 = new Client();
        client1.setId(1L);

        Client client2 = new Client();
        client2.setId(1L);

        // Test equals method
        assertTrue(client1.equals(client2));
        assertTrue(client2.equals(client1));

        // Test hashCode method
        assertEquals(client1.hashCode(), client2.hashCode());
    }

    @Test
    public void testNotEquals() {
        // Create two Client objects with different ids
        Client client1 = new Client();
        client1.setId(1L);

        Client client2 = new Client();
        client2.setId(2L);

        // Test equals method
        assertFalse(client1.equals(client2));
        assertFalse(client2.equals(client1));

        // As a rule, it's a good practice for different objects to have different hash codes.
        assertNotEquals(client1.hashCode(), client2.hashCode());
    }
}
