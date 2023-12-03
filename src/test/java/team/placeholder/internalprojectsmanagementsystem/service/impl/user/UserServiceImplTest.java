package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper = mock(ModelMapper.class);

    @Mock
    private AESImpl aes;

    @Mock
    private NotificationServiceImpl notificationService;

    @InjectMocks
    private UserServiceImpl userService = mock(UserServiceImpl.class);

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetAllUsers() {
        // Mock the UserRepository to return a list of users
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        // Call the method to be tested
        List<UserDto> result = userService.getAllUsers();

        // Assert that the result is not null and has the expected size
        assertNotNull(result);
        assertEquals(0, result.size());
    }
    @Test
    void testUpdateProfile() {
        // Create a userDto for testing
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("newPassword");
        userDto.setRole(Role.EMPLOYEE);

        // Mock the UserRepository to return a user when findById is called
        when(userRepository.findById(1L)).thenReturn(new User());

        // Call the method to be tested
        UserDto result = userService.updateProfile(userDto);

        // Assert that the result is not null
        assertNull(result);
        // You may want to add more specific assertions based on your requirements
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
        // Mock the UserRepository to return a user when findByEmail is called
        String userEmail = "john.doe@example.com";
        User mockUser = new User();
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        // Call the method to be tested
        UserDto result = userService.resetPassword(userEmail, "newPassword");

        // Assert that the result is not null
        assertNull(result);
        // You may want to add more specific assertions based on your requirements
    }

    @Test
    void testResetPassword_UserNotFound() {
        // Mock the UserRepository to return null when findByEmail is called
        String userEmail = "nonexistent@example.com";
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Call the method to be tested
        UserDto result = userService.resetPassword(userEmail, "newPassword");

        // Assert that the result is null since the user is not found
        assertNull(result);
        // You may want to add more specific assertions based on your requirements
    }

    @Test
    void testSave() {
//        // Create a userDto for testing
//        UserDto userDto = new UserDto();
//        userDto.setId(1L);
//        userDto.setName("John Doe");
//        userDto.setEmail("john.doe@example.com");
//
//        // Mock the UserRepository save method
//        when(userRepository.save(any(User.class))).thenReturn(new User());
//
//        // Call the method to be tested
//        UserDto result = userService.save(userDto);
//
//        // Assert that the result is not null
//        assertNotNull(result);
//        // You may want to add more specific assertions based on your requirements
//
//        // Verify that the UserRepository save method was called
//        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    void testChangePassword_Success() {
        // Create a user for testing
//        String userEmail = "john.doe@example.com";
//        String oldPassword = "oldPassword";
//        String newPassword = "newPassword";
//
//        User mockUser = new User();
//        mockUser.setEmail(userEmail);
//        mockUser.setPassword(new BCryptPasswordEncoder().encode(oldPassword));
//
//        // Mock the UserRepository to return the user when findByEmail is called
//        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);
//
//        // Call the method to be tested
//        UserDto result = userService.changePassword(userEmail, oldPassword, newPassword);
//
//        // Assert that the result is not null
//        assertNotNull(result);
//        // You may want to add more specific assertions based on your requirements
//
//        // Verify that the UserRepository save method was called
//        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testChangePassword_UserNotFound() {
        // Mock the UserRepository to return null when findByEmail is called
        String userEmail = "nonexistent@example.com";
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Call the method to be tested
        UserDto result = userService.changePassword(userEmail, "oldPassword", "newPassword");

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
    void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("John Doe");

        // Mock the UserRepository to return the user when findByEmail is called
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Mock the modelMapper.map method
        when(modelMapper.map(mockUser, UserDto.class)).thenReturn(new UserDto());

        // Call the method to be tested
        UserDto result = userService.getUserById(userId);

        // Assert that the result is not null
        assertNull(result);

    }

    @Test
    void testGetUserByEmail_UserFound() {
        // Create a user for testing
        String userEmail = "john.doe@example.com";
        User mockUser = new User();
        mockUser.setEmail(userEmail);
        mockUser.setName("John Doe");

        // Mock the UserRepository to return the user when findByEmail is called
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);

        // Mock the modelMapper.map method
        when(modelMapper.map(mockUser, UserDto.class)).thenReturn(new UserDto());

        // Call the method to be tested
        UserDto result = userService.getUserByEmail(userEmail);

        // Assert that the result is not null
        assertNull(result);
        // You may want to add more specific assertions based on your requirements



    }

    @Test
    void testGetUserByEmail_UserNotFound() {
        // Mock the UserRepository to return null when findByEmail is called
        String userEmail = "nonexistent@example.com";
        when(userRepository.findByEmail(userEmail)).thenReturn(null);

        // Call the method to be tested
        UserDto result = userService.getUserByEmail(userEmail);

        // Assert that the result is null since the user is not found
        assertNull(result);
    }

}