package team.placeholder.internalprojectsmanagementsystem.dto.mapper.user;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    @Test
    public void testToClientDto() {
        // Create a sample Client
        Client client = new Client();
        client.setId(1L);
        client.setName("Sample Client");
        client.setEmail("sample@example.com");
        client.setPhone("123-456-7890");

        // Use the mapper to convert the sample Client to ClientDto
        ClientDto clientDto = ClientMapper.toClientDto(client);

        // Verify that the mapping is correct
        assertEquals(1L, clientDto.getId());
        assertEquals("Sample Client", clientDto.getName());
        assertEquals("sample@example.com", clientDto.getEmail());
        assertEquals("123-456-7890", clientDto.getPhone());
    }

    @Test
    public void testToClientDto_NullInput() {
        // Use the mapper to convert a null Client to ClientDto
        ClientDto clientDto = ClientMapper.toClientDto(null);

        // Verify that the result is also null
        assertNull(clientDto);
    }

    @Test
    public void testToClient() {
        // Create a sample ClientDto
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("Sample Client");
        clientDto.setEmail("sample@example.com");
        clientDto.setPhone("123-456-7890");

        // Use the mapper to convert the sample ClientDto to Client
        Client client = ClientMapper.toClient(clientDto);

        // Verify that the mapping is correct
        assertEquals(1L, client.getId());
        assertEquals("Sample Client", client.getName());
        assertEquals("sample@example.com", client.getEmail());
        assertEquals("123-456-7890", client.getPhone());
    }

    @Test
    public void testToClient_NullInput() {
        // Use the mapper to convert a null ClientDto to Client
        Client client = ClientMapper.toClient(null);

        // Verify that the result is also null
        assertNull(client);
    }

}