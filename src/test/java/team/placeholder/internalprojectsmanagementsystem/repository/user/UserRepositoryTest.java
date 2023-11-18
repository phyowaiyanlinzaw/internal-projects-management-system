package team.placeholder.internalprojectsmanagementsystem.repository.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import team.placeholder.internalprojectsmanagementsystem.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        // Mock the behavior of userRepository.findByEmail
        when(userRepository.findByEmail(email)).thenReturn(user);

        // Act
        User result = userRepository.findByEmail(email);

        // Assert
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testFindByName() {
        // Arrange
        String name = "TestUser";
        User user = new User();
        user.setName(name);

        // Mock the behavior of userRepository.findByName
        when(userRepository.findByName(name)).thenReturn(user);

        // Act
        User result = userRepository.findByName(name);

        // Assert
        assertEquals(name, result.getName());
    }



    @Test
    public void testFindById() {
        // Arrange
        long id = 1L;
        User user = new User();
        user.setId(id);

        // Mock the behavior of userRepository.findById
        when(userRepository.findById(id)).thenReturn(user);

        // Act
        User result = userRepository.findById(id);

        // Assert
        assertEquals(id, result.getId());
    }

    @Test
    public void testFindAllByRole(){

    }

    @Test
    public void testFindAllByProjectManagerId(){

    }

    @Test
    public void testFindUserByDepartmentIdAndRole(){

    }

    @Test
    public void testFindCountAllByDepartmentId(){

    }

    @Test
    public void testFindAllByProjectId(){

    }






}
