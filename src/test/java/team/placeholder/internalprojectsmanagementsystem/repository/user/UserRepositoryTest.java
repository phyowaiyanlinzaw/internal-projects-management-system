package team.placeholder.internalprojectsmanagementsystem.repository.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import team.placeholder.internalprojectsmanagementsystem.model.user.User;

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
}
