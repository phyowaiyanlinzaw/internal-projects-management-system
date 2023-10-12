package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @MockBean
    private  User user;

    @MockBean
    private Department department;

    @MockBean
    private Project project;



    @BeforeEach
    private void setUp() {
        user = new User();
    }

    @Test
    void testConstructor() {
        assertNotNull(user);
    }

    @Test
    public void testId(){
        long userId = 1;
        user.setId(userId);
        assertEquals(userId, user.getId());
    }

    @Test
    public void testName() {
        String name = "John Doe";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testEmail() {
        String email = "john@gmail.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testPassword() {
        String password = "john123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testRole() {
       Role role = Role.PMO;
        user.setRole(Role.PMO);
        assertEquals(Role.PMO, user.getRole());
    }

    @Test
    public void testDepartment(){
        user.setDepartment(department);
        assertEquals(department, user.getDepartment());
    }

    @Test
    public void testProjects(){

        user.getProjects().add(project);
        assertTrue(user.getProjects().contains(project));
    }







}