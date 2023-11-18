package team.placeholder.internalprojectsmanagementsystem.repository.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;


public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        long id = 1L;
        Client client = new Client();
        client.setId(id);

        // Mock the behavior of clientRepository.findById
        when(clientRepository.findById(id)).thenReturn(client);

        // Act
        Client result = clientRepository.findById(id);

        // Assert
        assertEquals(id, result.getId());
    }







}
