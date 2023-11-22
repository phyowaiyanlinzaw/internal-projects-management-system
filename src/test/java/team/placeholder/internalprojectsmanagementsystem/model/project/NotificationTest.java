package team.placeholder.internalprojectsmanagementsystem.model.project;


import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NotificationTest {
    @Test
    public void testNotificationConstruction() {
        // Arrange
        long id = 1;
        String description = "Test Notification";
        long notificationTime = System.currentTimeMillis();
        User user = new User(); // Assuming you have a User class

        // Act
        Notification notification = new Notification();
        notification.setId(id);
        notification.setDescription(description);
        notification.setNoti_time(notificationTime);
        notification.setUser(user);

        // Assert
        assertEquals(id, notification.getId());
        assertEquals(description, notification.getDescription());
        assertEquals(notificationTime, notification.getNoti_time());
        assertEquals(user, notification.getUser());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        Notification notification1 = new Notification();
        notification1.setId(1);

        Notification notification2 = new Notification();
        notification2.setId(1);

        Notification notification3 = new Notification();
        notification3.setId(2);

        // Act & Assert
        assertEquals(notification1, notification2);
        assertEquals(notification1.hashCode(), notification2.hashCode());

        assertNotEquals(notification1, notification3);
        assertNotEquals(notification1.hashCode(), notification3.hashCode());
    }

}


