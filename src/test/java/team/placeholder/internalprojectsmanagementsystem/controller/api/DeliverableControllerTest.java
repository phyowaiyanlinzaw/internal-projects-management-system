package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class DeliverableControllerTest {

    @Mock
    private DeliverableServiceImpl deliverableService;

    @Mock
    private FakerService fakerService;

    @InjectMocks
    private DeliverableController deliverableController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateFakeDeliverable() {
        int count = 10;
        doNothing().when(fakerService).generateAndSaveFakeDeliverable(count);
        String result= deliverableController.generateFakeDeliverable(count);
         assertEquals("Fake deliverables generated successfully",result);
        verify(fakerService, times(1)).generateAndSaveFakeDeliverable(count);
    }


    @Test
    void testUpdateDeliverable() {
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(1L);

        // Mock the behavior of deliverableService.updateDeliverable
        when(deliverableService.updateDeliverable(deliverableDto)).thenReturn(deliverableDto);

        // Act
        ResponseEntity<DeliverableDto> result = deliverableController.updateDeliverable(deliverableDto);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }




}