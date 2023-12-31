package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.repository.project.ProjectRepository;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableTypeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliverableTypeControllerTest {

    @Mock
    private FakerService fakerService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private DeliverableTypeServiceImpl deliverableTypeService;

    @InjectMocks
    private DeliverableTypeController deliverableTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateFakeDeliverableType() {
        // Arrange
        int count = 5;
        // Act
        ResponseEntity<String> result = deliverableTypeController.generateFakeDeliverableType(count);
        // Assert
        assertEquals("Fake deliverable types generated successfully", result.getBody());
        verify(fakerService, times(1)).generateAndSaveDeliverableTypes(count);
    }

    @Test
    void testGetAll() {
        // Mock data
        Set<DeliverableTypeDto> mockDeliverableTypes = new HashSet<>();
        when(deliverableTypeService.getAll()).thenReturn(mockDeliverableTypes);

        // Call the controller method
        ResponseEntity<Set<DeliverableTypeDto>> responseEntity = deliverableTypeController.getAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockDeliverableTypes, responseEntity.getBody());

        // Verify that the service method was called
        verify(deliverableTypeService, times(1)).getAll();
    }

    @Test
    void testGetAllByProject(){
        long projectId = 1L;
        Project project = new Project();
        when(projectRepository.findById(projectId)).thenReturn(project);

        Set<DeliverableTypeDto> allDeliverableTypes = new HashSet<>();
        allDeliverableTypes.add(new DeliverableTypeDto());
        allDeliverableTypes.add(new DeliverableTypeDto());
        when(deliverableTypeService.getAll()).thenReturn(allDeliverableTypes);

        ResponseEntity<Set<DeliverableTypeDto>> responseEntity = deliverableTypeController.getAllByProject(projectId);

        assertNotNull(responseEntity, "Response entity should not be null");

        // Print information for debugging
        System.out.println("Response Status Code: " + responseEntity.getStatusCodeValue());
        System.out.println("Response Body: " + responseEntity.getBody());

        // Use assertSame to compare HttpStatus instances
        assertSame(HttpStatus.OK, responseEntity.getStatusCode(), "Unexpected status code");

        Set<DeliverableTypeDto> result = responseEntity.getBody();
        assertNotNull(result, "Response body should not be null");
    }
}