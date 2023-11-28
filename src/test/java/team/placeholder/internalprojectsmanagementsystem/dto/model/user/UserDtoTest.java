package team.placeholder.internalprojectsmanagementsystem.dto.model.user;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoTest {
    @Mock
    private UserDto userDto;

    @Test
    public void testUserDto() {
        // Create a UserDto object with some mock data
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password123");
        userDto.setRole(Role.EMPLOYEE);

        // Test the getter methods
        assertEquals(1L, userDto.getId());
        assertEquals("John Doe", userDto.getName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals("password123", userDto.getPassword());
        assertEquals(Role.EMPLOYEE, userDto.getRole());
    }
}

