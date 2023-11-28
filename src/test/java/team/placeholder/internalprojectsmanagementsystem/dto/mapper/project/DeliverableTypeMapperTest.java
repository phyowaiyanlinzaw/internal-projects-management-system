package team.placeholder.internalprojectsmanagementsystem.dto.mapper.project;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.project.DeliverableTypeDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.DeliverableType;

import static org.junit.jupiter.api.Assertions.*;

class DeliverableTypeMapperTest {
    @Test
    public void testToDeliverableTypeDto() {
        // Create a sample DeliverableType
        DeliverableType deliverableType = new DeliverableType();
        deliverableType.setId(1L);
        deliverableType.setType("SampleType");

        // Use the mapper to convert the sample DeliverableType to DeliverableTypeDto
        DeliverableTypeDto deliverableTypeDto = DeliverableTypeMapper.toDeliverableTypeDto(deliverableType);

        // Verify that the mapping is correct
        assertEquals(1L, deliverableTypeDto.getId());
        assertEquals("SampleType", deliverableTypeDto.getType());
    }

    @Test
    public void testToDeliverableTypeDto_NullInput() {
        // Use the mapper to convert a null DeliverableType to DeliverableTypeDto
        DeliverableTypeDto deliverableTypeDto = DeliverableTypeMapper.toDeliverableTypeDto(null);

        // Verify that the result is also null
        assertNull(deliverableTypeDto);
    }

    @Test
    public void testToDeliverableType() {
        // Create a sample DeliverableTypeDto
        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        deliverableTypeDto.setId(1L);
        deliverableTypeDto.setType("SampleType");

        // Use the mapper to convert the sample DeliverableTypeDto to DeliverableType
        DeliverableType deliverableType = DeliverableTypeMapper.toDeliverableType(deliverableTypeDto);

        // Verify that the mapping is correct
        assertEquals(1L, deliverableType.getId());
        assertEquals("SampleType", deliverableType.getType());
    }

    @Test
    public void testToDeliverableType_NullInput() {
        // Use the mapper to convert a null DeliverableTypeDto to DeliverableType
        DeliverableType deliverableType = DeliverableTypeMapper.toDeliverableType(null);

        // Verify that the result is also null
        assertNull(deliverableType);
    }

}