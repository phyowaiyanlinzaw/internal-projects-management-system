package team.placeholder.internalprojectsmanagementsystem.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import team.placeholder.internalprojectsmanagementsystem.controller.api.LoginUser;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.project.AvailableUser;
import team.placeholder.internalprojectsmanagementsystem.model.user.User;
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
import team.placeholder.internalprojectsmanagementsystem.repository.project.AvailableUserRepo;
import team.placeholder.internalprojectsmanagementsystem.repository.user.UserRepository;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginUserTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AvailableUserRepo availableUserRepo;

    @InjectMocks
    private LoginUser loginUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testLoginUser(){
        UserDto userDto = new UserDto();
        userDto.setRole(Role.PROJECT_MANAGER);
        userDto.setId(1L);

        when(userService.getUserByEmail(any())).thenReturn(userDto);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser"); // Set the username as needed
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<Map<String, Object>> responseEntity = loginUser.loginUser();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody().get("currentUser"));

        verify(userService, times(1)).getUserByEmail(any());
    }

    @Test
    public void testGetAvailableEmployee(){

        String userEmail = "pm@example.com"; // Replace with the expected project manager's email
        UserDto mockUserDto = new UserDto(); // Create a mock UserDto with test data
        mockUserDto.setRole(Role.PROJECT_MANAGER); // Set the role to PROJECT_MANAGER

        // Mock the behavior of userService.getUserByEmail
        when(userService.getUserByEmail(userEmail)).thenReturn(mockUserDto);

        // Mock the behavior of userRepository.findAllByProjectManagerId
        List<User> mockEmployees = new ArrayList<>(); // Create a list of mock employees with test data
        when(userRepository.findAllByProjectManagerId(mockUserDto.getId())).thenReturn(mockEmployees);

        // Mock the behavior of availableUserRepo.findByUserIdIn
        List<AvailableUser> mockUserAvailabilities = new ArrayList<>(); // Create a list of mock availabilities with test data
        when(availableUserRepo.findByUserIdIn(mockEmployees.stream().map(User::getId).toList())).thenReturn(mockUserAvailabilities);

        // Simulate a logged-in user in the SecurityContextHolder
        Authentication authentication = new UsernamePasswordAuthenticationToken(userEmail, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Act
        ResponseEntity<List<UserDto>> result = loginUser.getAvailableEmployees();

        // Assert
        assertEquals(200, result.getStatusCodeValue());


    }

}