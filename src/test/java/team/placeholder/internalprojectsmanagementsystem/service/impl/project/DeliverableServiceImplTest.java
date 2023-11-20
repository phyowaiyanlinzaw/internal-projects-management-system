package team.placeholder.internalprojectsmanagementsystem.service.impl.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.ArchitectureDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;
import team.placeholder.internalprojectsmanagementsystem.repository.project.DeliverableRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class DeliverableServiceImplTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DeliverableServiceImpl deliverableService;
    @Mock
    private DeliverableRepository deliverableRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(1L);
        deliverableDto.setStatus(false);

        Deliverable savedDeliverable = new Deliverable();
        savedDeliverable.setId(1L);
        savedDeliverable.setStatus(false);

        when(modelMapper.map(any(DeliverableDto.class), eq(Deliverable.class))).thenReturn(savedDeliverable);
        when(deliverableRepository.save(any(Deliverable.class))).thenReturn(savedDeliverable);
        when(modelMapper.map(any(Deliverable.class), eq(DeliverableDto.class))).thenReturn(deliverableDto);

        // Act
        DeliverableDto result = deliverableService.save(deliverableDto);

        // Assert
        assertEquals(deliverableDto.getId(), result.getId());
        assertEquals(deliverableDto.isStatus(), result.isStatus());

        // Verify that map and save methods are called
        verify(modelMapper, times(1)).map(deliverableDto, Deliverable.class);
        verify(deliverableRepository, times(1)).save(savedDeliverable);
        verify(modelMapper, times(1)).map(savedDeliverable, DeliverableDto.class);

    }

    @Test
    void getAll() {
        Deliverable deliverable1 = new Deliverable();
        deliverable1.setId(1L);

        Deliverable deliverable2 = new Deliverable();
        deliverable2.setId(2L);

        List<Deliverable> deliverables = Arrays.asList(deliverable1, deliverable2);

        when(deliverableRepository.findAll()).thenReturn(deliverables);

        // Act
        List<DeliverableDto> result = deliverableService.getAll();

        // Assert
        assertEquals(deliverables.size(), result.size());
        assertEquals(deliverable1.getId(), result.get(0).getId());
        assertEquals(deliverable2.getId(), result.get(1).getId());
    }

    @Test
    void updateDeliverable() {
        long deliverableId = 1L;
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(deliverableId);
        deliverableDto.setStatus(true);

        Deliverable existingDeliverable = new Deliverable();
        existingDeliverable.setId(deliverableId);
        existingDeliverable.setStatus(false);

        when(deliverableRepository.findById(deliverableId)).thenReturn(existingDeliverable);
        when(deliverableRepository.save(any(Deliverable.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(modelMapper.map(existingDeliverable, DeliverableDto.class)).thenReturn(deliverableDto);

        // Act
        DeliverableDto result = deliverableService.updateDeliverable(deliverableDto);

        // Assert
        assertEquals(deliverableDto, result);
        assertEquals(true, result.isStatus());
    }
}