package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.Test;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    public void testClientProperties() {
        // Create a Client object and set its properties
        Client client = new Client();
        client.setId(1L);
        client.setName("John");
        client.setPhone("123-456-7890");
        client.setEmail("john@example.com");

        // Verify that the properties can be retrieved correctly
        assertEquals(1L, client.getId());
        assertEquals("John", client.getName());
        assertEquals("123-456-7890", client.getPhone());
        assertEquals("john@example.com", client.getEmail());
    }

    @Test
    public void testClientProjectRelationship() {
        // Create a Project object
        Project project = new Project();
        project.setId(1L);

        // Create a Client object without associating any projects
        Client client = new Client();

        // Verify that the project list is initially empty (null)
        assertNull(client.getProject());

        // Add the project to the client
        client.getProject().add(project);

        // Verify that the project was added to the client's projects
        assertTrue(client.getProject().contains(project));

        // Verify that the client is set as the project's client
        assertEquals(client, project.getClient());

        // Remove the project from the client
        client.getProject().remove(project);

        // Verify that the project was removed from the client's projects
        assertFalse(client.getProject().contains(project));
    }
}



