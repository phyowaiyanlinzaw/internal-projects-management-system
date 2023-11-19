package team.placeholder.internalprojectsmanagementsystem.service.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableTypeRepo;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.DeliverableTypeServiceImpl;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliverableTypeServiceTest {

    @Mock
    private DeliverableTypeRepo deliverableTypeRepo;

    @InjectMocks
    private DeliverableTypeServiceImpl deliverableTypeService;

    @Test
    void testSaveDeliverableType() {
        // Arrange
        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        DeliverableType deliverableType = new DeliverableType();
        when(deliverableTypeRepo.save(Mockito.any(DeliverableType.class))).thenReturn(deliverableType);

        // Act
        DeliverableType savedDeliverableType = deliverableTypeService.save(deliverableTypeDto);

        // Assert
        assertEquals(deliverableType, savedDeliverableType);
        verify(deliverableTypeRepo).save(Mockito.any(DeliverableType.class));
    }

    @Test
    void testGetAllDeliverableTypes() {
        // Arrange
        DeliverableType deliverableType1 = new DeliverableType();
        DeliverableType deliverableType2 = new DeliverableType();
        when(deliverableTypeRepo.findAll()).thenReturn(Arrays.asList(deliverableType1, deliverableType2));

        // Act
        Set<DeliverableTypeDto> allDeliverableTypes = deliverableTypeService.getAll();

        // Assert
        assertEquals(2, allDeliverableTypes.size());
        verify(deliverableTypeRepo).findAll();
    }
}
