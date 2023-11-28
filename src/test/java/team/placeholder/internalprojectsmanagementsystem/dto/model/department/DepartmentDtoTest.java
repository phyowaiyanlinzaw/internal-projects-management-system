package team.placeholder.internalprojectsmanagementsystem.dto.model.department;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentDtoTest {

    @Test
    public void testDepartmentDto() {
        // Create a DepartmentDto object with some mock data
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Human Resources");

        // Test the getter methods
        assertEquals(1L, departmentDto.getId());
        assertEquals("Human Resources", departmentDto.getName());
    }
}

