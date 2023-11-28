package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

import static org.junit.jupiter.api.Assertions.*;

public class UserUIDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a UserUIDto
        UserUIDto userUIDto = new UserUIDto();
        userUIDto.setId(1L);
        userUIDto.setName("John Doe");
        userUIDto.setEmail("john.doe@example.com");
        userUIDto.setPassword("password123");
        userUIDto.setRole(Role.FOC);
        userUIDto.setEnabled(true);
        userUIDto.setDepartmentId(2L);
        userUIDto.setProjectManagerId(3L);
        userUIDto.setProjectsByProjectManagerIds(new long[]{4L, 5L, 6L});

        // Verify that the getters return the expected values
        assertEquals(1L, userUIDto.getId());
        assertEquals("John Doe", userUIDto.getName());
        assertEquals("john.doe@example.com", userUIDto.getEmail());
        assertEquals("password123", userUIDto.getPassword());
        assertEquals(Role.FOC, userUIDto.getRole());
        assertTrue(userUIDto.isEnabled());
        assertEquals(2L, userUIDto.getDepartmentId());
        assertEquals(3L, userUIDto.getProjectManagerId());
        assertArrayEquals(new long[]{4L, 5L, 6L}, userUIDto.getProjectsByProjectManagerIds());

        // Modify some values using setters
        userUIDto.setId(7L);
        userUIDto.setName("Jane Doe");
        userUIDto.setEmail("jane.doe@example.com");
        userUIDto.setPassword("newpassword");
        userUIDto.setRole(Role.PMO);
        userUIDto.setEnabled(false);
        userUIDto.setDepartmentId(8L);
        userUIDto.setProjectManagerId(9L);
        userUIDto.setProjectsByProjectManagerIds(new long[]{10L, 11L, 12L});

        // Verify that the modified values are reflected
        assertEquals(7L, userUIDto.getId());
        assertEquals("Jane Doe", userUIDto.getName());
        assertEquals("jane.doe@example.com", userUIDto.getEmail());
        assertEquals("newpassword", userUIDto.getPassword());
        assertEquals(Role.PMO, userUIDto.getRole());
        assertFalse(userUIDto.isEnabled());
        assertEquals(8L, userUIDto.getDepartmentId());
        assertEquals(9L, userUIDto.getProjectManagerId());
        assertArrayEquals(new long[]{10L, 11L, 12L}, userUIDto.getProjectsByProjectManagerIds());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
