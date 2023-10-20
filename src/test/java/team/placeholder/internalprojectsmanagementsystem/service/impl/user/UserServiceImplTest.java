package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role.PMO;
import static team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role.SDQC;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private MailSender mailSender;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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
    public void testChangePassword() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123456");
        user.setDepartment(new Department());
        user.setRole(SDQC);
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(user);
        User user1 = userRepository.findById(1L);
        user1.setPassword("1234567");
        userRepository.save(user1);
        assertEquals("1234567", user1.getPassword());
        verify(userRepository, times(1)).save(user1);

    }



}