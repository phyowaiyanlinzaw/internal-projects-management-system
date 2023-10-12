package team.placeholder.internalprojectsmanagementsystem.dto.model.project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationDtoTest {
    @Mock
    private NotificationDto notificationDto;

    @Test
    public void testNotificationDto() {
        // Create a NotificationDto object with some mock data
        notificationDto = new NotificationDto();
        notificationDto.setId(1L);
        notificationDto.setDescription("Sample Notification");

        // Test the getter methods
        assertEquals(1L, notificationDto.getId());
        assertEquals("Sample Notification", notificationDto.getDescription());
    }
}

