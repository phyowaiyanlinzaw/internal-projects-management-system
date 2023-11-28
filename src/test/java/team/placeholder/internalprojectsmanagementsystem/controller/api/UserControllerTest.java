package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.AvailableUserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UserUIDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.AvailableUserRepo;
import team.placeholder.internalprojectsmanagementsystem.service.FakerService;
import team.placeholder.internalprojectsmanagementsystem.service.impl.department.DepartmentServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.AESImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @Mock
    private ModelMapper modalMapper;
    @Mock
    private DepartmentServiceImpl departmentService;

    @Mock
    private AvailableUserRepo availableEmployeeService;
    @Mock
    private DepartmentDto departmentDto;
    @Mock
    private FakerService fakerService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateFakeUser() {
        // Arrange
        int count = 5;

        // Mock behavior
        doNothing().when(fakerService).generateAndSaveFakeUsers(count);

        // Act
        ResponseEntity<String> responseEntity = userController.generateFakeUsers(count);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fake users generated successfully", responseEntity.getBody());

        // Verify that the service method was called with the correct parameter
        verify(fakerService, times(1)).generateAndSaveFakeUsers(count);
    }


    @Test
    void testGetUserInfo() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(authentication.getName()).thenReturn("test@example.com");
        when(userService.getUserByEmail(anyString())).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserInfo();

        assertEquals(userDto, response.getBody());
    }

    @Test
    void testSendEmailUserNotFound() {
        String email = "test@example.com";

        when(userService.sendOtp(email)).thenReturn(null);

        ResponseEntity<String> response = userController.sendEmail(email);

        assertEquals("User not found", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("User not found"), response);
    }

    @Test
    void testSendEmailSucess() {
        String email = "test@example.com";
        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.sendOtp(email)).thenReturn(userDto);

        ResponseEntity<String> response = userController.sendEmail(email);

        assertEquals("Email sent successfully", response.getBody());
    }

    @Test
    void testGetUserInfoWithoutAuthentication() {
        // Set Authentication to null
        SecurityContextHolder.getContext().setAuthentication(null);

        ResponseEntity<UserDto> response = userController.getUserInfo();

        assertNull(response.getBody());
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void testConfirmOtpSuccess() {
        String email = "test@example.com";
        String otp = "123456";

        when(userService.confirmOtp(email, otp)).thenReturn(true);

        ResponseEntity<String> response = userController.confirmOtp(email, otp);

        assertEquals("OTP has been confirmed.", response.getBody());
        assertEquals(ResponseEntity.ok("OTP has been confirmed."), response);
    }

    @Test
    void testConfirmOtpFailure() {
        String email = "test@example.com";
        String otp = "123456";

        when(userService.confirmOtp(email, otp)).thenReturn(false);

        ResponseEntity<String> response = userController.confirmOtp(email, otp);

        assertEquals("OTP is not confirmed", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("OTP is not confirmed"), response);
    }

    @Test
    void testResetPasswordSuccess() {
        String email = "test@example.com";
        String newPassword = "newPassword";

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.resetPassword(email, newPassword)).thenReturn(userDto);

        ResponseEntity<String> response = userController.resetPassword(email, newPassword);

        assertEquals("Password reset successfully", response.getBody());
        assertEquals(ResponseEntity.ok("Password reset successfully"), response);
    }

    @Test
    void testResetPasswordUserNotFound() {
        String email = "test@example.com";
        String newPassword = "newPassword";

        when(userService.resetPassword(email, newPassword)).thenReturn(null);

        ResponseEntity<String> response = userController.resetPassword(email, newPassword);

        assertEquals("User not found", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("User not found"), response);
    }

    @Test
    void testRegisterEmployeeSuccess() {
        RegisterEmployeeDto registerEmployeeDto = new RegisterEmployeeDto(); // You need to create a RegisterEmployeeDto object here
        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.registerUser(registerEmployeeDto)).thenReturn(userDto);

        ResponseEntity<String> response = userController.registerEmployee(registerEmployeeDto);

        assertEquals("Employee registered successfully", response.getBody());
        assertEquals(ResponseEntity.ok("Employee registered successfully"), response);
    }

    @Test
    void testRegisterEmployeeUserNotFound() {
        RegisterEmployeeDto registerEmployeeDto = new RegisterEmployeeDto(); // You need to create a RegisterEmployeeDto object here

        when(userService.registerUser(registerEmployeeDto)).thenReturn(null);

        ResponseEntity<String> response = userController.registerEmployee(registerEmployeeDto);

        assertEquals("User not found", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("User not found"), response);
    }

    @Test
    void testChangePasswordSuccess() {
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(authentication.getName()).thenReturn("test@example.com");
        when(userService.changePassword("test@example.com", oldPassword, newPassword)).thenReturn(userDto);

        ResponseEntity<String> response = userController.changePassword(oldPassword, newPassword);

        assertEquals("Password changed successfully", response.getBody());
        assertEquals(ResponseEntity.ok("Password changed successfully"), response);
    }

    @Test
    void testChangePasswordUserNotFound() {
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(authentication.getName()).thenReturn("test@example.com");
        when(userService.changePassword("test@example.com", oldPassword, newPassword)).thenReturn(null);

        ResponseEntity<String> response = userController.changePassword(oldPassword, newPassword);

        assertEquals("User not found", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("User not found"), response);
    }

    @Test
    void testChangePasswordAuthenticationNull() {
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        // Set Authentication to null
        SecurityContextHolder.getContext().setAuthentication(null);

        ResponseEntity<String> response = userController.changePassword(oldPassword, newPassword);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void testUpdateDepartmentSuccess() {
        Long departmentId = 1L;

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(authentication.getName()).thenReturn("test@example.com");
        when(userService.getUserByEmail("test@example.com")).thenReturn(userDto);
        when(departmentService.getDepartmentById(departmentId)).thenReturn(departmentDto);

        ResponseEntity<String> response = userController.updateDepartment(departmentId);

        assertEquals("Department updated successfully", response.getBody());
        assertEquals(ResponseEntity.ok("Department updated successfully"), response);
    }

    @Test
    void testUpdateDepartmentUserNotFound() {
        Long departmentId = 1L;

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(authentication.getName()).thenReturn("test@example.com");
        when(userService.getUserByEmail("test@example.com")).thenReturn(null);

        ResponseEntity<String> response = userController.updateDepartment(departmentId);

        assertEquals("User not found", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("User not found"), response);
    }

    @Test
    void testUpdateDepartmentAuthenticationNull() {
        Long departmentId = 1L;

        // Set Authentication to null
        SecurityContextHolder.getContext().setAuthentication(null);

        ResponseEntity<String> response = userController.updateDepartment(departmentId);

        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void testUpdateEmployeeStatusSuccess() {
        Long userId = 1L;
        boolean status = true;

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.changeStatus(userId, status)).thenReturn(userDto);

        ResponseEntity<String> response = userController.updateEmployeeStatus(userId, status);

        assertEquals("Employee status updated successfully", response.getBody());
        assertEquals(ResponseEntity.ok("Employee status updated successfully"), response);
    }

    @Test
    void testUpdateEmployeeStatusUserNotFound() {
        Long userId = 1L;
        boolean status = true;

        when(userService.changeStatus(userId, status)).thenReturn(null);

        ResponseEntity<String> response = userController.updateEmployeeStatus(userId, status);

        assertEquals("User not found", response.getBody());
        assertEquals(ResponseEntity.badRequest().body("User not found"), response);
    }

    @Test
    void testGetUserByIdSuccess() {
        Long userId = 1L;

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.getUserById(userId)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(userId);

        assertEquals(userDto, response.getBody());
        assertEquals(ResponseEntity.ok(userDto), response);
    }

    @Test
    void testGetUserByIdUserNotFound() {
        Long userId = 1L;

        when(userService.getUserById(userId)).thenReturn(null);

        ResponseEntity<UserDto> response = userController.getUserById(userId);

        assertEquals(null, response.getBody());
        assertEquals(ResponseEntity.badRequest().body(null), response);
    }

    @Test
    void testGetUserByEmailSuccess() {
        String email = "test@example.com";

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.getUserByEmail(email)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserByEmail(email);

        assertEquals(userDto, response.getBody());
        assertEquals(ResponseEntity.ok(userDto), response);
    }

    @Test
    void testGetUserByEmailUserNotFound() {
        String email = "test@example.com";

        when(userService.getUserByEmail(email)).thenReturn(null);

        ResponseEntity<UserDto> response = userController.getUserByEmail(email);

        assertEquals(null, response.getBody());
        assertEquals(ResponseEntity.badRequest().body(null), response);
    }

    @Test
    void testGetAllUsersSuccess() {
        UserDto user1 = new UserDto(); // You need to create UserDto objects for testing
        UserDto user2 = new UserDto();
        List<UserDto> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(userList, response.getBody());
        assertEquals(ResponseEntity.ok(userList), response);
    }

    @Test
    void testGetAllUsersEmptyList() {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(Collections.emptyList(), response.getBody());
        assertEquals(ResponseEntity.ok(Collections.emptyList()), response);
    }

    @Test
    void testGetAllEmployeesExceptPMOAndSDQCSuccess() {
        UserDto employee1 = new UserDto(); // You need to create UserDto objects for testing
        UserDto employee2 = new UserDto();
        List<UserDto> employeeList = Arrays.asList(employee1, employee2);

        when(userService.getAllEmployeesExceptPMOAndSDQC()).thenReturn(employeeList);

        ResponseEntity<List<UserDto>> response = userController.getAllEmployeesExceptPMOAndSDQC();

        assertEquals(employeeList, response.getBody());
        assertEquals(ResponseEntity.ok(employeeList), response);
    }

    @Test
    void testGetAllUsersByRoleSuccess() {
        Role role = Role.EMPLOYEE;

        UserDto user1 = new UserDto(); // You need to create UserDto objects for testing
        UserDto user2 = new UserDto();
        List<UserDto> userList = Arrays.asList(user1, user2);

        when(userService.getAllByRole(role)).thenReturn(userList);

        ResponseEntity<List<UserDto>> response = userController.getAllUsersByRole(role);

        assertEquals(userList, response.getBody());
        assertEquals(ResponseEntity.ok(userList), response);
    }

    @Test
    void testGetAllUsersByProjectManagerIdSuccess() {
        Long projectManagerId = 1L;

        UserDto employee1 = new UserDto(); // You need to create UserDto objects for testing
        UserDto employee2 = new UserDto();
        List<UserDto> employeeList = Arrays.asList(employee1, employee2);

        when(userService.getAllUsersByPMId(projectManagerId)).thenReturn(employeeList);

        ResponseEntity<List<UserDto>> response = userController.getAllUsersByProjectManagerId(projectManagerId);

        assertEquals(employeeList, response.getBody());
        assertEquals(ResponseEntity.ok(employeeList), response);
    }

    @Test
    void testGetAllUsersByProjectIdSuccess() {
        Long projectId = 1L;

        UserDto employee1 = new UserDto(); // You need to create UserDto objects for testing
        UserDto employee2 = new UserDto();
        List<UserDto> employeeList = Arrays.asList(employee1, employee2);

        when(userService.getEmployeeByProjectId(projectId)).thenReturn(employeeList);

        ResponseEntity<List<UserDto>> response = userController.getAllUsersByProjectId(projectId);

        assertEquals(employeeList, response.getBody());
        assertEquals(ResponseEntity.ok(employeeList), response);
    }

    @Test
    void testCountAllByRoleSuccess() {
        Role role = Role.EMPLOYEE;

        when(userService.countAllByRole(role)).thenReturn(5L);

        ResponseEntity<Long> response = userController.countAllByRole(role);

        assertEquals(5L, response.getBody());
        assertEquals(ResponseEntity.ok(5L), response);
    }

    @Test
    void testCountAllSuccess() {
        when(userService.countAll()).thenReturn(10L);

        ResponseEntity<Long> response = userController.countAll();

        assertEquals(10L, response.getBody());
        assertEquals(ResponseEntity.ok(10L), response);
    }

    @Test
    void testGetUserByDepartmentIdAndRoleSuccess() {
        Long departmentId = 1L;
        Role role = Role.FOC;

        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        when(userService.getUserByDepartmentIdAndRole(departmentId, role)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserByDepartmentIdAndRole(role, departmentId);

        assertEquals(userDto, response.getBody());
        assertEquals(ResponseEntity.ok(userDto), response);
    }

    @Test
    void testCountAllByDepartmentIdSuccess() {
        Long departmentId = 1L;

        when(userService.countAllByDepartmentId(departmentId)).thenReturn(3L);

        ResponseEntity<Long> response = userController.countAllByDepartmentId(departmentId);

        assertEquals(3L, response.getBody());
        assertEquals(ResponseEntity.ok(3L), response);
    }

    @Test
    void testChangeUsernameSuccess() {
        UserDto userDto = new UserDto(); // You need to create a UserDto object here

        ResponseEntity<String> response = userController.changeUsername(userDto);

        verify(userService, times(1)).changeUsername(userDto);
        assertEquals("Username changed successfully", response.getBody());
        assertEquals(ResponseEntity.ok("Username changed successfully"), response);
    }


        @Test
        void getAllAvailableUsersByPMId() throws Exception {
            // Mocking data for the test
            Long pmId = 1L;

            // Mock the behavior of userService.getAllUsersByPMId
            List<UserDto> mockUsers = new ArrayList<>();
            // Add some mock users to the list...

            when(userService.getAllUsersByPMId(pmId)).thenReturn(mockUsers);

            // Mock the behavior of availableEmployeeService.findByUserIdIn
            List<Long> userIdList = mockUsers.stream().map(UserDto::getId).toList();
            List<AvailableUser> mockAvailableUsers = new ArrayList<>();
            // Add some mock available users to the list...

            when(availableEmployeeService.findByUserIdIn(userIdList)).thenReturn(mockAvailableUsers);

            // Act
            ResponseEntity<List<AvailableUserDto>> result = userController.getAllAvailableUsersByPMId(pmId);

            // Verify
            verify(userService).getAllUsersByPMId(pmId);

        }
    @Test
    void testUpdateUser() {
        // Arrange
        long userId = 1L;
        UserUIDto userUIDto = new UserUIDto(); // Create a UserUIDto with test data

        // Mock the behavior of userService.updateUser
        UserDto mockUpdatedUser = new UserDto(); // Create a mock UserDto with expected data
        when(userService.updateUser(userUIDto)).thenReturn(mockUpdatedUser);

        // Act
        ResponseEntity<UserDto> result = userController.updateUser(userId, userUIDto);

        // Assert
        verify(userService).updateUser(userUIDto);

        if (mockUpdatedUser != null) {
            assertEquals(200, result.getStatusCodeValue()); // OK status code
            assertNotNull(result.getBody()); // Make sure the response body is not null
            // Add more assertions based on your specific requirements, e.g., comparing properties of the returned user.
        } else {
            assertEquals(400, result.getStatusCodeValue()); // Bad Request status code
            assertEquals(null, result.getBody()); // Make sure the response body is null
        }
    }
    @Test
    void testGetUserById() {
        // Arrange
        long userId = 1L;

        // Mock the behavior of userService.getUserById
        UserDto mockUser = new UserDto(); // Create a mock UserDto with test data
        when(userService.getUserById(userId)).thenReturn(mockUser);

        // Act
        ResponseEntity<UserDto> result = userController.getUserById(userId);

        // Assert
        verify(userService).getUserById(userId);

        if (mockUser != null) {
            assertEquals(200, result.getStatusCodeValue()); // OK status code
            assertNotNull(result.getBody()); // Make sure the response body is not null
            assertEquals(mockUser, result.getBody()); // Compare the returned user with the mock user
        } else {
            assertEquals(404, result.getStatusCodeValue()); // Not Found status code
            assertTrue(result.getBody() == null); // Make sure the response body is null
        }
    }


    @Test
    void testGetUsers() {
        // Arrange
        List<UserDto> mockUsers = new ArrayList<>(); // Create a list of mock UserDto with test data

        // Mock the behavior of userService.getAllUsers
        when(userService.getAllUsers()).thenReturn(mockUsers);

        // Act
        List<UserDto> result = userController.getUsers();

        // Assert
        verify(userService).getAllUsers();
        assertEquals(mockUsers.size(), result.size()); // Compare the size of the returned list with the mock list
        // You may want to add more specific assertions depending on the behavior you expect.
    }

}





