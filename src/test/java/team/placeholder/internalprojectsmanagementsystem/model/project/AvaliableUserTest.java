package team.placeholder.internalprojectsmanagementsystem.model.project;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvaliableUserTest {
    @Mock
    private User user;
    @Mock
    private AvailableUser availableUser;
    @Test
    public void testAvailableUserConstruction() {
        // Arrange
        long availableUserId = 1;
        user = new User(); // Assuming you have a User class
        boolean available = true;

        // Act
        availableUser = new AvailableUser();
        availableUser.setId(availableUserId);
        availableUser.setUser(user);
        availableUser.setAvaliable(available);

        // Assert
        assertEquals(availableUserId, availableUser.getId());
        assertEquals(user, availableUser.getUser());
        assertTrue(availableUser.isAvaliable());
    }
}
