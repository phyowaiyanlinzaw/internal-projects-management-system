package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.Deliverable;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliverableMapperTest {

    @Test
    public void testToDeliverableDto() {
        Deliverable deliverable = new Deliverable();
        deliverable.setId(1L);
        deliverable.setStatus(true);
        deliverable.setDeliverableTypes(new DeliverableType());

        // Mock DeliverableTypeMapper
        DeliverableTypeMapper deliverableTypeMapper = mock(DeliverableTypeMapper.class);

        // Correct usage of matchers for reference types
        when(deliverableTypeMapper.toDeliverableTypeDto(any())).thenReturn(new DeliverableTypeDto());

        // Use the mapper to convert the sample Deliverable to DeliverableDto
        DeliverableDto deliverableDto = DeliverableMapper.toDeliverableDto(deliverable);

        // Verify that the mapping is correct
        assertEquals(1L, deliverableDto.getId());
        assertTrue(deliverableDto.isStatus());
        assertNotNull(deliverableDto.getDeliverableType());

        // Verify that the DeliverableTypeMapper method was called with the correct argument
        verify(deliverableTypeMapper, times(1)).toDeliverableTypeDto(any());
    }


    // Add similar tests for toDeliverable, toDeliverableDtos, and toDeliverables

    @Test
    public void testToDeliverableDto_ListWithElements() {
        // Create a list of sample Deliverables
        List<Deliverable> deliverables = new ArrayList<>();
        Deliverable deliverable1 = new Deliverable();
        deliverable1.setId(1L);
        deliverable1.setStatus(true);
        deliverable1.setDeliverableTypes(new DeliverableType());
        deliverables.add(deliverable1);

        Deliverable deliverable2 = new Deliverable();
        deliverable2.setId(2L);
        deliverable2.setStatus(false);
        deliverable2.setDeliverableTypes(new DeliverableType());
        deliverables.add(deliverable2);

        // Mock DeliverableTypeMapper
        DeliverableTypeMapper deliverableTypeMapper = mock(DeliverableTypeMapper.class);
        when(deliverableTypeMapper.toDeliverableTypeDto(any())).thenReturn(new DeliverableTypeDto());

        // Use the mapper to convert the list to a list of DeliverableDtos
        List<DeliverableDto> deliverableDtos = DeliverableMapper.toDeliverableDtos(deliverables);

        // Verify that the mapping is correct for each element
        assertEquals(2, deliverableDtos.size());
        assertEquals(1L, deliverableDtos.get(0).getId());
        assertTrue(deliverableDtos.get(0).isStatus());
        assertNotNull(deliverableDtos.get(0).getDeliverableType());
        verify(deliverableTypeMapper, times(2)).toDeliverableTypeDto(any());
    }


    @Test
    public void testToDeliverableDto_NullInput() {
        // Use the mapper to convert a null Deliverable to DeliverableDto
        DeliverableDto deliverableDto = DeliverableMapper.toDeliverableDto(null);

        // Verify that the result is also null
        assertNull(deliverableDto);
    }

    @Test
    public void testToDeliverable_NullInput() {
        // Use the mapper to convert a null DeliverableDto to Deliverable
        Deliverable deliverable = DeliverableMapper.toDeliverable(null);

        // Verify that the result is also null
        assertNull(deliverable);
    }

    @Test
    public void testToDeliverableDto_ListWithNulls() {
        // Use the mapper to convert a list with null Deliverables to a list of DeliverableDtos
        List<DeliverableDto> deliverableDtos = DeliverableMapper.toDeliverableDtos(Arrays.asList(null, null));

        // Verify that the result is an empty list
        assertNotNull(deliverableDtos);
        assertTrue(deliverableDtos.isEmpty());
    }

    @Test
    public void testToDeliverable_ListWithNulls() {
        // Use the mapper to convert a list with null DeliverableDtos to a list of Deliverables
        List<Deliverable> deliverables = DeliverableMapper.toDeliverables(Arrays.asList(null, null));

        // Verify that the result is an empty list
        assertNotNull(deliverables);
        assertTrue(deliverables.isEmpty());
    }

}