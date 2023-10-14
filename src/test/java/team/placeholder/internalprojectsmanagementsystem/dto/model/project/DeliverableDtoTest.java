package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DeliverableDtoTest {
    @Mock
    private DeliverableDto deliverableDto;

    @Test
    public void testDeliverableDto() {
        // Create a DeliverableDto object with some mock data
        deliverableDto = new DeliverableDto();
        deliverableDto.setId(1L);
        deliverableDto.setDescription("Sample description");
        deliverableDto.setName("Sample Name");
        deliverableDto.setType("Type A");
        deliverableDto.setStatus("In Progress");
        deliverableDto.setDue_date(new Date(System.currentTimeMillis()));

        // Test the getter methods
        assertEquals(1L, deliverableDto.getId());
        assertEquals("Sample description", deliverableDto.getDescription());
        assertEquals("Sample Name", deliverableDto.getName());
        assertEquals("Type A", deliverableDto.getType());
        assertEquals("In Progress", deliverableDto.getStatus());
        assertNotNull(deliverableDto.getDue_date());
    }
}

