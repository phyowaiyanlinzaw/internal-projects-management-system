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
        UserDto userDto = new UserDto();
        String newPassword = "newPassword";
        Mockito.when(userService.changePassword(userDto, newPassword)).thenReturn(userDto);
        UserDto changedPassword = userService.changePassword(userDto, newPassword);
        assertEquals(userDto, changedPassword);
    }



}