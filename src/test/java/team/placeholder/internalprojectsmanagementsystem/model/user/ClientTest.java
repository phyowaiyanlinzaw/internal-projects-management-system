package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientTest {

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("John");
        client1.setPhone("123456789");
        client1.setEmail("john@example.com");

        Client client2 = new Client();
        client2.setId(1L); // Same ID as client1
        client2.setName("Jane");
        client2.setPhone("987654321");
        client2.setEmail("jane@example.com");

        Client client3 = new Client();
        client3.setId(2L); // Different ID
        client3.setName("John");
        client3.setPhone("123456789");
        client3.setEmail("john@example.com");

        // Act & Assert
        assertEquals(client1, client2);
        assertEquals(client1.hashCode(), client2.hashCode());

        assertNotEquals(client1, client3);
        assertNotEquals(client1.hashCode(), client3.hashCode());
    }

    @Test
    void testSetterGetter() {
        // Arrange
        Client client = new Client();

        // Act
        client.setId(1L);
        client.setName("John");
        client.setPhone("123456789");
        client.setEmail("john@example.com");

        // Assert
        assertEquals(1L, client.getId());
        assertEquals("John", client.getName());
        assertEquals("123456789", client.getPhone());
        assertEquals("john@example.com", client.getEmail());
    }
}
