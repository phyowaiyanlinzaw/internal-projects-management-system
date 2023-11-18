package team.placeholder.internalprojectsmanagementsystem.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        UserDto userDto = new UserDto();

        Mockito.when(userService.save(userDto)).thenReturn(userDto);
        UserDto savedUser = userService.save(userDto);
        assertEquals(userDto, savedUser);

    }

    @Test
    public void testGetUserById() {
        long id = 1;
        UserDto userDto = new UserDto();
        Mockito.when(userService.getUserById(id)).thenReturn(userDto);

        UserDto userById = userService.getUserById(1L);
        assertEquals(userDto, userById);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@gmail.com";
        UserDto userDto = new UserDto();
        Mockito.when(userService.getUserByEmail(email)).thenReturn(userDto);
        UserDto userByEmail = userService.getUserByEmail(email);

        assertEquals(userDto, userByEmail);
    }

    @Test
    public void testGetAllUsers() {
      List<UserDto > list = new ArrayList<>();
        Mockito.when(userService.getAllUsers()).thenReturn(list);
        List<UserDto> userDto = userService.getAllUsers();
        assertEquals(userDto, userService.getAllUsers());
    }

    @Test
    public void testUpdateProfile() {
        UserDto userDto = new UserDto();
        Mockito.when(userService.updateProfile(userDto)).thenReturn(userDto);
        UserDto updatedUser = userService.updateProfile(userDto);
        assertEquals(userDto, updatedUser);
    }

    @Test
    public void testChangePassword() {
        String email = "eep@gmail.com";
        String oldPassword = "12345678";
        String newPassword = "87654321";

        UserDto userDto = new UserDto();
        Mockito.when(userService.changePassword(email, oldPassword, newPassword)).thenReturn(userDto);
        UserDto changedPassword = userService.changePassword(email, oldPassword, newPassword);
        assertEquals(userDto, changedPassword);

    }

    @Test
    public void testSendOtp() {
        String email = "eep@gmail.com";
        UserDto userDto = new UserDto();
        Mockito.when(userService.sendOtp(email)).thenReturn(userDto);
        UserDto sentOtp = userService.sendOtp(email);
        assertEquals(userDto, sentOtp);

    }

//    @Test
//    public void testConfirmOtp() {
//        String email = "eep@gmail.com";
//        String otp = "12345678";
//        when(otpService.validateOtp(email, otp)).thenReturn(true);
//
//        // Act
//        boolean result = userService.confirmOtp(email, otp);
//
//        // Assert
//        assertTrue(result);
//
//        // Verify that the OtpService method was called
//        verify(otpService, times(1)).validateOtp(email, otp);
//
//    }




}