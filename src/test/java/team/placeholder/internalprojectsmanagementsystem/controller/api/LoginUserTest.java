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
import team.placeholder.internalprojectsmanagementsystem.model.user.userenums.Role;
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

        UserDto userDto = new UserDto();
        userDto.setRole(Role.PROJECT_MANAGER);
        userDto.setId(1L);

        List<UserDto> userDtos = new ArrayList<>();

        when(userService.getUserByEmail(any())).thenReturn(userDto);
        when(userService.getAllUsersByPMId(anyLong())).thenReturn(userDtos);

        when(userRepository.findAllByProjectManagerId(anyLong())).thenReturn(new ArrayList<>());

        Authentication authentication = new UsernamePasswordAuthenticationToken("testUser", "testPassword");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<List<UserDto>> responseEntity = loginUser.getAvailableEmployees();

        // Verify that the response is as expected
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDtos, responseEntity.getBody());

        verify(userService, times(1)).getUserByEmail(any());
        verify(userService, times(1)).getAllUsersByPMId(anyLong());

        verify(userRepository, times(1)).findAllByProjectManagerId(anyLong());

    }

}