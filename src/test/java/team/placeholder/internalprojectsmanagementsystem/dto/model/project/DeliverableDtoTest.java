package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DeliverableDtoTest {
    @Mock
    private DeliverableDto deliverableDto;

    @Test
    void testGetterAndSetter() {
        // Arrange
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(1L);
        deliverableDto.setStatus(true);
        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        deliverableDto.setDeliverableType(deliverableTypeDto);

        long id = deliverableDto.getId();
        boolean status = deliverableDto.isStatus();
        DeliverableTypeDto retrievedDeliverableType = deliverableDto.getDeliverableType();

        // Assert
        assertEquals(1L, id);
        assertTrue(status);
        assertEquals(deliverableTypeDto, retrievedDeliverableType);
    }

    @Test
    void testToString() {
        // Arrange
        DeliverableDto deliverableDto = new DeliverableDto();
        deliverableDto.setId(1L);
        deliverableDto.setStatus(true);
        DeliverableTypeDto deliverableTypeDto = new DeliverableTypeDto();
        deliverableDto.setDeliverableType(deliverableTypeDto);

        // Act
        String toStringResult = deliverableDto.toString();

        // Assert
        assertTrue(toStringResult.contains("id=1"));
        assertTrue(toStringResult.contains("status=true"));
        assertTrue(toStringResult.contains("deliverableType=" + deliverableTypeDto.toString()));
    }
}

