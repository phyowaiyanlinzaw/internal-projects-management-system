package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.project.DeliverableTypeService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliverableTypeControllerTest {

    @Mock
    private FakerService fakerService;

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
}