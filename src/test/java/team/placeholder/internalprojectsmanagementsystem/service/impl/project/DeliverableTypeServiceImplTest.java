package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableTypeRepo;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeliverableTypeServiceImplTest {
    @Mock
    private DeliverableTypeRepo deliverableTypeRepo;

    @InjectMocks
    private DeliverableTypeServiceImpl deliverableTypeService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {

        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        deliverableTypeDto.setId(1L);
        deliverableTypeDto.setType("Type 1");

        DeliverableType savedDeliverableType = new DeliverableType();
        savedDeliverableType.setId(1L);
        savedDeliverableType.setType("Type 1");

        when(deliverableTypeRepo.save(any(DeliverableType.class))).thenReturn(savedDeliverableType);

        // Act
        DeliverableType result = deliverableTypeService.save(deliverableTypeDto);

        // Assert
        assertEquals(savedDeliverableType, result);

        // Verify that save method is called
        verify(deliverableTypeRepo, times(1)).save(any(DeliverableType.class));
    }

    @Test
    void getAll() {
        DeliverableType deliverableType = new DeliverableType();
        deliverableType.setId(1L);
        deliverableType.setType("Type 1");

        List<DeliverableType> deliverableTypes = Collections.singletonList(deliverableType);

        when(deliverableTypeRepo.findAll()).thenReturn(deliverableTypes);

        // Act
        Set<DeliverableTypeDto> result = deliverableTypeService.getAll();

        // Assert
        assertEquals(1, result.size());

        // Verify that findAll method is called
        verify(deliverableTypeRepo, times(1)).findAll();
    }
}