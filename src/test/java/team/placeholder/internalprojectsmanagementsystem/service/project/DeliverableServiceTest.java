package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ProjectDto;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliverableServiceTest {

    @Mock
    private DeliverableRepository deliverableRepository; // Assuming you have a DeliverableRepository


    @Mock
    private DeliverableService deliverableService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testSaveDeliverable() {

        // Arrange
        DeliverableDto deliverableDto = new DeliverableDto();
        when(deliverableService.save(deliverableDto)).thenReturn(deliverableDto);
        // Act
        DeliverableDto savedDeliverable = deliverableService.save(deliverableDto);
        // Assert
        assertEquals(deliverableDto, savedDeliverable);
    }



    @Test
    void testUpdateDeliverable() {
        // Arrange
        DeliverableDto deliverableDto = new DeliverableDto();
        Mockito.when(deliverableService.updateDeliverable(deliverableDto)).thenReturn(deliverableDto);
        // Act
        DeliverableDto updatedDeliverable = deliverableService.updateDeliverable(deliverableDto);
        // Assert
        assertEquals(deliverableDto, updatedDeliverable);

    }
}
