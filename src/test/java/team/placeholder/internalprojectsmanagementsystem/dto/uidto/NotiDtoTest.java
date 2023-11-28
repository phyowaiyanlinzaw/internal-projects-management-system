package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotiDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a NotiDto
        NotiDto notiDto = new NotiDto();
        notiDto.setId(1L);
        notiDto.setDescription("Notification description");
        notiDto.setNoti_time(1636600000000L); // Assuming a timestamp in milliseconds

        // Verify that the getters return the expected values
        assertEquals(1L, notiDto.getId());
        assertEquals("Notification description", notiDto.getDescription());
        assertEquals(1636600000000L, notiDto.getNoti_time());

        // Modify some values using setters
        notiDto.setId(2L);
        notiDto.setDescription("Updated description");
        notiDto.setNoti_time(1636700000000L); // Assuming a different timestamp in milliseconds

        // Verify that the modified values are reflected
        assertEquals(2L, notiDto.getId());
        assertEquals("Updated description", notiDto.getDescription());
        assertEquals(1636700000000L, notiDto.getNoti_time());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
