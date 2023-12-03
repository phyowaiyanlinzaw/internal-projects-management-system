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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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


}