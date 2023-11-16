package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.ClientServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ClientControllerTest {

    @Mock
    private ClientServiceImpl clientService;

    @Mock
    private FakerService fakerService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateFakeClient() {
        // Arrange
        int count = 5;

        // Mock behavior
        doNothing().when(fakerService).generateAndSaveFakeClients(count);

        // Act
        ResponseEntity<String> responseEntity = clientController.generateFakeClient(count);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fake clients generated successfully", responseEntity.getBody());

        // Verify that the service method was called with the correct parameter
        verify(fakerService, times(1)).generateAndSaveFakeClients(count);
    }

    @Test
    void getAllClients() {
        List<ClientDto> mockClientDtoList = Arrays.asList(new ClientDto(), new ClientDto());
        when(clientService.getAllClient()).thenReturn(mockClientDtoList);

        ResponseEntity<List<ClientDto>> responseEntity = clientController.getAllClients();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockClientDtoList, responseEntity.getBody());
        verify(clientService, times(1)).getAllClient();
        ResponseEntity<List<ClientDto>> expectedResponse = ResponseEntity.ok(mockClientDtoList);
         assertEquals(expectedResponse, responseEntity);
    }

    @Test
    void testGetAllClientsWhenListIsNull() {
        when(clientService.getAllClient()).thenReturn(null);

        ResponseEntity<List<ClientDto>> responseEntity = clientController.getAllClients();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());

        verify(clientService, times(1)).getAllClient();
    }

    @Test
    void testSave() throws Exception {
        // Arrange
        ClientDto clientDto = new ClientDto();
        when(clientService.save(clientDto)).thenReturn(clientDto);

        ResponseEntity<ClientDto> responseEntity = clientController.save(clientDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(clientDto, responseEntity.getBody());
        verify(clientService, times(1)).save(clientDto);

    }

    @Test
    void testCountAll() {
        // Arrange
        Long expectedCount = 10L;
        // Mock the service response
        when(clientService.countAll()).thenReturn(expectedCount);

        ResponseEntity<Long> responseEntity = clientController.countAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedCount, responseEntity.getBody());

        // Verify that the service method was called
        verify(clientService, times(1)).countAll();
    }

    @Test
    void testCountAllWhenCountIsNull() {
        when(clientService.countAll()).thenReturn(null);

        // Act
        ResponseEntity<Long> responseEntity = clientController.countAll();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());

        verify(clientService, times(1)).countAll();
    }

    @Test
    void testFindByProjectName() {
        String projectName = "exampleProject";
        ClientDto expectedClient = new ClientDto();

        when(clientService.findByProjectName(projectName)).thenReturn(expectedClient);

        // Act
        ResponseEntity<ClientDto> responseEntity = clientController.findByProjectName(projectName);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedClient, responseEntity.getBody());

        verify(clientService, times(1)).findByProjectName(projectName);
    }

    @Test
    void testFindByProjectNameNull() {
        String projectName = "exampleProject";
        ClientDto expectedClient = null;

        when(clientService.findByProjectName(projectName)).thenReturn(expectedClient);

        // Act
        ResponseEntity<ClientDto> responseEntity = clientController.findByProjectName(projectName);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(expectedClient, responseEntity.getBody());

        verify(clientService, times(1)).findByProjectName(projectName);
    }


}