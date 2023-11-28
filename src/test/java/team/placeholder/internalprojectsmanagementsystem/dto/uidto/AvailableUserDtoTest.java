package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import static org.junit.jupiter.api.Assertions.*;

public class AvailableUserDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a sample UserDto
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("john_doe");
        userDto.setEmail("john.doe@example.com");

        // Create an AvailableUserDto
        AvailableUserDto availableUserDto = new AvailableUserDto();
        availableUserDto.setId(123L);
        availableUserDto.setUser(userDto);
        availableUserDto.setAvaliable(true);

        // Verify that the getters return the expected values
        assertEquals(123L, availableUserDto.getId());
        assertEquals(userDto, availableUserDto.getUser());


        // Modify some values using setters
        UserDto newUserDto = new UserDto();
        newUserDto.setId(456L);
        newUserDto.setName("jane_doe");
        newUserDto.setEmail("jane.doe@example.com");

        availableUserDto.setId(789L);
        availableUserDto.setUser(newUserDto);
        availableUserDto.setAvaliable(false);

        // Verify that the modified values are reflected
        assertEquals(789L, availableUserDto.getId());
        assertEquals(newUserDto, availableUserDto.getUser());

    }

    @Test
    public void testNoArgsConstructor() {
        // Create an AvailableUserDto using the no-args constructor
        AvailableUserDto availableUserDto = new AvailableUserDto();

        // Verify that the default values are set
        assertEquals(0L, availableUserDto.getId());
        assertNull(availableUserDto.getUser());

    }

    @Test
    public void testAllArgsConstructor() {
        // Create a sample UserDto
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("john_doe");
        userDto.setEmail("john.doe@example.com");

        // Create an AvailableUserDto using the all-args constructor
        AvailableUserDto availableUserDto = new AvailableUserDto(123L, userDto, true);

        // Verify that the values are set correctly
        assertEquals(123L, availableUserDto.getId());
        assertEquals(userDto, availableUserDto.getUser());
    }
    }