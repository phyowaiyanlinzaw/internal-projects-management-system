package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import org.springframework.dao.DataIntegrityViolationException;
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
import team.placeholder.internalprojectsmanagementsystem.service.user.UserService;
import team.placeholder.internalprojectsmanagementsystem.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role.PMO;
import static team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role.SDQC;



class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserService userService1;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;



    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }

    // Existing test cases...

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123456");
        user.setDepartment(new Department());
        user.setRole(SDQC);
        userRepository.save(user);
        verify(userRepository, times(1)).save(user);

    }


    @Test
    public void testGetAllUsers() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setName("John");
        user1.setEmail("john@gmail.com");
        user1.setPassword("123456");
        user1.setRole(SDQC);
        user1.setDepartment(new Department());

        User user2 = new User();
        user2.setName("Jane");
        user2.setEmail("jane@gmail.com");
        user2.setPassword("123456");
        user2.setRole(PMO);
        user2.setDepartment(new Department());

        list.add(user1);
        list.add(user2);

        when(userRepository.findAll()).thenReturn(list);
        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    public void testUserById() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123456");
        user.setDepartment(new Department());
        user.setRole(SDQC);
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(user);
        User user1 = userRepository.findById(1L);
        assertEquals("John", user1.getName());
        verify(userRepository, times(1)).findById(1L);

    }

    @Test
    public void testUserByEmail() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123456");
        user.setDepartment(new Department());
        user.setRole(SDQC);
        user.setId(1L);
        when(userRepository.findByEmail("john@gmail.com")).thenReturn(user);
        User user1 = userRepository.findByEmail("john@gmail.com");
        assertEquals("John", user1.getName());
        verify(userRepository, times(1)).findByEmail("john@gmail.com");

    }

    @Test
    public void testUpdateProfile() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123456");
        user.setDepartment(new Department());
        user.setRole(SDQC);
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(user);
        User user1 = userRepository.findById(1L);
        user1.setName("Jane");
        userRepository.save(user1);
        assertEquals("Jane", user1.getName());
        verify(userRepository, times(1)).save(user1);

    }




    @Test
    public void testResetPassword() {
        String password = "12345678";
        assertEquals(password.length(), PasswordGenerator.generatePassword(8).length());
    }



    @Test
    public void testChangeUsername() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123456");
        user.setDepartment(new Department());
        user.setRole(SDQC);
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(user);
        User user1 = userRepository.findById(1L);
        user1.setName("Jane");
        userRepository.save(user1);
        assertEquals("Jane", user1.getName());
        verify(userRepository, times(1)).save(user1);

    }

    @Test
    public void testChangePassword_Success() {
        // Create a User object
        User user = new User();
        user.setEmail("john@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("oldPassword"));

        // Mock the behavior of the dependencies
        when(userRepository.findByEmail("john@gmail.com")).thenReturn(user);
        when(passwordEncoder.matches("oldPassword", user.getPassword())).thenReturn(true);
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);

        // Call the method to be tested
        UserDto updatedUserDto = userService.changePassword("john@gmail.com", "oldPassword", "newPassword");

        // Verify the behavior
        verify(userRepository, times(1)).save(user);
        assertTrue(passwordEncoder.matches("newPassword", user.getPassword()));
        assertNotNull(updatedUserDto);
        assertEquals(user.getEmail(), updatedUserDto.getEmail());
    }

    @Test
    public void testChangePassword_UserNotFound() {
        // Mock the behavior of the UserRepository
        when(userRepository.findByEmail("john@gmail.com")).thenReturn(null);

        // Call the method to be tested
        UserDto updatedUserDto = userService.changePassword("john@gmail.com", "oldPassword", "newPassword");

        // Verify the behavior
        verify(userRepository, never()).save(any(User.class));
        assertNull(updatedUserDto);
    }

    @Test
    public void testChangePassword_IncorrectOldPassword() {
        // Create a User object
        User user = new User();
        user.setEmail("john@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("oldPassword"));

        // Mock the behavior of the dependencies
        when(userRepository.findByEmail("john@gmail.com")).thenReturn(user);
        when(passwordEncoder.matches("wrongPassword", user.getPassword())).thenReturn(false);

        // Call the method to be tested
        UserDto updatedUserDto = userService.changePassword("john@gmail.com", "wrongPassword", "newPassword");

        // Verify the behavior
        verify(userRepository, never()).save(any(User.class));
        assertNull(updatedUserDto);
    }


}