package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@Slf4j

class ClientServiceImplTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Client client;
    @Mock
    private ClientDto clientDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(clientRepository, modelMapper);
    }



    @Test
    public void testSave() {

        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        Client savedClient = new Client();
        when(modelMapper.map(savedClient, ClientDto.class)).thenReturn(clientDto);
        when(clientRepository.save(client)).thenReturn(savedClient);

        // Calling the method under test
        ClientDto result = clientService.save(clientDto);

        // Verifying interactions and assertions
        verify(clientRepository, times(1)).save(client);
        verify(modelMapper, times(1)).map(savedClient, ClientDto.class);
        assertEquals(clientDto, result);
    }


    @Test
    void testGetAllClientWhenClientsExistThenReturnListOfClientDto() {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client();
        client1.setName("John Doe");
        client1.setEmail("johndoe@example.com");
        client1.setPhone("123-456-7890");

        Client client2 = new Client();
        client2.setName("John Doe");
        client2.setEmail("janesmith@example.com");
        client2.setPhone("987-654-3210");

        clients.add(client1);
        clients.add(client2);

        when(clientRepository.findAll()).thenReturn(clients);

        ClientDto clientDto1 = new ClientDto();
        clientDto1.setName(client1.getName());
        clientDto1.setEmail(client1.getEmail());
        clientDto1.setPhone(client1.getPhone());

        ClientDto clientDto2 = new ClientDto();
        clientDto2.setName(client2.getName());
        clientDto2.setEmail(client2.getEmail());
        clientDto2.setPhone(client2.getPhone());

        when(modelMapper.map(client1, ClientDto.class)).thenReturn(clientDto1);
        when(modelMapper.map(client2, ClientDto.class)).thenReturn(clientDto2);

        List<ClientDto> clientDtos = clientService.getAllClient();

        verify(clientRepository, times(1)).findAll();
        assertEquals(2, clientDtos.size());
        assertEquals(clientDto1.getName(), clientDtos.get(0).getName());
        assertEquals(clientDto2.getName(), clientDtos.get(1).getName());
    }

    @Test
    public void testGetAllClient() {
        // Mock behavior
        List<Client> clientList = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(clientList);

        // Call the method to be tested
        List<ClientDto> result = clientService.getAllClient();

        // Verify the behavior
        verify(clientRepository, times(1)).findAll();
        assertEquals(clientList.size(), result.size());
    }

    @Test
    public void testCountAll() {
        // Test data
        long expectedCount = 10L;

        // Mock behavior
        when(clientRepository.count()).thenReturn(expectedCount);

        // Call the method to be tested
        Long result = clientService.countAll();

        // Verify the behavior
        verify(clientRepository, times(1)).count();
        assertEquals(expectedCount, result);
    }

    @Test
    public void testFindByProjectName() {

        String projectName = "TestProject";
        Client client = new Client();
        ClientDto clientDto = new ClientDto();
        when(clientRepository.findByProjectName(projectName)).thenReturn(client);
        when(modelMapper.map(client, ClientDto.class)).thenReturn(clientDto);

        // Calling the method under test
        ClientDto result = clientService.findByProjectName(projectName);

        // Verifying interactions and assertions
        verify(clientRepository, times(1)).findByProjectName(projectName);
        verify(modelMapper, times(1)).map(client, ClientDto.class);
        assertEquals(clientDto, result);
    }


}