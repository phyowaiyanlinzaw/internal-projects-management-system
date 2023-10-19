package team.placeholder.internalprojectsmanagementsystem.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveClient() {
        ClientDto clientDto = new ClientDto();

        Mockito.when(clientService.save(clientDto)).thenReturn(clientDto);
        ClientDto savedClient = clientService.save(clientDto);
        assertEquals(clientDto, savedClient);
    }

    @Test
    public void  testGetAllClient() {
        List<ClientDto> clientDto = new ArrayList<>();

        Mockito.when(clientService.getAllClient()).thenReturn(clientDto);

        List<ClientDto> allClient = clientService.getAllClient();
        assertEquals(clientDto, allClient);
    }

}