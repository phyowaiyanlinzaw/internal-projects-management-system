package team.placeholder.internalprojectsmanagementsystem.model.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @MockBean
    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
    }

    @Test
    public void testConstructor() {
        assertNotNull(department);
    }

    @Test
    public void testId() {
        long departmentId = 1;
        department.setId(departmentId);
        assertEquals(departmentId, department.getId());
    }

    @Test
    public void testName() {
        String name = "Department 1";
        department.setName(name);
        assertEquals(name, department.getName());
    }

    @Test
    public void testUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();

        // Test adding users to the department
        department.setUsers(users);
        assertEquals(users, department.getUsers());

        // Test adding individual users
        users.add(user1);
        users.add(user2);
        department.setUsers(users);
        assertTrue(department.getUsers().contains(user1));
        assertTrue(department.getUsers().contains(user2));

        // Test removing users
        users.remove(user1);
        department.setUsers(users);
        assertFalse(department.getUsers().contains(user1));
    }

}