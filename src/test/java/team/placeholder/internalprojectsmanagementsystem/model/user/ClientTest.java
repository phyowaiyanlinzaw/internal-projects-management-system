package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
