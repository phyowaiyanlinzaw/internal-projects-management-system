package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveClient() {
        Client client = new Client();
        client.setName("John Doe");
        client.setEmail("johndoe@gmail.com");
        client.setPhone("123-456-7890");
        clientRepository.save(client);
        verify(clientRepository, times(1)).save(client);

    }
    @Test
    public void testGetAllClients() {
        List<Client> list = new ArrayList<>();
        Client client1 = new Client();
        client1.setName("John Doe");
        client1.setEmail("johndoe@example.com");
        client1.setPhone("123-456-7890");

        Client client2 = new Client();
        client2.setName("Jane Smith");
        client2.setEmail("janesmith@example.com");
        client2.setPhone("987-654-3210");

        list.add(client1);
        list.add(client2);

        when(clientRepository.findAll()).thenReturn(list);
        List<Client> clients = clientRepository.findAll();

        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll();
    }



}