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
    public void testGetAvailableEmployee(){

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("example@example.com");

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setRole(Role.PROJECT_MANAGER);
        when(userService.getUserByEmail("example@example.com")).thenReturn(userDto);

        // Mock employees working under the project manager
        User employee1 = new User();
        employee1.setId(2L);
        User employee2 = new User();
        employee2.setId(3L);
        List<User> employeeList = List.of(employee1, employee2);
        when(userRepository.findAllByProjectManagerId(1L)).thenReturn(employeeList);

        // Mock available users
        AvailableUser availableUser1 = new AvailableUser();
        availableUser1.setUser(employee1);
        availableUser1.setAvaliable(true);
        AvailableUser availableUser2 = new AvailableUser();
        availableUser2.setUser(employee2);
        availableUser2.setAvaliable(false);
        List<AvailableUser> availableUserList = List.of(availableUser1, availableUser2);
        when(availableUserRepository.findByUserIdIn(List.of(2L, 3L))).thenReturn(availableUserList);

        // Call the controller method
        ResponseEntity<List<UserDto>> responseEntity = loginUser.getAvailableEmployees();

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<UserDto> result = responseEntity.getBody();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getId());


    }

}