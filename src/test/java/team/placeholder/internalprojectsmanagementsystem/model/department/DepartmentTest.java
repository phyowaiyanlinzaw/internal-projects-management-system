package team.placeholder.internalprojectsmanagementsystem.model.department;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    @MockBean
    private Department department;
    @Test
    public void testDepartmentConstructor() {
        Department department = new Department();
        assertNotNull(department);
    }
    @Test
    public void testDepartmentGettersAndSetters() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 1");
        assertEquals(1L, department.getId());
        assertEquals("Department 1", department.getName());
    }
    @Test
    public void testDepartmentUsers() {
        Department department = new Department();
        User user1 = new User();
        User user2 = new User();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        department.setUsers(users);
        assertEquals(users, department.getUsers());
    }
    @Test
    public void testEquals() {
        // Create two department instances with the same ID
        Department department1 = new Department();
        department1.setId(1L);
        Department department2 = new Department();
        department2.setId(1L);
        // Verify that they are equal
        assertTrue(department1.equals(department2));
        assertTrue(department2.equals(department1));
    }
    @Test
    public void testNotEquals() {
        // Create two department instances with different IDs
        Department department1 = new Department();
        department1.setId(1L);
        Department department2 = new Department();
        department2.setId(2L);
        // Verify that they are not equal
        assertFalse(department1.equals(department2));
        assertFalse(department2.equals(department1));
    }

    @Test
    public void testHashCode() {
        // Create a department instance with a specific ID
        Department department = new Department();
        department.setId(1L);
        // Create another department instance with the same ID
        Department sameIdDepartment = new Department();
        sameIdDepartment.setId(1L);
        // Verify that both instances have the same hash code
        assertEquals(department.hashCode(), sameIdDepartment.hashCode());
    }
    @Test
    void testOrElse() {
        Department result = Department.orElse(department);
        // Assert
        assertNull(result); // Assuming you expect null as the result
    }
    @Test
    public void testConstructor() {
        // Arrange
        long expectedId = 1L; // Replace with the actual expected ID
        // Act
        DepartmentDto departmentDto = new DepartmentDto(expectedId);
        // Assert
        assertEquals(expectedId, departmentDto.getId());
    }
}
