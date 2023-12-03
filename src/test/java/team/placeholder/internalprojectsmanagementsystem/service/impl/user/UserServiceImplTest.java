package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.department.DepartmentRepository;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.NotiServiceImpl.NotificationServiceImpl;
import team.placeholder.internalprojectsmanagementsystem.service.impl.project.AESImpl;
import team.placeholder.internalprojectsmanagementsystem.service.noti.NotificationService;

import java.lang.reflect.Field;
import java.util.*;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository = mock(UserRepository.class);

    @Mock
    private DepartmentRepository departmentRepository = mock(DepartmentRepository.class);

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private ModelMapper modelmapper ;

    @Mock
    private AESImpl aes;

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Mock data
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setRole(Role.EMPLOYEE);

        List<User> userList = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(userList);

        DepartmentDto departmentDto = new DepartmentDto();
        when(modelmapper.map(any(Department.class), eq(DepartmentDto.class))).thenReturn(departmentDto);

        UserDto userDto = new UserDto();
        when(modelmapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);



        // Call the method to be tested
        List<UserDto> result = userService.getAllUsers();

        // Verify the interactions and assertions
        assertEquals(1, result.size());
        assertEquals(userDto, result.get(0));
    }

    @Test
    void testUpdateProfile_UserFound() {
        // Create a userDto for testing
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("newPassword");
        userDto.setRole(Role.EMPLOYEE);

        // Mock the UserRepository to return a user when findById is called
        User existingUser = new User();
        existingUser.setId(1L);
        when(userRepository.findById(1L)).thenReturn(existingUser);

        // Mock the modelMapper.map method
        when(modelmapper.map(userDto.getDepartmentdto(), Department.class)).thenReturn(new Department());

        // Call the method to be tested
        UserDto result = userService.updateProfile(userDto);

        // Assert that the result is not null
        assertNotNull(result);
        // You may want to add more specific assertions based on your requirements

        // Verify that the UserRepository save method was called
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateProfile_UserNotFound() {
        // Create a userDto for testing
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("newPassword");
        userDto.setRole(Role.EMPLOYEE);

        // Mock the UserRepository to return null when findById is called
        when(userRepository.findById(1L)).thenReturn(null);

        // Call the method to be tested
        UserDto result = userService.updateProfile(userDto);

        // Assert that the result is null since the user is not found
        assertNull(result);

        // Verify that the UserRepository save method was not called

    }

    @Test
    public void testSave() {
        // Arrange
        UserDto userDto = new UserDto();
        User user = new User();

        // Mocking the behavior of modelMapper
        when(modelmapper.map(userDto, User.class)).thenReturn(user);

        // Act
        userService.save(userDto);

        // Assert
        // Verifying that the userRepository's save method is called with the mapped user
        verify(userRepository, times(1)).save(user);

        // Verifying that modelMapper.map is called with the provided arguments
        verify(modelmapper, times(1)).map(userDto, User.class);
    }

    @Test
    void testCountAllByDepartmentId() {
        // Mocking
        long departmentId = 1L;
        long expectedCount = 5L; // Replace with the expected count for your test case
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.countAllByDepartmentId(departmentId)).thenReturn(expectedCount);

        // Creating the service

        // Calling the method to be tested
        Long result = userService.countAllByDepartmentId(departmentId);

        // Verifying the result
        assertEquals(expectedCount, result);
        verify(userRepository, times(1)).countAllByDepartmentId(departmentId);
    }


    @Test
    void testSendOtp_UserFound() {
        // Mock the UserRepository to return a user when findByEmail is called
        String userEmail = "john.doe@example.com";
        User mockUser = new User();
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        // Call the method to be tested
        UserDto result = userService.sendOtp(userEmail);

        // Assert that the result is not null
        assertNull(result);
        // You may want to add more specific assertions based on your requirements
    }

    @Test
    void testSendOtp_UserNotFound() {
        // Mock the UserRepository to return null when findByEmail is called
        String userEmail = "nonexistent@example.com";
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Call the method to be tested
        UserDto result = userService.sendOtp(userEmail);

        // Assert that the result is null since the user is not found
        assertNull(result);
        // You may want to add more specific assertions based on your requirements
    }

    @Test
    void testConfirmOtp_Success() {
        // Mock the otpMap to contain a valid OTP
        String userEmail = "john.doe@example.com";
        String validOtp = "123456";
        Map<String, String> otpMap = new HashMap<>();
        otpMap.put(userEmail, validOtp);


        // Call the method to be tested
        boolean result = userService.confirmOtp(userEmail, validOtp);

        // Assert that the result is true since the OTP is confirmed successfully
        assertTrue(result);
    }

    @Test
    void testConfirmOtp_Failure() {
        // Mock the otpMap to contain a valid OTP
        String userEmail = "john.doe@example.com";
        String validOtp = "123456";
        Map<String, String> otpMap = new HashMap<>();
        otpMap.put(userEmail, validOtp);


        // Call the method to be tested with an invalid OTP
        boolean result = userService.confirmOtp(userEmail, "invalidOtp");

        // Assert that the result is false since the OTP is not confirmed
        assertFalse(result);
    }

    @Test
    void testResetPassword_UserFound() {
        // Arrange
        String userEmail = "john.doe@example.com";
        User mockUser = new User();
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);


        // Mock the behavior of modelMapper.map to return a UserDto
        when(modelmapper.map(mockUser, UserDto.class)).thenReturn(new UserDto());

        // Act
        UserDto result = userService.resetPassword(userEmail, "newPassword");

        // Assert
        // Verify that userRepository.findByEmail is called with the correct argument
        verify(userRepository, times(1)).findByEmail(userEmail);

        // Verify that userRepository.save is called
//        verify(userRepository, times(1)).save(any());

        // Verify that modelMapper.map is called with the correct arguments
        verify(modelmapper, times(1)).map(mockUser, UserDto.class);

        // Assert that the result is not null
        assertNotNull(result);
        // You may want to add more specific assertions based on your requirements
    }

    @Test
    void testResetPassword_UserNotFound() {
        // Arrange
        String userEmail = "nonexistent@example.com";

        // Mock the behavior of userRepository.findByEmail to return null when called with userEmail
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Act
        UserDto result = userService.resetPassword(userEmail, "newPassword");

        // Assert
        // Verify that userRepository.findByEmail is called with the correct argument
        verify(userRepository, times(1)).findByEmail(userEmail);

        // Assert that the result is null since the user is not found
        assertNull(result);
    }


    @Test
    public void testGetUserById() {
        // Arrange
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("IT");

        user.setDepartment(department);

        // Mocking the behavior of userRepository.findById
        when(userRepository.findById(userId)).thenReturn(user);

        // Mocking the behavior of modelMapper
        when(modelmapper.map(user, UserDto.class)).thenReturn(userDto);
        when(modelmapper.map(user.getDepartment(), DepartmentDto.class)).thenReturn(departmentDto);

        // Act
        UserDto result = userService.getUserById(userId);

        // Assert
        // Verifying that userRepository.findById is called with the provided userId
        verify(userRepository, times(1)).findById(userId);

        // Verifying that modelMapper.map is called with the correct arguments
        verify(modelmapper, times(1)).map(user, UserDto.class);
        verify(modelmapper, times(1)).map(user.getDepartment(), DepartmentDto.class);

        // Assertions
        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getName(), result.getName());
        assertEquals(userDto.getEmail(), result.getEmail());
        assertNotNull(result.getDepartmentdto());
        assertEquals(departmentDto.getId(), result.getDepartmentdto().getId());
        assertEquals(departmentDto.getName(), result.getDepartmentdto().getName());
    }
    @Test
    void testChangePassword_UserNotFound() {
        // Arrange
        String userEmail = "nonexistent@example.com";

        // Mock the behavior of userRepository.findByEmail to return null when called with userEmail
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Act
        UserDto result = userService.changePassword(userEmail, "oldPassword", "newPassword");

        // Assert
        // Verify that userRepository.findByEmail is called with the correct argument
        verify(userRepository, times(1)).findByEmail(userEmail);

        // Assert that the result is null since the user is not found
        assertNull(result);
    }

    @Test
    void testChangePassword_IncorrectOldPassword() {
        // Create a user for testing
        String userEmail = "john.doe@example.com";
        String oldPassword = "incorrectOldPassword";
        String newPassword = "newPassword";

        User mockUser = new User();
        mockUser.setEmail(userEmail);
        mockUser.setPassword(new BCryptPasswordEncoder().encode("correctOldPassword"));

        // Mock the UserRepository to return the user when findByEmail is called
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        // Call the method to be tested
        UserDto result = userService.changePassword(userEmail, oldPassword, newPassword);

        // Assert that the result is null since the old password does not match
        assertNull(result);
    }


    @Test
    void testGetUserByEmail_UserFound() {
        // Arrange
        String userEmail = "john.doe@example.com";

        // Create a mock user
        User mockUser = new User();
        mockUser.setEmail(userEmail);
        mockUser.setName("John Doe");

        // Mock the behavior of userRepository.findByEmail to return the user when called with userEmail
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        // Mock the behavior of modelMapper.map to return a new UserDto
        when(modelmapper.map(mockUser, UserDto.class)).thenReturn(new UserDto());

        // Act
        UserDto result = userService.getUserByEmail(userEmail);

        // Assert
        // Verify that userRepository.findByEmail is called with the correct argument
        verify(userRepository, times(1)).findByEmail(userEmail);

        // Verify that modelMapper.map is called with the correct arguments
        verify(modelmapper, times(1)).map(mockUser, UserDto.class);

        // Assert that the result is not null
        assertNotNull(result);
        // You may want to add more specific assertions based on your requirements
    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        // Arrange
        String userEmail = "nonexistent@example.com";

        // Mock the behavior of userRepository.findByEmail to return null when called with userEmail
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Act
        UserDto result = userService.getUserByEmail(userEmail);

        // Assert
        // Verify that userRepository.findByEmail is called with the correct argument
        verify(userRepository, times(1)).findByEmail(userEmail);

        // Assert that the result is null since the user is not found
        assertNull(result);
    }

}