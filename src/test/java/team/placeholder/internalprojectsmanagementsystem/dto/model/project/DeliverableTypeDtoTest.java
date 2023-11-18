package team.placeholder.internalprojectsmanagementsystem.dto.model.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliverableTypeDtoTest {

    @Test
    void testGetterAndSetter() {
        // Arrange
        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        deliverableTypeDto.setId(1L);
        deliverableTypeDto.setType("Zip File");

        Long id = deliverableTypeDto.getId();
        String type = deliverableTypeDto.getType();

        // Assert
        assertEquals(1L, id);
        assertEquals("Zip File", type);
    }


}