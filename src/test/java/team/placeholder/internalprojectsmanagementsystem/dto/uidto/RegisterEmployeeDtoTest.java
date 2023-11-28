package team.placeholder.internalprojectsmanagementsystem.dto.uidto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterEmployeeDtoTest {

    @Test
    public void testGetterAndSetter() {
        // Create a RegisterEmployeeDto
        RegisterEmployeeDto registerEmployeeDto = new RegisterEmployeeDto();
        registerEmployeeDto.setName("John Doe");
        registerEmployeeDto.setEmail("john.doe@example.com");
        registerEmployeeDto.setPassword("password123");
        registerEmployeeDto.setRole("Developer");
        registerEmployeeDto.setProjectManagerId(1L);
        registerEmployeeDto.setDepartmentId(2L);

        // Verify that the getters return the expected values
        assertEquals("John Doe", registerEmployeeDto.getName());
        assertEquals("john.doe@example.com", registerEmployeeDto.getEmail());
        assertEquals("password123", registerEmployeeDto.getPassword());
        assertEquals("Developer", registerEmployeeDto.getRole());
        assertEquals(1L, registerEmployeeDto.getProjectManagerId());
        assertEquals(2L, registerEmployeeDto.getDepartmentId());

        // Modify some values using setters
        registerEmployeeDto.setName("Jane Doe");
        registerEmployeeDto.setEmail("jane.doe@example.com");
        registerEmployeeDto.setPassword("newpassword");
        registerEmployeeDto.setRole("QA Engineer");
        registerEmployeeDto.setProjectManagerId(3L);
        registerEmployeeDto.setDepartmentId(4L);

        // Verify that the modified values are reflected
        assertEquals("Jane Doe", registerEmployeeDto.getName());
        assertEquals("jane.doe@example.com", registerEmployeeDto.getEmail());
        assertEquals("newpassword", registerEmployeeDto.getPassword());
        assertEquals("QA Engineer", registerEmployeeDto.getRole());
        assertEquals(3L, registerEmployeeDto.getProjectManagerId());
        assertEquals(4L, registerEmployeeDto.getDepartmentId());
    }

    // Add more tests for edge cases or specific scenarios

    // ... rest of the tests
}
