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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class LoginUserTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AvailableUserRepo availableUserRepository;

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
    void testGetAvailableEmployees() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setRole(Role.PROJECT_MANAGER);
        userDto.setId(1L);

        when(userService.getUserByEmail(any())).thenReturn(userDto);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testUser");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock userRepository and availableUserRepo as needed
        // ...

        // Act
        ResponseEntity<List<UserDto>> responseEntity = loginUser.getAvailableEmployees();

        // Assert
        if (userDto.getRole() == Role.PROJECT_MANAGER) {
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            // You can add more assertions based on the expected behavior of your method
            // For example, verifying that userService.getAllUsersByPMId() is called
            // and checking the content of the returned list
            // verify(userService, times(1)).getAllUsersByPMId(anyLong());
        } else {
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }

        // Verify that userService.getUserByEmail is called
        verify(userService, times(1)).getUserByEmail(any());
    }

}