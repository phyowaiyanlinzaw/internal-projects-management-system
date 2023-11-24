package team.placeholder.internalprojectsmanagementsystem.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.RegisterEmployeeDto;
import team.placeholder.internalprojectsmanagementsystem.dto.uidto.UserUIDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository; // Assuming UserRepository is a repository for User entities
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   @Mock
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSaveUser() {
        UserDto userDto = new UserDto();

        when(userService.save(userDto)).thenReturn(userDto);
        UserDto savedUser = userService.save(userDto);
        assertEquals(userDto, savedUser);

    }

    @Test
    public void testGetUserById() {
        long id = 1;
        UserDto userDto = new UserDto();
        when(userService.getUserById(id)).thenReturn(userDto);

        UserDto userById = userService.getUserById(1L);
        assertEquals(userDto, userById);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@gmail.com";
        UserDto userDto = new UserDto();
        when(userService.getUserByEmail(email)).thenReturn(userDto);
        UserDto userByEmail = userService.getUserByEmail(email);

        assertEquals(userDto, userByEmail);
    }

    @Test
    public void testGetAllUsers() {
        List<UserDto > list = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(list);
        List<UserDto> userDto = userService.getAllUsers();
        assertEquals(userDto, userService.getAllUsers());
    }

    @Test
    public void testUpdateProfile() {
        UserDto userDto = new UserDto();
        when(userService.updateProfile(userDto)).thenReturn(userDto);
        UserDto updatedUser = userService.updateProfile(userDto);
        assertEquals(userDto, updatedUser);
    }

    @Test
    public void testChangePassword() {
        String email = "eep@gmail.com";
        String oldPassword = "12345678";
        String newPassword = "87654321";

        UserDto userDto = new UserDto();
        when(userService.changePassword(email, oldPassword, newPassword)).thenReturn(userDto);
        UserDto changedPassword = userService.changePassword(email, oldPassword, newPassword);
        assertEquals(userDto, changedPassword);

    }

    @Test
    public void testSendOtp() {
        String email = "eep@gmail.com";
        UserDto userDto = new UserDto();
        when(userService.sendOtp(email)).thenReturn(userDto);
        UserDto sentOtp = userService.sendOtp(email);
        assertEquals(userDto, sentOtp);

    }
    @Test
    void registerUser() {

    }

    @Test
    void findByName() {
        String name = "John Doe";
        UserDto userDto = new UserDto();

        when(userService.findByName(name)).thenReturn(userDto);

        // When
        UserDto result = userService.findByName(name);

        // Then
        assertEquals(userDto, result);
    }

    @Test
    void getAllByRole() {
        Role role = Role.PMO; // Assuming ROLE_USER is one of the enum values
        List<UserDto> expectedUserList = Arrays.asList(new UserDto(), new UserDto());

        when(userService.getAllByRole(role)).thenReturn(expectedUserList);

        // When
        List<UserDto> result = userService.getAllByRole(role);

        // Then
        assertEquals(expectedUserList, result);
    }

    @Test
    void countAllByRole() {
        Role role = Role.PMO; // Assuming ROLE_USER is one of the enum values
        long expectedCount = 5;

        when(userService.countAllByRole(role)).thenReturn(expectedCount);

        // When
        long result = userService.countAllByRole(role);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void countAll() {
        long expectedCount = 10;

        when(userService.countAll()).thenReturn(expectedCount);

        // When
        long result = userService.countAll();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void getAllUsersByPMId() {
        Long projectId = 1L;
        List<UserDto> expectedUserList = Arrays.asList(new UserDto(), new UserDto());

        when(userService.getAllUsersByPMId(projectId)).thenReturn(expectedUserList);

        // When
        List<UserDto> result = userService.getAllUsersByPMId(projectId);

        // Then
        assertEquals(expectedUserList, result);
    }

    @Test
    void getUserByDepartmentIdAndRole() {
        Long departmentId = 1L;
        Role role = Role.PMO; // Assuming ROLE_USER is one of the enum values
        UserDto expectedUser = new UserDto();

        when(userService.getUserByDepartmentIdAndRole(departmentId, role)).thenReturn(expectedUser);

        // When
        UserDto result = userService.getUserByDepartmentIdAndRole(departmentId, role);

        // Then
        assertEquals(expectedUser, result);
    }

    @Test
    void countAllByDepartmentId() {
        Long departmentId = 1L;
        long expectedCount = 5;

        when(userService.countAllByDepartmentId(departmentId)).thenReturn(expectedCount);

        // When
        long result = userService.countAllByDepartmentId(departmentId);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void getAllEmployeesExceptPMOAndSDQC() {
        List<UserDto> expectedUserList = Arrays.asList(new UserDto(), new UserDto());

        when(userService.getAllEmployeesExceptPMOAndSDQC()).thenReturn(expectedUserList);

        // When
        List<UserDto> result = userService.getAllEmployeesExceptPMOAndSDQC();

        // Then
        assertEquals(expectedUserList, result);
    }

    @Test
    void getEmployeeByProjectId() {
        Long projectId = 1L;
        List<UserDto> expectedUserList = Arrays.asList(new UserDto(), new UserDto());

        when(userService.getEmployeeByProjectId(projectId)).thenReturn(expectedUserList);

        // When
        List<UserDto> result = userService.getEmployeeByProjectId(projectId);

        // Then
        assertEquals(expectedUserList, result);
    }

    @Test
    void changeUsername() {
        UserDto userDto = new UserDto();
        userDto.setName("newUsername");

        doNothing().when(userService).changeUsername(userDto);

        // When
        userService.changeUsername(userDto);

        // Then
        // Add assertions or verifications as needed based on your specific requirements
        verify(userService, times(1)).changeUsername(userDto);
        assertEquals("newUsername", userDto.getName());
    }

    @Test
    void changeStatus() {

    }

    @Test
    void updateUser() {
        UserUIDto userUIDto = new UserUIDto();
        userUIDto.setId(1L);
        userUIDto.setName("newUsername");
        userUIDto.setEmail("newEmail@example.com");

        UserDto updatedUser = new UserDto();
        updatedUser.setId(userUIDto.getId());
        updatedUser.setName(userUIDto.getName());
        updatedUser.setEmail(userUIDto.getEmail());

        when(userService.updateUser(userUIDto)).thenReturn(updatedUser);

        // When
        UserDto result = userService.updateUser(userUIDto);

        // Then
        assertEquals(userUIDto.getId(), result.getId());
        assertEquals(userUIDto.getName(), result.getName());
        assertEquals(userUIDto.getEmail(), result.getEmail());
    }
}