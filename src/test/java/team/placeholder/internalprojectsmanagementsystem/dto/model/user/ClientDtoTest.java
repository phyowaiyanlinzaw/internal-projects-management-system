package team.placeholder.internalprojectsmanagementsystem.dto.model.user;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientDtoTest {
    @Mock
    private ClientDto clientDto;

    @Test
    public void testClientDto() {
        // Create a ClientDto object with some mock data
        clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("John Doe");
        clientDto.setEmail("john.doe@example.com");
        clientDto.setPhone("123-456-7890");

        // Test the getter methods
        assertEquals(1L, clientDto.getId());
        assertEquals("John Doe", clientDto.getName());
        assertEquals("john.doe@example.com", clientDto.getEmail());
        assertEquals("123-456-7890", clientDto.getPhone());
    }
}

